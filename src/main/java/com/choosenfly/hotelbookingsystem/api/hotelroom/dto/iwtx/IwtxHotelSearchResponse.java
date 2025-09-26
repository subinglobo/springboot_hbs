package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * IWTX XML response DTO for hotel search
 */
@JacksonXmlRootElement(localName = "HotelSearchResponse")
public class IwtxHotelSearchResponse {

    @JacksonXmlProperty(localName = "Hotels")
    private IwtxHotels hotels;

    public IwtxHotelSearchResponse() {}

    public IwtxHotels getHotels() {
        return hotels;
    }

    public void setHotels(IwtxHotels hotels) {
        this.hotels = hotels;
    }

    public static class IwtxHotels {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Hotel")
        private List<IwtxHotel> hotelList;

        public List<IwtxHotel> getHotelList() {
            return hotelList;
        }

        public void setHotelList(List<IwtxHotel> hotelList) {
            this.hotelList = hotelList;
        }
    }

    public static class IwtxHotel {
        @JacksonXmlProperty(localName = "SourceId")
        private String sourceId;

        @JacksonXmlProperty(localName = "HotelId")
        private String hotelId;

        @JacksonXmlProperty(localName = "HotelName")
        private String hotelName;

        @JacksonXmlProperty(localName = "PreferredStatus")
        private String preferredStatus;

        @JacksonXmlProperty(localName = "PropertyType")
        private String propertyType;

        @JacksonXmlProperty(localName = "StarRating")
        private Integer starRating;

        @JacksonXmlProperty(localName = "GeoLocation")
        private IwtxGeoLocation geoLocation;

        @JacksonXmlProperty(localName = "Chain")
        private String chain;

        @JacksonXmlProperty(localName = "HotelCode")
        private String hotelCode;

        @JacksonXmlProperty(localName = "TimeZone")
        private String timeZone;

        @JacksonXmlProperty(localName = "City")
        private String city;

        @JacksonXmlProperty(localName = "RoomTypeDetails")
        private IwtxRoomTypeDetails roomTypeDetails;
        
        // Grouped rooms for better response structure
        private List<IwtxGroupedRoomResponse> groupedRooms;

        // Getters and Setters
        public String getSourceId() { return sourceId; }
        public void setSourceId(String sourceId) { this.sourceId = sourceId; }

        public String getHotelId() { return hotelId; }
        public void setHotelId(String hotelId) { this.hotelId = hotelId; }

        public String getHotelName() { return hotelName; }
        public void setHotelName(String hotelName) { this.hotelName = hotelName; }

        public String getPreferredStatus() { return preferredStatus; }
        public void setPreferredStatus(String preferredStatus) { this.preferredStatus = preferredStatus; }

        public String getPropertyType() { return propertyType; }
        public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

        public Integer getStarRating() { return starRating; }
        public void setStarRating(Integer starRating) { this.starRating = starRating; }

        public IwtxGeoLocation getGeoLocation() { return geoLocation; }
        public void setGeoLocation(IwtxGeoLocation geoLocation) { this.geoLocation = geoLocation; }

        public String getChain() { return chain; }
        public void setChain(String chain) { this.chain = chain; }

        public String getHotelCode() { return hotelCode; }
        public void setHotelCode(String hotelCode) { this.hotelCode = hotelCode; }

        public String getTimeZone() { return timeZone; }
        public void setTimeZone(String timeZone) { this.timeZone = timeZone; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public IwtxRoomTypeDetails getRoomTypeDetails() { return roomTypeDetails; }
        public void setRoomTypeDetails(IwtxRoomTypeDetails roomTypeDetails) { this.roomTypeDetails = roomTypeDetails; }
        
        public List<IwtxGroupedRoomResponse> getGroupedRooms() { return groupedRooms; }
        public void setGroupedRooms(List<IwtxGroupedRoomResponse> groupedRooms) { this.groupedRooms = groupedRooms; }
    }

    public static class IwtxGeoLocation {
        @JacksonXmlProperty(localName = "Longitude")
        private BigDecimal longitude;

        @JacksonXmlProperty(localName = "Latitude")
        private BigDecimal latitude;

        public BigDecimal getLongitude() { return longitude; }
        public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

        public BigDecimal getLatitude() { return latitude; }
        public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    }

    public static class IwtxRoomTypeDetails {
        @JacksonXmlProperty(localName = "Rooms")
        private IwtxRooms rooms;

        public IwtxRooms getRooms() { return rooms; }
        public void setRooms(IwtxRooms rooms) { this.rooms = rooms; }
    }

    public static class IwtxRooms {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Room")
        private List<IwtxRoomDetail> roomList;

        public List<IwtxRoomDetail> getRoomList() { return roomList; }
        public void setRoomList(List<IwtxRoomDetail> roomList) { this.roomList = roomList; }
    }

    public static class IwtxRoomDetail {
        @JacksonXmlProperty(localName = "RoomNo")
        private Integer roomNo;

        @JacksonXmlProperty(localName = "RoomType")
        private String roomType;

        @JacksonXmlProperty(localName = "RoomTypeCode")
        private String roomTypeCode;

        @JacksonXmlProperty(localName = "RoomTypeSupplierCode")
        private String roomTypeSupplierCode;

        @JacksonXmlProperty(localName = "MealPlanSupplierCode")
        private String mealPlanSupplierCode;

        @JacksonXmlProperty(localName = "RoomStatus")
        private String roomStatus;

        @JacksonXmlProperty(localName = "CurrCode")
        private String currCode;

        @JacksonXmlProperty(localName = "ContractTokenId")
        private String contractTokenId;

        @JacksonXmlProperty(localName = "RoomConfigurationId")
        private Integer roomConfigurationId;

        @JacksonXmlProperty(localName = "RatePlanCode")
        private String ratePlanCode;

        @JacksonXmlProperty(localName = "RatePlanId")
        private String ratePlanId;

        @JacksonXmlProperty(localName = "MealPlan")
        private String mealPlan;

        @JacksonXmlProperty(localName = "MealPlanCode")
        private String mealPlanCode;

        @JacksonXmlProperty(localName = "NumberOfMeals")
        private Integer numberOfMeals;

        @JacksonXmlProperty(localName = "RoomNumber")
        private Integer roomNumber;

        @JacksonXmlProperty(localName = "Rate")
        private BigDecimal rate;

        @JacksonXmlProperty(localName = "TotalRate")
        private BigDecimal totalRate;

        @JacksonXmlProperty(localName = "RateBeforeTax")
        private BigDecimal rateBeforeTax;

        @JacksonXmlProperty(localName = "TotalDiscount")
        private BigDecimal totalDiscount;

        @JacksonXmlProperty(localName = "RecommendedRetailPrice")
        private BigDecimal recommendedRetailPrice;

        @JacksonXmlProperty(localName = "NonRefundable")
        private String nonRefundable;

        @JacksonXmlProperty(localName = "DynamicYN")
        private String dynamicYN;

        @JacksonXmlProperty(localName = "ContractLabel")
        private String contractLabel;

        @JacksonXmlProperty(localName = "CancellationPolicyDetails")
        private IwtxCancellationPolicyDetails cancellationPolicyDetails;

        // Getters and Setters
        public Integer getRoomNo() { return roomNo; }
        public void setRoomNo(Integer roomNo) { this.roomNo = roomNo; }

        public String getRoomType() { return roomType; }
        public void setRoomType(String roomType) { this.roomType = roomType; }

        public String getRoomTypeCode() { return roomTypeCode; }
        public void setRoomTypeCode(String roomTypeCode) { this.roomTypeCode = roomTypeCode; }

        public String getRoomTypeSupplierCode() { return roomTypeSupplierCode; }
        public void setRoomTypeSupplierCode(String roomTypeSupplierCode) { this.roomTypeSupplierCode = roomTypeSupplierCode; }

        public String getMealPlanSupplierCode() { return mealPlanSupplierCode; }
        public void setMealPlanSupplierCode(String mealPlanSupplierCode) { this.mealPlanSupplierCode = mealPlanSupplierCode; }

        public String getRoomStatus() { return roomStatus; }
        public void setRoomStatus(String roomStatus) { this.roomStatus = roomStatus; }

        public String getCurrCode() { return currCode; }
        public void setCurrCode(String currCode) { this.currCode = currCode; }

        public String getContractTokenId() { return contractTokenId; }
        public void setContractTokenId(String contractTokenId) { this.contractTokenId = contractTokenId; }

        public Integer getRoomConfigurationId() { return roomConfigurationId; }
        public void setRoomConfigurationId(Integer roomConfigurationId) { this.roomConfigurationId = roomConfigurationId; }

        public String getRatePlanCode() { return ratePlanCode; }
        public void setRatePlanCode(String ratePlanCode) { this.ratePlanCode = ratePlanCode; }

        public String getRatePlanId() { return ratePlanId; }
        public void setRatePlanId(String ratePlanId) { this.ratePlanId = ratePlanId; }

        public String getMealPlan() { return mealPlan; }
        public void setMealPlan(String mealPlan) { this.mealPlan = mealPlan; }

        public String getMealPlanCode() { return mealPlanCode; }
        public void setMealPlanCode(String mealPlanCode) { this.mealPlanCode = mealPlanCode; }

        public Integer getNumberOfMeals() { return numberOfMeals; }
        public void setNumberOfMeals(Integer numberOfMeals) { this.numberOfMeals = numberOfMeals; }

        public Integer getRoomNumber() { return roomNumber; }
        public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

        public BigDecimal getRate() { return rate; }
        public void setRate(BigDecimal rate) { this.rate = rate; }

        public BigDecimal getTotalRate() { return totalRate; }
        public void setTotalRate(BigDecimal totalRate) { this.totalRate = totalRate; }

        public BigDecimal getRateBeforeTax() { return rateBeforeTax; }
        public void setRateBeforeTax(BigDecimal rateBeforeTax) { this.rateBeforeTax = rateBeforeTax; }

        public BigDecimal getTotalDiscount() { return totalDiscount; }
        public void setTotalDiscount(BigDecimal totalDiscount) { this.totalDiscount = totalDiscount; }

        public BigDecimal getRecommendedRetailPrice() { return recommendedRetailPrice; }
        public void setRecommendedRetailPrice(BigDecimal recommendedRetailPrice) { this.recommendedRetailPrice = recommendedRetailPrice; }

        public String getNonRefundable() { return nonRefundable; }
        public void setNonRefundable(String nonRefundable) { this.nonRefundable = nonRefundable; }

        public String getDynamicYN() { return dynamicYN; }
        public void setDynamicYN(String dynamicYN) { this.dynamicYN = dynamicYN; }

        public String getContractLabel() { return contractLabel; }
        public void setContractLabel(String contractLabel) { this.contractLabel = contractLabel; }

        public IwtxCancellationPolicyDetails getCancellationPolicyDetails() { return cancellationPolicyDetails; }
        public void setCancellationPolicyDetails(IwtxCancellationPolicyDetails cancellationPolicyDetails) { this.cancellationPolicyDetails = cancellationPolicyDetails; }
    }

    public static class IwtxCancellationPolicyDetails {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Cancellation")
        private List<IwtxCancellation> cancellations;

        public List<IwtxCancellation> getCancellations() { return cancellations; }
        public void setCancellations(List<IwtxCancellation> cancellations) { this.cancellations = cancellations; }
    }

    public static class IwtxCancellation {
        @JacksonXmlProperty(localName = "FromDate")
        private String fromDate;

        @JacksonXmlProperty(localName = "ToDate")
        private String toDate;

        @JacksonXmlProperty(localName = "PercentOrAmt")
        private String percentOrAmt;

        @JacksonXmlProperty(localName = "Value")
        private BigDecimal value;

        public String getFromDate() { return fromDate; }
        public void setFromDate(String fromDate) { this.fromDate = fromDate; }

        public String getToDate() { return toDate; }
        public void setToDate(String toDate) { this.toDate = toDate; }

        public String getPercentOrAmt() { return percentOrAmt; }
        public void setPercentOrAmt(String percentOrAmt) { this.percentOrAmt = percentOrAmt; }

        public BigDecimal getValue() { return value; }
        public void setValue(BigDecimal value) { this.value = value; }
    }
}
