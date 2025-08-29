package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.RoomRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.HotelRoomSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelRoomSearchController.class)
public class HotelRoomSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelRoomSearchService hotelRoomSearchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/hotel-rooms/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hotel Room Search Service is running"));
    }

    @Test
    public void testSearchHotelRooms_Success() throws Exception {
        // Prepare test data
        HotelRoomSearchRequest request = createValidRequest();
        HotelRoomSearchResponse mockResponse = HotelRoomSearchResponse.success(Arrays.asList());

        // Mock service response
        when(hotelRoomSearchService.searchHotelRooms(any(HotelRoomSearchRequest.class)))
                .thenReturn(mockResponse);

        // Perform request
        mockMvc.perform(post("/api/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void testSearchHotelRooms_InvalidApiId() throws Exception {
        HotelRoomSearchRequest request = createValidRequest();
        request.setApiId(99); // Invalid API ID

        mockMvc.perform(post("/api/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("API not implemented. Only API ID 11 (IWTX) is currently supported."));
    }

    @Test
    public void testSearchHotelRooms_MissingApiId() throws Exception {
        HotelRoomSearchRequest request = createValidRequest();
        request.setApiId(null);

        mockMvc.perform(post("/api/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("API ID is required"));
    }

    private HotelRoomSearchRequest createValidRequest() {
        HotelRoomSearchRequest request = new HotelRoomSearchRequest();
        request.setCheckInDate("2024-12-01");
        request.setCheckOutDate("2024-12-03");
        request.setHotelCode("HTL001");
        request.setNationality("US");
        request.setAgentId("AGENT123");
        request.setApiId(11);

        RoomRequest room = new RoomRequest();
        room.setAdults(2);
        room.setChildren(0);
        room.setAdultAges(Arrays.asList(30, 28));

        request.setRooms(Arrays.asList(room));
        return request;
    }
}
