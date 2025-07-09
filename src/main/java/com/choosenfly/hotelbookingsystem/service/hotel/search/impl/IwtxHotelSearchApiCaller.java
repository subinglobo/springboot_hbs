package com.choosenfly.hotelbookingsystem.service.hotel.search.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.dto.hotel.search.RoomConfiguration;
import com.choosenfly.hotelbookingsystem.service.hotel.search.HotelSearchApiCaller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Component
public class IwtxHotelSearchApiCaller implements HotelSearchApiCaller {

//    @Autowired
//    private IwtxHotelsRepository iwtxHotelsRepository;
//
//    @Autowired
//    private ConCityMappingRepository conCityMappingRepository;
//
//    @Autowired
//    private ConCountryMappingRepository conCountryMappingRepository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static final String API_URL = "https://api.iwtx.com/hotel/search";
//
//    @Override
//    public List<HotelSearchResult> callApi(HotelSearchRequest request) {
//        List<HotelSearchResult> results = new ArrayList<>();
//
//        ConCityMapping cityMapping = conCityMappingRepository.findByInternalCityId(request.getDestinationCityId());
//        ConCountryMapping countryMapping = conCountryMappingRepository.findByInternalCountryId(request.getDestinationCountryId());
//
//        if (cityMapping == null || countryMapping == null) return results;
//
//        Integer iwtxCityId = cityMapping.getIwtxCityId();
//        Integer iwtxCountryId = countryMapping.getIwtxCountryId();
//
//        List<IwtxHotels> hotels = iwtxHotelsRepository.findByCityIdAndCountryId(iwtxCityId, iwtxCountryId);
//        Set<String> hotelCodes = hotels.stream().map(IwtxHotels::getHotelCode).collect(Collectors.toSet());
//
//        if (hotelCodes.isEmpty()) return results;
//
//        String xmlRequest = buildIwtxSearchXmlRequest(request, hotelCodes);
//        String responseXml = restTemplate.postForObject(API_URL, xmlRequest, String.class);
//
//        try {
//            JAXBContext context = JAXBContext.newInstance(HotelSearchResponseWrapper.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            HotelSearchResponseWrapper wrapper = (HotelSearchResponseWrapper) unmarshaller.unmarshal(new StringReader(responseXml));
//            results.addAll(wrapper.getHotels());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return results;
//    }
//
//    private String buildIwtxSearchXmlRequest(HotelSearchRequest request, Set<String> hotelCodes) {
//        StringWriter writer = new StringWriter();
//        try {
//            HotelSearchRequestXml iwtxRequest = new HotelSearchRequestXml();
//
//            Profile profile = new Profile();
//            profile.setCode("******");
//            profile.setPassword("********");
//            profile.setTokenNumber(UUID.randomUUID().toString());
//            iwtxRequest.setProfile(profile);
//
//            SearchCriteria criteria = new SearchCriteria();
//            criteria.setCancellationPolicy("Y");
//            criteria.setHotelCode(String.join(",", hotelCodes));
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//            criteria.setStartDate(LocalDate.parse(request.getCheckIn()).format(formatter));
//            criteria.setEndDate(LocalDate.parse(request.getCheckOut()).format(formatter));
//            criteria.setNationality(request.getNationalityId());
//
//            RoomConfiguration roomConfig = new RoomConfiguration();
//            for (var room : request.getRoomConfigurations()) {
//                Room roomXml = new Room();
//                room.getAdults().forEach(age -> {
//                    Adult adult = new Adult();
//                    adult.setAge(age);
//                    roomXml.getAdult().add(adult);
//                });
//                roomConfig.getRoom().add(roomXml);
//            }
//            criteria.setRoomConfiguration(roomConfig);
//            iwtxRequest.setSearchCriteria(criteria);
//
//            JAXBContext context = JAXBContext.newInstance(HotelSearchRequestXml.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(iwtxRequest, writer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return writer.toString();
//    }
//
    @Override
    public String getApiKey() {
        return "iwtx";
    }

	@Override
	public List<HotelSearchResult> callApi(HotelSearchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

} 