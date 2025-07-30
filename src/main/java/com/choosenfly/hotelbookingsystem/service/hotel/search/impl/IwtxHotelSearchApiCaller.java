package com.choosenfly.hotelbookingsystem.service.hotel.search.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
import org.springframework.web.client.RestTemplate;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.RoomConfiguration;
import com.choosenfly.hotelbookingsystem.dto.iwtx.BaseRateIwtx;
import com.choosenfly.hotelbookingsystem.dto.iwtx.HotelBaseRate;
import com.choosenfly.hotelbookingsystem.dto.iwtx.HotelInfoIwtx;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.AdultiwtxSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.ChildiwtxSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.HotelIwtxSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.ProfileiwtxSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.RoomConfigurationiwtxSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.RoomiwxtSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.request.SearchCriteriaIwtxSearch;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response.HotelIwtx;
import com.choosenfly.hotelbookingsystem.dto.iwtx.api.search.response.RoomIwtxResponse;
import com.choosenfly.hotelbookingsystem.service.hotel.search.HotelSearchApiCaller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Component
public class IwtxHotelSearchApiCaller implements HotelSearchApiCaller {

//    @Autowired
//    private IwtxHotelsRepository iwtxHotelsRepository;

//    @Autowired
//    private ConCityMappingRepository conCityMappingRepository;
//
//    @Autowired
//    private ConCountryMappingRepository conCountryMappingRepository;


    
    private static final int BATCH_SIZE = 50;
    private static final int THREAD_POOL_SIZE = 10;

    private static final String API_URL = "https://api.iwtxconnect.com/hotel/api/v1/search";

    @Override
    public List<HotelSearchResult> callApi(HotelSearchRequest request) {
        List<HotelSearchResult> results = new ArrayList<>();

        // Step 1: Fetch hotel details from the database
//        List<HotelInfoIwtx> hotelInfos = hotelRepository.findHotelsByCityAndCountry(
//            request.getDestinationCityId(),
//            request.getDestinationCountryId()
//        );
        

        
        List<HotelInfoIwtx> hotelInfos = Arrays.asList(
        	    new HotelInfoIwtx("9999-19861433", "The Grand Palace", "https://example.com/images/h001.jpg", 5, "123 Palace Road, City A"),
        	    new HotelInfoIwtx("9999-19861463", "Sunset Resort", "https://example.com/images/h002.jpg", 4, "456 Beach Avenue, City B")
        	);

        if (hotelInfos == null || hotelInfos.isEmpty()) {
            return results; // Return empty list if no hotels found
        }

        // Step 2: Extract hotel codes and split into batches of 50
        List<String> hotelCodes = hotelInfos.stream()
            .map(HotelInfoIwtx::getHotelCode)
            .collect(Collectors.toList());
        List<List<String>> batches = splitIntoBatches(hotelCodes, BATCH_SIZE);

        // Step 3: Make parallel API calls for each batch
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<List<HotelBaseRate>>> futures = new ArrayList<>();

        for (List<String> batch : batches) {
            futures.add(executor.submit(() -> callApiForBatch(batch, request)));
        }

        // Step 4: Collect API responses
        Map<String, Double> baseRateMap = new HashMap<>();
        for (Future<List<HotelBaseRate>> future : futures) {
            try {
                List<HotelBaseRate> hotelRates = future.get();
                for (HotelBaseRate rate : hotelRates) {
                    baseRateMap.put(rate.getHotelCode(), rate.getBaseRate());
                }
            } catch (Exception e) {
                // Log error and continue processing other batches
                System.err.println("Error processing batch: " + e.getMessage());
            }
        }

        executor.shutdown();

        // Step 5: Map hotel details and base rates to HotelSearchResult
        for (HotelInfoIwtx hotelInfo : hotelInfos) {
            HotelSearchResult result = new HotelSearchResult();
            result.setHotelCode(hotelInfo.getHotelCode());
            result.setHotelName(hotelInfo.getHotelName());
            result.setHotelImage(hotelInfo.getHotelImage());
            result.setStarRating(hotelInfo.getStarRating());
            result.setHotelAddress(hotelInfo.getHotelAddress());
            result.setApiType("IWTX");
            result.setBaseRate(baseRateMap.getOrDefault(hotelInfo.getHotelCode(), null));
            results.add(result);
        }

        System.out.println(results);
        return results;
        
    }

    private List<List<String>> splitIntoBatches(List<String> hotelCodes, int batchSize) {
        List<List<String>> batches = new ArrayList<>();
        for (int i = 0; i < hotelCodes.size(); i += batchSize) {
            batches.add(hotelCodes.subList(i, Math.min(i + batchSize, hotelCodes.size())));
        }
        return batches;
    }

    private List<HotelBaseRate> callApiForBatch(List<String> hotelCodes, HotelSearchRequest request) {
        List<HotelBaseRate> hotelRates = new ArrayList<>();
        try {
            // Create XML request
        	HotelIwtxSearchRequest requestXml = createHotelSearchRequestXml(hotelCodes, request);
            String xmlRequest = jaxbObjectToXML(requestXml);

            // Make API call
            BaseRateIwtx responseXml = callExternalApi(xmlRequest);

            
            System.out.println("Response :: "+responseXml);
            // Process response
            if (responseXml != null && responseXml.getHotels() != null && responseXml.getHotels().getHotel() != null) {
                for (HotelIwtx hotel : responseXml.getHotels().getHotel()) {
                    Double baseRate = calculateBaseRate(hotel, Integer.parseInt(request.getNoOfRooms()));
                    if (baseRate != null) {
                        hotelRates.add(new HotelBaseRate(hotel.getHotelCode(), baseRate));
                    }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            // Log error and return empty rates for this batch
            System.err.println("Error calling API for batch: " + e.getMessage());
        }
        return hotelRates;
    }

    private HotelIwtxSearchRequest createHotelSearchRequestXml(List<String> hotelCodes, HotelSearchRequest request) {
    	HotelIwtxSearchRequest requestXml = new HotelIwtxSearchRequest();
        requestXml.setOutputFormat("XML");

        ProfileiwtxSearch profile = new ProfileiwtxSearch();
        profile.setPassword("C0nN3cTW0rLd_2O23");
        profile.setCode("Connect_World");
        profile.setTokenNumber("1d11cf19-fc8b-4d58-b590-a048bc601f81-20230516");
        requestXml.setProfile(profile);

        SearchCriteriaIwtxSearch criteria = new SearchCriteriaIwtxSearch();
        criteria.setHotelCode(String.join(",", hotelCodes));
        criteria.setStartDate(request.getCheckIn().replace("-", ""));
        criteria.setEndDate(request.getCheckOut().replace("-", ""));
        criteria.setNationality(request.getNationalityId());
        criteria.setGroupByRooms("Y");
        criteria.setCancellationPolicy("Y");

        RoomConfigurationiwtxSearch roomConfig = new RoomConfigurationiwtxSearch();
        

        for (RoomConfiguration config : request.getRoomConfigurations()) {
            RoomiwxtSearch room = new RoomiwxtSearch();
            if (config.getAdultAges() != null) {
            	List<Integer> adultAges = config.getAdultAges();
            	if (adultAges != null && !adultAges.isEmpty()) {
            	    AdultiwtxSearch[] adults = adultAges.stream()
            	        .map(age -> {
            	            AdultiwtxSearch adult = new AdultiwtxSearch();
            	            adult.setAge(age);
            	            return adult;
            	        })
            	        .toArray(AdultiwtxSearch[]::new);
            	    
            	    room.setAdult(adults);
            	    
            	} else {
            	    room.setAdult(new AdultiwtxSearch[0]);
     
            	}
            }
            
            
            if (config.getChildAges() != null) {
            	List<Integer> childAges = config.getChildAges();
            	if (childAges != null && !childAges.isEmpty()) {
            		ChildiwtxSearch[] childs = childAges.stream()
            	        .map(age -> {
            	        	ChildiwtxSearch child = new ChildiwtxSearch();
            	        	child.setAge(age);
            	            return child;
            	        })
            	        .toArray(ChildiwtxSearch[]::new);
            	    
            	    room.setChild(childs);
            	    
            	} else {
            	    room.setChild(new ChildiwtxSearch[0]); // Set empty array if no adults
            	}
            }
            
            roomConfig.setRoom(room);
           
        }
        criteria.setRoomConfiguration(roomConfig);
        requestXml.setSearchCriteria(criteria);

        return requestXml;
    }

    private String jaxbObjectToXML(HotelIwtxSearchRequest requestXml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(HotelIwtxSearchRequest.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(requestXml, writer);
        return writer.toString();
    }

    private BaseRateIwtx callExternalApi(String xmlRequest) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);
        httpPost.addHeader("Content-Type", "application/xml");
        httpPost.addHeader("Content-Encoding", "gzip");

        System.out.println("request :: "+xmlRequest);
        
        StringEntity entity = new StringEntity(xmlRequest);
        httpPost.setEntity(entity);

        try (CloseableHttpResponse response = client.execute(httpPost)) {
            if (response.getEntity() != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String output = builder.toString()
                    .replace(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "")
                    .replace(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"", "")
                    .replace("xsi:nil=\"true\"", "");

                JAXBContext jaxbContext = JAXBContext.newInstance(BaseRateIwtx.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                return (BaseRateIwtx) unmarshaller.unmarshal(new StringReader(output));
            }
        }
        return null;
    }

    private Double calculateBaseRate(HotelIwtx hotel, int roomCount) {
        if (hotel == null || hotel.getRoomTypeDetails() == null || hotel.getRoomTypeDetails().getRooms() == null || hotel.getRoomTypeDetails().getRooms().getRoom() == null) {
            return null;
        }

        List<Double> rateList = new ArrayList<>();
        for (RoomIwtxResponse room : hotel.getRoomTypeDetails().getRooms().getRoom()) {
            double usdRate = room.getTotalRate();
            double aedRate = usdRate * 3.67; // Convert to AED
            double roundedAedRate = Math.round(aedRate * 100.0) / 100.0;
            rateList.add(roundedAedRate);
        }

        Optional<Double> minValue = rateList.stream().min(Double::compare);
        if (minValue.isPresent()) {
            return Math.round(minValue.get() * roomCount * 100.0) / 100.0;
        }
        return null;
    }

	@Override
	public String getApiKey() {
		// TODO Auto-generated method stub
		return "iwtx";
	}
	

} 