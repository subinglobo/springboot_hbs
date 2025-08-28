package com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

/**
 * IWTX XML request DTO for hotel search
 */
@JacksonXmlRootElement(localName = "HotelSearchRequest")
public class IwtxHotelSearchRequest {

    @JacksonXmlProperty(localName = "OutputFormat")
    private String outputFormat = "XML";

    @JacksonXmlProperty(localName = "Profile")
    private IwtxProfile profile;

    @JacksonXmlProperty(localName = "SearchCriteria")
    private IwtxSearchCriteria searchCriteria;

    // Constructors
    public IwtxHotelSearchRequest() {}

    public IwtxHotelSearchRequest(IwtxProfile profile, IwtxSearchCriteria searchCriteria) {
        this.profile = profile;
        this.searchCriteria = searchCriteria;
    }

    // Getters and Setters
    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public IwtxProfile getProfile() {
        return profile;
    }

    public void setProfile(IwtxProfile profile) {
        this.profile = profile;
    }

    public IwtxSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(IwtxSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Profile credentials for IWTX API
     */
    public static class IwtxProfile {
        @JacksonXmlProperty(localName = "Password")
        private String password;

        @JacksonXmlProperty(localName = "Code")
        private String code;

        @JacksonXmlProperty(localName = "TokenNumber")
        private String tokenNumber;

        public IwtxProfile() {}

        public IwtxProfile(String password, String code, String tokenNumber) {
            this.password = password;
            this.code = code;
            this.tokenNumber = tokenNumber;
        }

        // Getters and Setters
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTokenNumber() {
            return tokenNumber;
        }

        public void setTokenNumber(String tokenNumber) {
            this.tokenNumber = tokenNumber;
        }
    }

    /**
     * Search criteria for hotel search
     */
    public static class IwtxSearchCriteria {
        @JacksonXmlProperty(localName = "RoomConfiguration")
        private IwtxRoomConfiguration roomConfiguration;

        @JacksonXmlProperty(localName = "StartDate")
        private String startDate;

        @JacksonXmlProperty(localName = "EndDate")
        private String endDate;

        @JacksonXmlProperty(localName = "HotelCode")
        private String hotelCode;

        @JacksonXmlProperty(localName = "Nationality")
        private String nationality;

        @JacksonXmlProperty(localName = "GroupByRooms")
        private String groupByRooms = "Y";

        @JacksonXmlProperty(localName = "CancellationPolicy")
        private String cancellationPolicy = "Y";

        public IwtxSearchCriteria() {}

        // Getters and Setters
        public IwtxRoomConfiguration getRoomConfiguration() {
            return roomConfiguration;
        }

        public void setRoomConfiguration(IwtxRoomConfiguration roomConfiguration) {
            this.roomConfiguration = roomConfiguration;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getHotelCode() {
            return hotelCode;
        }

        public void setHotelCode(String hotelCode) {
            this.hotelCode = hotelCode;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getGroupByRooms() {
            return groupByRooms;
        }

        public void setGroupByRooms(String groupByRooms) {
            this.groupByRooms = groupByRooms;
        }

        public String getCancellationPolicy() {
            return cancellationPolicy;
        }

        public void setCancellationPolicy(String cancellationPolicy) {
            this.cancellationPolicy = cancellationPolicy;
        }
    }

    /**
     * Room configuration containing multiple rooms
     */
    public static class IwtxRoomConfiguration {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Room")
        private List<IwtxRoom> rooms;

        public IwtxRoomConfiguration() {}

        public IwtxRoomConfiguration(List<IwtxRoom> rooms) {
            this.rooms = rooms;
        }

        public List<IwtxRoom> getRooms() {
            return rooms;
        }

        public void setRooms(List<IwtxRoom> rooms) {
            this.rooms = rooms;
        }
    }

    /**
     * Individual room with adults and children
     */
    public static class IwtxRoom {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Adult")
        private List<IwtxPerson> adults;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Child")
        private List<IwtxPerson> children;

        public IwtxRoom() {}

        public IwtxRoom(List<IwtxPerson> adults, List<IwtxPerson> children) {
            this.adults = adults;
            this.children = children;
        }

        public List<IwtxPerson> getAdults() {
            return adults;
        }

        public void setAdults(List<IwtxPerson> adults) {
            this.adults = adults;
        }

        public List<IwtxPerson> getChildren() {
            return children;
        }

        public void setChildren(List<IwtxPerson> children) {
            this.children = children;
        }
    }

    /**
     * Person (adult or child) with age
     */
    public static class IwtxPerson {
        @JacksonXmlProperty(localName = "Age")
        private Integer age;

        public IwtxPerson() {}

        public IwtxPerson(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
