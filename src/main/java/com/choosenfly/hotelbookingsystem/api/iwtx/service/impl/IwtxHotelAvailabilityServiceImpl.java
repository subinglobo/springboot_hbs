package com.choosenfly.hotelbookingsystem.api.iwtx.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.Adult;
import com.choosenfly.hotelbookingsystem.configuration.IwtxApiConfig;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelAvailabilityRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.Profile;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.Room;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.RoomConfiguration;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.SearchCriteria;
import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.response.HotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.iwtx.service.CurrencyConversionService;
import com.choosenfly.hotelbookingsystem.api.iwtx.service.DateFormatService;
import com.choosenfly.hotelbookingsystem.api.iwtx.service.IwtxHotelAvailabilityService;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Service
public class IwtxHotelAvailabilityServiceImpl implements IwtxHotelAvailabilityService {

    @Autowired
    private IwtxApiConfig iwtxApiConfig;
    
    @Autowired
    private CurrencyConversionService currencyConversionService;
    
    @Autowired
    private DateFormatService dateFormatService;

    @Override
    public HotelSearchResponse checkHotelAvailability(HotelSearchRequest request) throws Exception {
        // Ensure profile is set with configuration values
        if (request.getProfile() == null) {
            request.setProfile(createProfile());
        } else {
            // Override with configuration values to ensure consistency
            request.getProfile().setPassword(iwtxApiConfig.getPassword());
            request.getProfile().setCode(iwtxApiConfig.getCode());
            request.getProfile().setTokenNumber(iwtxApiConfig.getToken());
        }
        
        // Convert request object to XML
        String xmlRequest = convertToXml(request);
        
        // Make HTTP POST call to IWTX API
        String xmlResponse = callIwtxApi(xmlRequest);
        
        // Convert XML response back to object
        return convertFromXml(xmlResponse);
    }

    @Override
    public HotelSearchResponse checkHotelAvailability(HotelAvailabilityRequest request) throws Exception {
        // Convert simplified request to full request with auto-populated profile
        HotelSearchRequest fullRequest = new HotelSearchRequest();
        fullRequest.setProfile(createProfile());
        
        // Convert dates from user format (YYYY-MM-DD) to IWTX format (YYYYMMDD)
        SearchCriteria searchCriteria = request.getSearchCriteria();
        if (searchCriteria != null) {
            if (searchCriteria.getStartDate() != null) {
                searchCriteria.setStartDate(dateFormatService.convertToIwtxFormat(searchCriteria.getStartDate()));
            }
            if (searchCriteria.getEndDate() != null) {
                searchCriteria.setEndDate(dateFormatService.convertToIwtxFormat(searchCriteria.getEndDate()));
            }
        }
        
        fullRequest.setSearchCriteria(searchCriteria);
        
        HotelSearchResponse response = checkHotelAvailability(fullRequest);
        
        // Convert response dates back to user format and rates to AED
        return processResponseForUser(response);
    }

    @Override
    public HotelSearchRequest createSampleRequest(String hotelCode, String startDate, String endDate, String nationality) {
        HotelSearchRequest request = new HotelSearchRequest();
        
        // Set profile information from configuration
        request.setProfile(createProfile());
        
        // Set search criteria
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setHotelCode(hotelCode);
        // Convert dates to IWTX format if needed
        searchCriteria.setStartDate(dateFormatService.convertToIwtxFormat(startDate));
        searchCriteria.setEndDate(dateFormatService.convertToIwtxFormat(endDate));
        searchCriteria.setNationality(nationality);
        searchCriteria.setIncludeRateDetails("Y");
        searchCriteria.setCancellationPolicy("Y");
        searchCriteria.setGroupByRooms("Y");
        
        // Set room configuration
        RoomConfiguration roomConfig = new RoomConfiguration();
        Room room = new Room();
        
        // Set adult information
        Adult adult = new Adult();
        adult.setAge("25");
        room.setAdult(adult);
        
        // Set room details (sample values)
        room.setRoomTypeCode("16306703");
        room.setMealPlanCode("7");
        room.setContractTokenId("766536");
        room.setRoomConfigurationId("1");
        
        roomConfig.setRoom(room);
        searchCriteria.setRoomConfiguration(roomConfig);
        
        request.setSearchCriteria(searchCriteria);
        
        return request;
    }

    private String convertToXml(HotelSearchRequest request) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(HotelSearchRequest.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        StringWriter writer = new StringWriter();
        marshaller.marshal(request, writer);
        
        return writer.toString();
    }

    private String callIwtxApi(String xmlRequest) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(iwtxApiConfig.getAvailability().getUrl());
        
        // Set headers
        httpPost.addHeader("Content-Type", "application/xml");
        httpPost.addHeader("Content-Encoding", "gzip");
        
        // Set request body
        StringEntity entity = new StringEntity(xmlRequest, "UTF-8");
        httpPost.setEntity(entity);
        
        try (CloseableHttpResponse response = client.execute(httpPost)) {
            if (response.getEntity() != null) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent())
                );
                
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                
                // Clean up XML response (remove namespace attributes that might cause issues)
                String cleanedResponse = builder.toString()
                    .replace(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "")
                    .replace(" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"", "")
                    .replace("xsi:nil=\"true\"", "");
                
                return cleanedResponse;
            }
        }
        
        throw new Exception("Failed to get response from IWTX API");
    }

    private HotelSearchResponse convertFromXml(String xmlResponse) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(HotelSearchResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        
        return (HotelSearchResponse) unmarshaller.unmarshal(new StringReader(xmlResponse));
    }
    
    private Profile createProfile() {
        Profile profile = new Profile();
        profile.setPassword(iwtxApiConfig.getPassword());
        profile.setCode(iwtxApiConfig.getCode());
        profile.setTokenNumber(iwtxApiConfig.getToken());
        return profile;
    }
    
    /**
     * Process response for user consumption - convert dates to user format and rates to AED
     */
    private HotelSearchResponse processResponseForUser(HotelSearchResponse response) {
        if (response == null) {
            return response;
        }
        
        // Convert dates and currency in the response
        if (response.getHotels() != null && response.getHotels().getHotel() != null) {
            response.getHotels().getHotel().forEach(hotel -> {
                // Convert hotel dates
                if (hotel.getStartDate() != null) {
                    hotel.setStartDate(dateFormatService.convertToUserFormat(hotel.getStartDate()));
                }
                if (hotel.getEndDate() != null) {
                    hotel.setEndDate(dateFormatService.convertToUserFormat(hotel.getEndDate()));
                }
                
                // Convert room rates to AED
                if (hotel.getRoomTypeDetails() != null && hotel.getRoomTypeDetails().getRooms() != null 
                    && hotel.getRoomTypeDetails().getRooms().getRoom() != null) {
                    
                    hotel.getRoomTypeDetails().getRooms().getRoom().forEach(room -> {
                        // Convert buy rate to AED
                        if (room.getBuyRate() != null) {
                            room.setBuyRate(currencyConversionService.convertToAED(room.getBuyRate(), "USD"));
                        }
                        
                        // Convert main rate to AED
                        if (room.getRate() != null) {
                            room.setRate(currencyConversionService.convertToAED(room.getRate(), "USD"));
                        }
                        
                        // Convert rate details
                        if (room.getRateDetails() != null && room.getRateDetails().getRate() != null) {
                            room.getRateDetails().setRate(currencyConversionService.convertToAED(room.getRateDetails().getRate(), "USD"));
                        }
                        
                        // Convert recommended retail price to AED
                        if (room.getRecommendedRetailPrice() != null) {
                            room.setRecommendedRetailPrice(currencyConversionService.convertToAED(room.getRecommendedRetailPrice(), "USD"));
                        }
                        
                        // Convert cash back amount to AED
                        if (room.getCashBackAmount() != null) {
                            room.setCashBackAmount(currencyConversionService.convertToAED(room.getCashBackAmount(), "USD"));
                        }
                        
                        // Update currency code to AED
                        room.setCurrCode("AED");
                    });
                }
            });
        }
        
        return response;
    }
}
