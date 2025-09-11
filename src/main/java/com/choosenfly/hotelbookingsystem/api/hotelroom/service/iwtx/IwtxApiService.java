package com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.RoomRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxHotelSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxGroupedRoomResponse;
import com.choosenfly.hotelbookingsystem.api.iwtx.exception.IwtxApiException;
import com.choosenfly.hotelbookingsystem.api.iwtx.exception.IwtxNoAvailabilityException;
import com.choosenfly.hotelbookingsystem.api.iwtx.exception.IwtxConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Service for integrating with IWTX API for hotel room search
 * Simplified implementation without XML processing for now
 */
@Service
public class IwtxApiService {

    private static final Logger logger = LoggerFactory.getLogger(IwtxApiService.class);

    // IWTX API configuration
    @Value("${iwtx.api.url}")
    private String iwtxApiUrl;

    @Value("${iwtx.api.password}")
    private String iwtxPassword;

    @Value("${iwtx.api.code}")
    private String iwtxCode;

    @Value("${iwtx.api.token}")
    private String iwtxToken;

    private final RestTemplate restTemplate;

    public IwtxApiService() {
        this.restTemplate = new RestTemplate();
        logger.info("IwtxApiService initialized successfully");
    }
    
    /**
     * Validate IWTX API configuration
     */
    private void validateConfiguration() {
        if (iwtxApiUrl == null || iwtxApiUrl.trim().isEmpty()) {
            throw new IwtxConfigurationException("IWTX API URL is not configured");
        }
        if (iwtxPassword == null || iwtxPassword.trim().isEmpty()) {
            throw new IwtxConfigurationException("IWTX API password is not configured");
        }
        if (iwtxCode == null || iwtxCode.trim().isEmpty()) {
            throw new IwtxConfigurationException("IWTX API code is not configured");
        }
        if (iwtxToken == null || iwtxToken.trim().isEmpty()) {
            throw new IwtxConfigurationException("IWTX API token is not configured");
        }
    }

    /**
     * Search hotel rooms via IWTX API
     * 
     * @param request Hotel room search request
     * @return IWTX hotel search response
     * @throws Exception if API call fails
     */
    public IwtxHotelSearchResponse searchHotelRooms(HotelRoomSearchRequest request) throws Exception {
        logger.info("Calling IWTX API for hotel search with hotel code: {}", request.getHotelCode());

        // Validate configuration first
        validateConfiguration();

        try {
            // Build XML request
            String xmlRequest = buildXmlRequest(request);
            logger.debug("IWTX XML Request: {}", xmlRequest);

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

            // Make API call
            ResponseEntity<String> response = restTemplate.exchange(
                iwtxApiUrl,
                HttpMethod.POST,
                entity,
                String.class
            );

            logger.info("IWTX API Response Status: {}", response.getStatusCode());
            logger.debug("IWTX API Response Body: {}", response.getBody());

            // Parse response
            return parseXmlResponse(response.getBody(), request);

        } catch (Exception e) {
            logger.error("Error calling IWTX API: {}", e.getMessage(), e);
            logger.error("API URL: {}", iwtxApiUrl);
            logger.error("Request details - Hotel Code: {}, Check-in: {}, Check-out: {}", 
                request.getHotelCode(), request.getCheckInDate(), request.getCheckOutDate());
            throw new IwtxApiException("Failed to call IWTX API: " + e.getMessage(), e);
        }
    }


    /**
     * Build XML request for IWTX API
     */
    private String buildXmlRequest(HotelRoomSearchRequest request) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        xml.append("<HotelSearchRequest>");
        
        // Output format
        xml.append("<OutputFormat>XML</OutputFormat>");
        
        // Profile section
        xml.append("<Profile>");
        xml.append("<Password>").append(iwtxPassword).append("</Password>");
        xml.append("<Code>").append(iwtxCode).append("</Code>");
        xml.append("<TokenNumber>").append(iwtxToken).append("</TokenNumber>");
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
     * Parse XML response from IWTX API
     */
    private IwtxHotelSearchResponse parseXmlResponse(String xmlResponse, HotelRoomSearchRequest request) {
        IwtxHotelSearchResponse response = new IwtxHotelSearchResponse();
        
        try {
            logger.debug("Parsing IWTX XML response: {}", xmlResponse);
            
            // Check for error response first
            if (xmlResponse.contains("<Error>") || xmlResponse.contains("<ErrorCode>") || xmlResponse.contains("<ErrorMessage>")) {
                logger.error("IWTX API returned error response: {}", xmlResponse);
                String errorMessage = extractErrorMessageFromXml(xmlResponse);
                throw new IwtxApiException(errorMessage, "IWTX_API_ERROR");
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
            
            logger.info("Successfully parsed XML response with {} hotels", hotelList.size());
            
        } catch (Exception e) {
            logger.error("Error parsing XML response: {}", e.getMessage(), e);
            logger.debug("Failed XML content: {}", xmlResponse);
            throw new IwtxApiException("Failed to parse IWTX API response: " + e.getMessage(), "IWTX_PARSE_ERROR", e);
        }
        
        // Check if no hotels found
        if (response.getHotels() == null || response.getHotels().getHotelList() == null || 
            response.getHotels().getHotelList().isEmpty()) {
            throw new IwtxNoAvailabilityException(request.getHotelCode(), 
                request.getCheckInDate().toString(), request.getCheckOutDate().toString());
        }
        
        return response;
    }
    
    /**
     * Parse individual hotel from XML block
     */
    private IwtxHotelSearchResponse.IwtxHotel parseHotelFromXml(String hotelXml, HotelRoomSearchRequest request) {
        try {
            IwtxHotelSearchResponse.IwtxHotel hotel = new IwtxHotelSearchResponse.IwtxHotel();
            
            // Extract hotel basic information
            hotel.setHotelId(extractXmlValue(hotelXml, "HotelCode", request.getHotelCode()));
            hotel.setHotelName(extractXmlValue(hotelXml, "HotelName", "IWTX Hotel"));
            
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
            
            logger.debug("Parsed hotel: {}", hotel.getHotelName());
            
            return hotel;
            
        } catch (Exception e) {
            logger.error("Error parsing hotel from XML block: {}", e.getMessage(), e);
            return null;
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
        
        // Sort rates within each room group by price
        for (IwtxGroupedRoomResponse roomGroup : roomGroups.values()) {
            roomGroup.getAvailableRates().sort((r1, r2) -> {
                if (r1.getTotalRate() == null && r2.getTotalRate() == null) return 0;
                if (r1.getTotalRate() == null) return 1;
                if (r2.getTotalRate() == null) return -1;
                return r1.getTotalRate().compareTo(r2.getTotalRate());
            });
        }
        
        logger.debug("Grouped {} room details into {} room categories", roomDetails.size(), roomGroups.size());
        
        return new ArrayList<>(roomGroups.values());
    }
    
    /**
     * Extract base room type from full room type description
     */
    private String extractBaseRoomType(String fullRoomType) {
        if (fullRoomType == null || fullRoomType.trim().isEmpty()) {
            return "Standard Room";
        }
        
        // Extract the main room type before the first comma or detailed description
        String baseType = fullRoomType.split(",")[0].trim();
        
        // Clean up common patterns
        baseType = baseType.replaceAll("\\d+\\s+(Bedroom|King|Twin|Queen).*", "$1");
        baseType = baseType.replaceAll("\\s+Villa.*", " Villa");
        
        return baseType;
    }
    
    /**
     * Extract error message from IWTX XML error response
     */
    private String extractErrorMessageFromXml(String xmlResponse) {
        try {
            // Try to extract from <Msg> tag first (similar to X3 format)
            String msgValue = extractXmlValue(xmlResponse, "Msg", null);
            if (msgValue != null && !msgValue.trim().isEmpty()) {
                return msgValue;
            }
            
            // Try to extract from <ErrorMessage> tag
            String errorMessageValue = extractXmlValue(xmlResponse, "ErrorMessage", null);
            if (errorMessageValue != null && !errorMessageValue.trim().isEmpty()) {
                return errorMessageValue;
            }
            
            // Try to extract from <Error> tag
            String errorValue = extractXmlValue(xmlResponse, "Error", null);
            if (errorValue != null && !errorValue.trim().isEmpty()) {
                return errorValue;
            }
            
            // Try to extract from <ErrorCode> tag
            String errorCodeValue = extractXmlValue(xmlResponse, "ErrorCode", null);
            if (errorCodeValue != null && !errorCodeValue.trim().isEmpty()) {
                return "Error Code: " + errorCodeValue;
            }
            
            // If no specific error message found, return a generic message
            return "IWTX API returned an error response";
            
        } catch (Exception e) {
            logger.warn("Failed to extract error message from XML: {}", e.getMessage());
            return "IWTX API returned an error response";
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

}
