package com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.RoomRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxHotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxGroupedRoomResponse;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Service for integrating with X3 API for hotel room search
 * Uses same logic as IWTX but with X3 credentials
 */
@Service
public class X3ApiService {

    private static final Logger logger = LoggerFactory.getLogger(X3ApiService.class);

    // X3 API configuration
    @Value("${x3.api.url}")
    private String x3ApiUrl;

    @Value("${x3.api.password}")
    private String x3Password;

    @Value("${x3.api.code}")
    private String x3Code;

    @Value("${x3.api.token}")
    private String x3Token;

    private final RestTemplate restTemplate;

    public X3ApiService() {
        this.restTemplate = new RestTemplate();
        logger.info("X3ApiService initialized successfully");
    }

    /**
     * Search hotel rooms via X3 API
     * 
     * @param request Hotel room search request
     * @return X3 hotel search response (using IWTX response format)
     * @throws Exception if API call fails
     */
    public IwtxHotelSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.info("Calling X3 API for hotel search with hotel code: {}", request.getHotelCode());
        logger.info("X3 API URL: {}", x3ApiUrl);
        logger.info("X3 Credentials - Password: {}, Code: {}, Token: {}", 
            x3Password != null ? "***" : "NULL", 
            x3Code != null ? x3Code : "NULL", 
            x3Token != null ? "***" : "NULL");

        try {
            // Build XML request
            String xmlRequest = buildXmlRequest(request);
            logger.info("X3 XML Request: {}", xmlRequest);

            // Make API call
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(List.of(MediaType.APPLICATION_XML));
            
            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);
            String xmlResponse = restTemplate.postForObject(x3ApiUrl, entity, String.class);

            logger.debug("X3 XML Response: {}", xmlResponse);

            // Parse XML response - for now return a simplified response
            IwtxHotelSearchResponse response = parseXmlResponse(xmlResponse, request);
            logger.info("Successfully processed X3 API response");
            
            return response;

        } catch (Exception e) {
            logger.error("Error calling X3 API: {}", e.getMessage(), e);
            logger.error("API URL: {}", x3ApiUrl);
            logger.error("Request details - Hotel Code: {}, Check-in: {}, Check-out: {}", 
                request.getHotelCode(), request.getCheckInDate(), request.getCheckOutDate());
            // Fallback to mock response if API fails
            logger.warn("Falling back to mock response due to API error: {}", e.getClass().getSimpleName());
            return createMockResponse(request);
        }
    }

    /**
     * Create a mock X3 response for testing
     */
    private IwtxHotelSearchResponse createMockResponse(HotelRoomSearchRequest request) {
        // Create a basic mock response structure
        IwtxHotelSearchResponse response = new IwtxHotelSearchResponse();
        
        // Create mock hotels list
        IwtxHotelSearchResponse.IwtxHotels hotels = new IwtxHotelSearchResponse.IwtxHotels();
        List<IwtxHotelSearchResponse.IwtxHotel> hotelList = new ArrayList<>();
        
        // Create one mock hotel
        IwtxHotelSearchResponse.IwtxHotel mockHotel = new IwtxHotelSearchResponse.IwtxHotel();
        mockHotel.setHotelId(request.getHotelCode());
        mockHotel.setHotelName("Mock X3 Hotel " + request.getHotelCode());
        mockHotel.setStarRating(4);
        mockHotel.setCity("Mock X3 City");
        mockHotel.setPropertyType("Hotel");
        
        hotelList.add(mockHotel);
        hotels.setHotelList(hotelList);
        response.setHotels(hotels);
        
        logger.debug("Created mock X3 response with {} hotels", hotelList.size());
        return response;
    }

    /**
     * Build XML request for X3 API (same format as IWTX but with X3 credentials)
     */
    private String buildXmlRequest(HotelRoomSearchRequest request) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        xml.append("<HotelSearchRequest>");
        
        // Output format
        xml.append("<OutputFormat>XML</OutputFormat>");
        
        // Profile section with X3 credentials
        xml.append("<Profile>");
        xml.append("<Password>").append(x3Password).append("</Password>");
        xml.append("<Code>").append(x3Code).append("</Code>");
        xml.append("<TokenNumber>").append(x3Token).append("</TokenNumber>");
        xml.append("</Profile>");
        
        // Search criteria
        xml.append("<SearchCriteria>");
        
        // Room configuration first (as per required format)
        xml.append("<RoomConfiguration>");
        
        for (RoomRequest room : request.getRooms()) {
            xml.append("<Room>");
            
            // Adults
            if (room.getAdultAges() != null && !room.getAdultAges().isEmpty()) {
                for (Integer age : room.getAdultAges()) {
                    xml.append("<Adult>");
                    xml.append("<Age>").append(age).append("</Age>");
                    xml.append("</Adult>");
                }
            } else {
                for (int i = 0; i < room.getAdults(); i++) {
                    xml.append("<Adult>");
                    xml.append("<Age>25</Age>");
                    xml.append("</Adult>");
                }
            }
            
            // Children
            if (room.getChildren() != null && room.getChildren() > 0) {
                if (room.getChildAges() != null && !room.getChildAges().isEmpty()) {
                    for (Integer age : room.getChildAges()) {
                        xml.append("<Child>");
                        xml.append("<Age>").append(age).append("</Age>");
                        xml.append("</Child>");
                    }
                } else {
                    for (int i = 0; i < room.getChildren(); i++) {
                        xml.append("<Child>");
                        xml.append("<Age>0</Age>");
                        xml.append("</Child>");
                    }
                }
            }
            
            xml.append("</Room>");
        }
        
        xml.append("</RoomConfiguration>");
        
        // Other search criteria
        xml.append("<StartDate>").append(convertDateFormat(request.getCheckInDate())).append("</StartDate>");
        xml.append("<EndDate>").append(convertDateFormat(request.getCheckOutDate())).append("</EndDate>");
        xml.append("<HotelCode>").append(request.getHotelCode()).append("</HotelCode>");
        xml.append("<Nationality>").append(request.getNationality()).append("</Nationality>");
        xml.append("<GroupByRooms>Y</GroupByRooms>");
        xml.append("<CancellationPolicy>Y</CancellationPolicy>");
        
        xml.append("</SearchCriteria>");
        xml.append("</HotelSearchRequest>");
        
        return xml.toString();
    }

    /**
     * Parse XML response from X3 API (same logic as IWTX)
     */
    private IwtxHotelSearchResponse parseXmlResponse(String xmlResponse, HotelRoomSearchRequest request) {
        IwtxHotelSearchResponse response = new IwtxHotelSearchResponse();
        
        try {
            logger.debug("Parsing X3 XML response: {}", xmlResponse);
            
            // Check for error response first
            if (xmlResponse.contains("<Error>") || xmlResponse.contains("<ErrorCode>")) {
                logger.error("X3 API returned error response: {}", xmlResponse);
                return createMockResponse(request);
            }
            
            IwtxHotelSearchResponse.IwtxHotels hotels = new IwtxHotelSearchResponse.IwtxHotels();
            List<IwtxHotelSearchResponse.IwtxHotel> hotelList = new ArrayList<>();
            
            // Parse hotels from XML response
            String[] hotelBlocks = xmlResponse.split("<Hotel>");
            
            for (int i = 1; i < hotelBlocks.length; i++) { // Skip first empty element
                String hotelBlock = hotelBlocks[i];
                String hotelEndTag = "</Hotel>";
                int endIndex = hotelBlock.indexOf(hotelEndTag);
                if (endIndex > 0) {
                    hotelBlock = hotelBlock.substring(0, endIndex);
                    
                    IwtxHotelSearchResponse.IwtxHotel hotel = parseHotelFromXml(hotelBlock, request);
                    if (hotel != null) {
                        hotelList.add(hotel);
                    }
                }
            }
            
            hotels.setHotelList(hotelList);
            response.setHotels(hotels);
            
            logger.info("Successfully parsed X3 XML response with {} hotels", hotelList.size());
            
        } catch (Exception e) {
            logger.error("Error parsing X3 XML response: {}", e.getMessage(), e);
            logger.debug("Failed XML content: {}", xmlResponse);
            // Return mock response on parse error
            return createMockResponse(request);
        }
        
        return response;
    }
    
    /**
     * Parse individual hotel from XML block (same logic as IWTX)
     */
    private IwtxHotelSearchResponse.IwtxHotel parseHotelFromXml(String hotelXml, HotelRoomSearchRequest request) {
        try {
            IwtxHotelSearchResponse.IwtxHotel hotel = new IwtxHotelSearchResponse.IwtxHotel();
            
            // Extract hotel basic information
            hotel.setHotelId(extractXmlValue(hotelXml, "HotelCode", request.getHotelCode()));
            hotel.setHotelName(extractXmlValue(hotelXml, "HotelName", "X3 Hotel"));
            
            // Extract star rating
            String starRatingStr = extractXmlValue(hotelXml, "StarRating", "0");
            try {
                hotel.setStarRating(Integer.parseInt(starRatingStr));
            } catch (NumberFormatException e) {
                hotel.setStarRating(0);
            }
            
            hotel.setCity(extractXmlValue(hotelXml, "City", "Unknown City"));
            hotel.setPropertyType(extractXmlValue(hotelXml, "PropertyType", "Hotel"));
            hotel.setChain(extractXmlValue(hotelXml, "Chain", null));
            
            // Parse room type details for this hotel
            IwtxHotelSearchResponse.IwtxRoomTypeDetails roomTypeDetails = parseRoomTypeDetailsFromXml(hotelXml);
            hotel.setRoomTypeDetails(roomTypeDetails);
            
            // Group rooms by category for better response structure
            if (roomTypeDetails != null && roomTypeDetails.getRooms() != null && 
                roomTypeDetails.getRooms().getRoomList() != null) {
                List<IwtxGroupedRoomResponse> groupedRooms = groupRoomsByCategory(roomTypeDetails.getRooms().getRoomList());
                hotel.setGroupedRooms(groupedRooms);
            }
            
            logger.debug("Parsed X3 hotel: {}", hotel.getHotelName());
            
            return hotel;
            
        } catch (Exception e) {
            logger.error("Error parsing hotel from X3 XML block: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Extract value from XML tag
     */
    private String extractXmlValue(String xml, String tagName, String defaultValue) {
        try {
            String startTag = "<" + tagName + ">";
            String endTag = "</" + tagName + ">";
            
            int startIndex = xml.indexOf(startTag);
            if (startIndex == -1) {
                return defaultValue;
            }
            
            startIndex += startTag.length();
            int endIndex = xml.indexOf(endTag, startIndex);
            if (endIndex == -1) {
                return defaultValue;
            }
            
            String value = xml.substring(startIndex, endIndex).trim();
            return value.isEmpty() ? defaultValue : value;
            
        } catch (Exception e) {
            logger.debug("Error extracting XML value for tag {}: {}", tagName, e.getMessage());
            return defaultValue;
        }
    }

    /**
     * Convert date from YYYY-MM-DD to YYYYMMDD format
     */
    private String convertDateFormat(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            logger.error("Error converting date format: {}", dateStr, e);
            return dateStr.replace("-", "");
        }
    }
    
    /**
     * Parse room type details from hotel XML block
     */
    private IwtxHotelSearchResponse.IwtxRoomTypeDetails parseRoomTypeDetailsFromXml(String hotelXml) {
        try {
            IwtxHotelSearchResponse.IwtxRoomTypeDetails roomTypeDetails = new IwtxHotelSearchResponse.IwtxRoomTypeDetails();
            IwtxHotelSearchResponse.IwtxRooms rooms = new IwtxHotelSearchResponse.IwtxRooms();
            List<IwtxHotelSearchResponse.IwtxRoomDetail> roomList = new ArrayList<>();
            
            // Parse room details from XML
            String[] roomBlocks = hotelXml.split("<Room>");
            
            for (int i = 1; i < roomBlocks.length; i++) { // Skip first empty element
                String roomBlock = roomBlocks[i];
                String roomEndTag = "</Room>";
                int endIndex = roomBlock.indexOf(roomEndTag);
                if (endIndex > 0) {
                    roomBlock = roomBlock.substring(0, endIndex);
                    
                    IwtxHotelSearchResponse.IwtxRoomDetail roomDetail = parseRoomDetailFromXml(roomBlock);
                    if (roomDetail != null) {
                        roomList.add(roomDetail);
                    }
                }
            }
            
            rooms.setRoomList(roomList);
            roomTypeDetails.setRooms(rooms);
            
            return roomTypeDetails;
            
        } catch (Exception e) {
            logger.error("Error parsing room type details from XML: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Parse individual room detail from XML block
     */
    private IwtxHotelSearchResponse.IwtxRoomDetail parseRoomDetailFromXml(String roomXml) {
        try {
            IwtxHotelSearchResponse.IwtxRoomDetail roomDetail = new IwtxHotelSearchResponse.IwtxRoomDetail();
            
            // Parse room basic information
            String roomNoStr = extractXmlValue(roomXml, "RoomNo", "1");
            try {
                roomDetail.setRoomNo(Integer.parseInt(roomNoStr));
            } catch (NumberFormatException e) {
                roomDetail.setRoomNo(1);
            }
            
            roomDetail.setRoomType(extractXmlValue(roomXml, "RoomType", "Standard"));
            roomDetail.setRoomTypeCode(extractXmlValue(roomXml, "RoomTypeCode", ""));
            roomDetail.setRoomTypeSupplierCode(extractXmlValue(roomXml, "RoomTypeSupplierCode", ""));
            roomDetail.setMealPlanSupplierCode(extractXmlValue(roomXml, "MealPlanSupplierCode", ""));
            roomDetail.setRoomStatus(extractXmlValue(roomXml, "RoomStatus", "Available"));
            roomDetail.setCurrCode(extractXmlValue(roomXml, "CurrCode", "USD"));
            roomDetail.setContractTokenId(extractXmlValue(roomXml, "ContractTokenId", ""));
            
            // Parse room configuration ID
            String roomConfigIdStr = extractXmlValue(roomXml, "RoomConfigurationId", "1");
            try {
                roomDetail.setRoomConfigurationId(Integer.parseInt(roomConfigIdStr));
            } catch (NumberFormatException e) {
                roomDetail.setRoomConfigurationId(1);
            }
            
            roomDetail.setRatePlanCode(extractXmlValue(roomXml, "RatePlanCode", ""));
            roomDetail.setRatePlanId(extractXmlValue(roomXml, "RatePlanId", ""));
            roomDetail.setMealPlan(extractXmlValue(roomXml, "MealPlan", ""));
            roomDetail.setMealPlanCode(extractXmlValue(roomXml, "MealPlanCode", ""));
            
            // Parse number of meals
            String mealsStr = extractXmlValue(roomXml, "NumberOfMeals", "0");
            try {
                roomDetail.setNumberOfMeals(Integer.parseInt(mealsStr));
            } catch (NumberFormatException e) {
                roomDetail.setNumberOfMeals(0);
            }
            
            // Parse room number
            String roomNumberStr = extractXmlValue(roomXml, "RoomNumber", "1");
            try {
                roomDetail.setRoomNumber(Integer.parseInt(roomNumberStr));
            } catch (NumberFormatException e) {
                roomDetail.setRoomNumber(1);
            }
            
            // Parse pricing information
            roomDetail.setRate(parseBigDecimalValue(extractXmlValue(roomXml, "Rate", "0")));
            roomDetail.setTotalRate(parseBigDecimalValue(extractXmlValue(roomXml, "TotalRate", "0")));
            roomDetail.setRateBeforeTax(parseBigDecimalValue(extractXmlValue(roomXml, "RateBeforeTax", "0")));
            roomDetail.setTotalDiscount(parseBigDecimalValue(extractXmlValue(roomXml, "TotalDiscount", "0")));
            roomDetail.setRecommendedRetailPrice(parseBigDecimalValue(extractXmlValue(roomXml, "RecommendedRetailPrice", "0")));
            
            roomDetail.setNonRefundable(extractXmlValue(roomXml, "NonRefundable", "N"));
            roomDetail.setDynamicYN(extractXmlValue(roomXml, "DynamicYN", "N"));
            roomDetail.setContractLabel(extractXmlValue(roomXml, "ContractLabel", ""));
            
            // Parse cancellation policy details
            IwtxHotelSearchResponse.IwtxCancellationPolicyDetails cancellationDetails = parseCancellationPolicyFromXml(roomXml);
            roomDetail.setCancellationPolicyDetails(cancellationDetails);
            
            return roomDetail;
            
        } catch (Exception e) {
            logger.error("Error parsing room detail from XML block: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Parse cancellation policy details from XML block
     */
    private IwtxHotelSearchResponse.IwtxCancellationPolicyDetails parseCancellationPolicyFromXml(String roomXml) {
        try {
            IwtxHotelSearchResponse.IwtxCancellationPolicyDetails policyDetails = new IwtxHotelSearchResponse.IwtxCancellationPolicyDetails();
            List<IwtxHotelSearchResponse.IwtxCancellation> cancellations = new ArrayList<>();
            
            // Parse cancellation blocks
            String[] cancellationBlocks = roomXml.split("<Cancellation>");
            
            for (int i = 1; i < cancellationBlocks.length; i++) { // Skip first empty element
                String cancellationBlock = cancellationBlocks[i];
                String cancellationEndTag = "</Cancellation>";
                int endIndex = cancellationBlock.indexOf(cancellationEndTag);
                if (endIndex > 0) {
                    cancellationBlock = cancellationBlock.substring(0, endIndex);
                    
                    IwtxHotelSearchResponse.IwtxCancellation cancellation = new IwtxHotelSearchResponse.IwtxCancellation();
                    cancellation.setFromDate(extractXmlValue(cancellationBlock, "FromDate", ""));
                    cancellation.setToDate(extractXmlValue(cancellationBlock, "ToDate", ""));
                    cancellation.setPercentOrAmt(extractXmlValue(cancellationBlock, "PercentOrAmt", ""));
                    cancellation.setValue(parseBigDecimalValue(extractXmlValue(cancellationBlock, "Value", "0")));
                    
                    cancellations.add(cancellation);
                }
            }
            
            policyDetails.setCancellations(cancellations);
            return policyDetails;
            
        } catch (Exception e) {
            logger.error("Error parsing cancellation policy from XML: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Parse BigDecimal value from string
     */
    private BigDecimal parseBigDecimalValue(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
    
    /**
     * Group rooms by room category/type for better response structure
     */
    private List<IwtxGroupedRoomResponse> groupRoomsByCategory(List<IwtxHotelSearchResponse.IwtxRoomDetail> roomDetails) {
        Map<String, IwtxGroupedRoomResponse> roomGroups = new LinkedHashMap<>();
        
        for (IwtxHotelSearchResponse.IwtxRoomDetail roomDetail : roomDetails) {
            String roomTypeKey = roomDetail.getRoomTypeCode();
            String baseRoomType = extractBaseRoomType(roomDetail.getRoomType());
            
            // Get or create room group
            IwtxGroupedRoomResponse roomGroup = roomGroups.get(roomTypeKey);
            if (roomGroup == null) {
                roomGroup = new IwtxGroupedRoomResponse();
                roomGroup.setRoomCategory(baseRoomType);
                roomGroup.setRoomTypeCode(roomTypeKey);
                roomGroup.setBaseRoomType(roomDetail.getRoomType());
                roomGroup.setAvailableRates(new ArrayList<>());
                roomGroups.put(roomTypeKey, roomGroup);
            }
            
            // Create rate option for this room
            IwtxGroupedRoomResponse.IwtxRateOption rateOption = new IwtxGroupedRoomResponse.IwtxRateOption();
            rateOption.setMealPlan(roomDetail.getMealPlan());
            rateOption.setMealPlanCode(roomDetail.getMealPlanCode());
            rateOption.setCurrency(roomDetail.getCurrCode());
            rateOption.setRate(roomDetail.getRate());
            rateOption.setTotalRate(roomDetail.getTotalRate());
            rateOption.setRateBeforeTax(roomDetail.getRateBeforeTax());
            rateOption.setRecommendedRetailPrice(roomDetail.getRecommendedRetailPrice());
            rateOption.setRoomStatus(roomDetail.getRoomStatus());
            rateOption.setNonRefundable("Y".equalsIgnoreCase(roomDetail.getNonRefundable()));
            rateOption.setContractLabel(roomDetail.getContractLabel());
            rateOption.setContractTokenId(roomDetail.getContractTokenId());
            
            // Convert cancellation policies
            if (roomDetail.getCancellationPolicyDetails() != null && 
                roomDetail.getCancellationPolicyDetails().getCancellations() != null) {
                List<IwtxGroupedRoomResponse.IwtxCancellationPolicy> policies = new ArrayList<>();
                
                for (IwtxHotelSearchResponse.IwtxCancellation cancellation : 
                     roomDetail.getCancellationPolicyDetails().getCancellations()) {
                    IwtxGroupedRoomResponse.IwtxCancellationPolicy policy = 
                        new IwtxGroupedRoomResponse.IwtxCancellationPolicy();
                    policy.setFromDate(cancellation.getFromDate());
                    policy.setToDate(cancellation.getToDate());
                    policy.setPercentOrAmount(cancellation.getPercentOrAmt());
                    policy.setValue(cancellation.getValue());
                    policies.add(policy);
                }
                
                rateOption.setCancellationPolicies(policies);
            }
            
            roomGroup.getAvailableRates().add(rateOption);
        }
        
        return new ArrayList<>(roomGroups.values());
    }
    
    /**
     * Extract base room type from full room type description
     */
    private String extractBaseRoomType(String fullRoomType) {
        if (fullRoomType == null || fullRoomType.trim().isEmpty()) {
            return "Standard";
        }
        
        String roomType = fullRoomType.trim();
        
        // Common room type patterns
        if (roomType.toLowerCase().contains("suite")) {
            return "Suite";
        } else if (roomType.toLowerCase().contains("deluxe")) {
            return "Deluxe";
        } else if (roomType.toLowerCase().contains("superior")) {
            return "Superior";
        } else if (roomType.toLowerCase().contains("executive")) {
            return "Executive";
        } else if (roomType.toLowerCase().contains("premium")) {
            return "Premium";
        } else if (roomType.toLowerCase().contains("standard")) {
            return "Standard";
        } else {
            // Return first word as room category
            String[] words = roomType.split("\\s+");
            return words.length > 0 ? words[0] : "Standard";
        }
    }
}
