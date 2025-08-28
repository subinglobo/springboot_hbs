package com.choosenfly.hotelbookingsystem.hotel.search.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.api.x3.dto.BaseRateX3;
import com.choosenfly.hotelbookingsystem.api.x3.dto.HotelBaseRateX3;
import com.choosenfly.hotelbookingsystem.api.x3.dto.HotelInfoX3;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.AdultX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.ChildX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.HotelX3SearchRequest;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.ProfileX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.RoomConfigurationX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.RoomX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.request.SearchCriteriaX3Search;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.response.HotelX3;
import com.choosenfly.hotelbookingsystem.api.x3.dto.search.response.RoomX3Response;
import com.choosenfly.hotelbookingsystem.api.x3.repository.X3HotelRepository;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.RoomConfiguration;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchApiCaller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Component
public class X3HotelSearchApiCaller implements HotelSearchApiCaller {

    @Autowired
    private X3HotelRepository x3HotelsRepository;

    private static final int X3_BATCH_SIZE = 50;
    private static final int X3_THREAD_POOL_SIZE = 10;

    // TODO: Replace with actual X3 API URL when credentials are provided
    private static final String X3_API_URL = "https://api.x3connect.com/hotel/api/v1/search";

    @Override
    public List<HotelSearchResult> callApi(HotelSearchRequest request) {
        List<HotelSearchResult> x3Results = new ArrayList<>();

        List<HotelInfoX3> x3HotelInfos = x3HotelsRepository.findHotelsByCityAndCountry(request.getDestinationCityId(), request.getDestinationCountryId());

        if (x3HotelInfos == null || x3HotelInfos.isEmpty()) {
            return x3Results; // Return empty list if no hotels found
        }

        // Step 2: Extract hotel codes and split into batches of 50
        List<String> x3HotelCodes = x3HotelInfos.stream()
            .map(HotelInfoX3::getHotelCode)
            .collect(Collectors.toList());
        List<List<String>> x3Batches = splitX3IntoBatches(x3HotelCodes, X3_BATCH_SIZE);

        // Step 3: Make parallel API calls for each batch
        ExecutorService x3Executor = Executors.newFixedThreadPool(X3_THREAD_POOL_SIZE);
        List<Future<List<HotelBaseRateX3>>> x3Futures = new ArrayList<>();

        for (List<String> x3Batch : x3Batches) {
            x3Futures.add(x3Executor.submit(() -> callX3ApiForBatch(x3Batch, request)));
        }

        // Step 4: Collect API responses
        Map<String, Double> x3BaseRateMap = new HashMap<>();
        for (Future<List<HotelBaseRateX3>> x3Future : x3Futures) {
            try {
                List<HotelBaseRateX3> x3HotelRates = x3Future.get();
                for (HotelBaseRateX3 x3Rate : x3HotelRates) {
                    x3BaseRateMap.put(x3Rate.getHotelCode(), x3Rate.getBaseRate());
                }
            } catch (Exception e) {
                // Log error and continue processing other batches
                System.err.println("Error processing X3 batch: " + e.getMessage());
            }
        }

        x3Executor.shutdown();

        // Step 5: Map hotel details and base rates to HotelSearchResult
        for (HotelInfoX3 x3HotelInfo : x3HotelInfos) {
            Double x3BaseRate = x3BaseRateMap.get(x3HotelInfo.getHotelCode());
            if (x3BaseRate != null) {  // ✅ Only add to results if baseRate is available
                HotelSearchResult x3Result = new HotelSearchResult();
                x3Result.setHotelCode(x3HotelInfo.getHotelCode());
                x3Result.setHotelName(x3HotelInfo.getHotelName());
                x3Result.setHotelImage(x3HotelInfo.getHotelImage());
                x3Result.setStarRating(x3HotelInfo.getStarRating());
                x3Result.setHotelAddress(x3HotelInfo.getHotelAddress());
                x3Result.setApiType("X3");
                x3Result.setBaseRate(x3BaseRate);
                x3Results.add(x3Result);
            }
        }
        System.out.println("X3 Results: " + x3Results);
        return x3Results;
    }

    private List<List<String>> splitX3IntoBatches(List<String> x3HotelCodes, int x3BatchSize) {
        List<List<String>> x3Batches = new ArrayList<>();
        for (int i = 0; i < x3HotelCodes.size(); i += x3BatchSize) {
            x3Batches.add(x3HotelCodes.subList(i, Math.min(i + x3BatchSize, x3HotelCodes.size())));
        }
        return x3Batches;
    }

    private List<HotelBaseRateX3> callX3ApiForBatch(List<String> x3HotelCodes, HotelSearchRequest request) {
        List<HotelBaseRateX3> x3HotelRates = new ArrayList<>();
        try {
            // Create XML request
            HotelX3SearchRequest x3RequestXml = createX3HotelSearchRequestXml(x3HotelCodes, request);
            String x3XmlRequest = jaxbX3ObjectToXML(x3RequestXml);

            // Make API call
            BaseRateX3 x3ResponseXml = callX3ExternalApi(x3XmlRequest);

            System.out.println("X3 Response :: " + x3ResponseXml);
            // Process response
            if (x3ResponseXml != null && x3ResponseXml.getHotels() != null && x3ResponseXml.getHotels().getHotel() != null) {
                for (HotelX3 x3Hotel : x3ResponseXml.getHotels().getHotel()) {
                    Double x3BaseRate = calculateX3BaseRate(x3Hotel, Integer.parseInt(request.getNoOfRooms()));
                    if (x3BaseRate != null) {
                        x3HotelRates.add(new HotelBaseRateX3(x3Hotel.getHotelCode(), x3BaseRate));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log error and return empty rates for this batch
            System.err.println("Error calling X3 API for batch: " + e.getMessage());
        }
        return x3HotelRates;
    }

    private HotelX3SearchRequest createX3HotelSearchRequestXml(List<String> x3HotelCodes, HotelSearchRequest request) {
        HotelX3SearchRequest x3RequestXml = new HotelX3SearchRequest();
        x3RequestXml.setOutputFormat("XML");

        ProfileX3Search x3Profile = new ProfileX3Search();
        // TODO: Replace with actual X3 credentials when provided
        x3Profile.setPassword("X3_PASSWORD_PLACEHOLDER");
        x3Profile.setCode("X3_CODE_PLACEHOLDER");
        x3Profile.setTokenNumber("X3_TOKEN_PLACEHOLDER");
        x3RequestXml.setProfile(x3Profile);

        SearchCriteriaX3Search x3Criteria = new SearchCriteriaX3Search();
        x3Criteria.setHotelCode(String.join(",", x3HotelCodes));
        x3Criteria.setStartDate(request.getCheckIn().replace("-", ""));
        x3Criteria.setEndDate(request.getCheckOut().replace("-", ""));
        x3Criteria.setNationality(request.getNationalityId());
        x3Criteria.setGroupByRooms("Y");
        x3Criteria.setCancellationPolicy("Y");

        RoomConfigurationX3Search x3RoomConfig = new RoomConfigurationX3Search();

        for (RoomConfiguration config : request.getRoomConfigurations()) {
            RoomX3Search x3Room = new RoomX3Search();
            if (config.getAdultAges() != null) {
                List<Integer> adultAges = config.getAdultAges();
                if (adultAges != null && !adultAges.isEmpty()) {
                    AdultX3Search[] x3Adults = adultAges.stream()
                        .map(age -> {
                            AdultX3Search x3Adult = new AdultX3Search();
                            x3Adult.setAge(age);
                            return x3Adult;
                        })
                        .toArray(AdultX3Search[]::new);
                    
                    x3Room.setAdult(x3Adults);
                    
                } else {
                    x3Room.setAdult(new AdultX3Search[0]);
                }
            }
            
            if (config.getChildAges() != null) {
                List<Integer> childAges = config.getChildAges();
                if (childAges != null && !childAges.isEmpty()) {
                    ChildX3Search[] x3Childs = childAges.stream()
                        .map(age -> {
                            ChildX3Search x3Child = new ChildX3Search();
                            x3Child.setAge(age);
                            return x3Child;
                        })
                        .toArray(ChildX3Search[]::new);
                    
                    x3Room.setChild(x3Childs);
                    
                } else {
                    x3Room.setChild(new ChildX3Search[0]); // Set empty array if no children
                }
            }
            
            x3RoomConfig.setRoom(x3Room);
        }
        x3Criteria.setRoomConfiguration(x3RoomConfig);
        x3RequestXml.setSearchCriteria(x3Criteria);

        return x3RequestXml;
    }

    private String jaxbX3ObjectToXML(HotelX3SearchRequest x3RequestXml) throws JAXBException {
        JAXBContext x3JaxbContext = JAXBContext.newInstance(HotelX3SearchRequest.class);
        Marshaller x3Marshaller = x3JaxbContext.createMarshaller();
        x3Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter x3Writer = new StringWriter();
        x3Marshaller.marshal(x3RequestXml, x3Writer);
        return x3Writer.toString();
    }

    private BaseRateX3 callX3ExternalApi(String x3XmlRequest) throws Exception {
        CloseableHttpClient x3Client = HttpClients.createDefault();
        HttpPost x3HttpPost = new HttpPost(X3_API_URL);
        x3HttpPost.addHeader("Content-Type", "application/xml");
        x3HttpPost.addHeader("Content-Encoding", "gzip");

        System.out.println("X3 request :: " + x3XmlRequest);
        
        StringEntity x3Entity = new StringEntity(x3XmlRequest);
        x3HttpPost.setEntity(x3Entity);

        try (CloseableHttpResponse x3Response = x3Client.execute(x3HttpPost)) {
            if (x3Response.getEntity() != null) {
                BufferedReader x3Reader = new BufferedReader(new InputStreamReader(x3Response.getEntity().getContent()));
                StringBuilder x3Builder = new StringBuilder();
                String x3Line;
                while ((x3Line = x3Reader.readLine()) != null) {
                    x3Builder.append(x3Line);
                }
                String x3Output = x3Builder.toString()
                    .replace(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "")
                    .replace(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"", "")
                    .replace("xsi:nil=\"true\"", "");

                JAXBContext x3JaxbContext = JAXBContext.newInstance(BaseRateX3.class);
                Unmarshaller x3Unmarshaller = x3JaxbContext.createUnmarshaller();
                return (BaseRateX3) x3Unmarshaller.unmarshal(new StringReader(x3Output));
            }
        }
        return null;
    }

    private Double calculateX3BaseRate(HotelX3 x3Hotel, int x3RoomCount) {
        if (x3Hotel == null || x3Hotel.getRoomTypeDetails() == null || x3Hotel.getRoomTypeDetails().getRooms() == null || x3Hotel.getRoomTypeDetails().getRooms().getRoom() == null) {
            return null;
        }

        List<Double> x3RateList = new ArrayList<>();
        for (RoomX3Response x3Room : x3Hotel.getRoomTypeDetails().getRooms().getRoom()) {
            double x3UsdRate = x3Room.getTotalRate();
            double x3AedRate = x3UsdRate * 3.67; // Convert to AED
            double x3RoundedAedRate = Math.round(x3AedRate * 100.0) / 100.0;
            x3RateList.add(x3RoundedAedRate);
        }

        Optional<Double> x3MinValue = x3RateList.stream().min(Double::compare);
        if (x3MinValue.isPresent()) {
            return Math.round(x3MinValue.get() * x3RoomCount * 100.0) / 100.0;
        }
        return null;
    }

    @Override
    public String getApiKey() {
        return "x3";
    }
}
