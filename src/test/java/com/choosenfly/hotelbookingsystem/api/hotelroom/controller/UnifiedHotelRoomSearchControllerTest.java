package com.choosenfly.hotelbookingsystem.api.hotelroom.controller;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.HotelRoomSearchRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.request.RoomRequest;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.response.HotelRoomSearchResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx.IwtxHotelRoomSearchService;
import com.choosenfly.hotelbookingsystem.api.hotelroom.service.x3.X3HotelRoomSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UnifiedHotelRoomSearchController.class)
public class UnifiedHotelRoomSearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IwtxHotelRoomSearchService iwtxHotelRoomSearchService;

    @MockBean
    private X3HotelRoomSearchService x3HotelRoomSearchService;

    @Autowired
    private ObjectMapper objectMapper;

    private HotelRoomSearchRequest validRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Create a valid request for testing
        validRequest = new HotelRoomSearchRequest();
        validRequest.setCheckInDate("2024-12-01");
        validRequest.setCheckOutDate("2024-12-03");
        validRequest.setHotelCode("TEST123");
        validRequest.setNationality("US");
        validRequest.setApiId(12); // IWTX

        RoomRequest room = new RoomRequest();
        room.setAdults(2);
        room.setChildren(0);
        room.setAdultAges(Arrays.asList(30, 25));
        validRequest.setRooms(Collections.singletonList(room));
    }

    @Test
    void testSearchHotelRooms_IWTX_Success() throws Exception {
        // Given
        HotelRoomSearchResponse mockResponse = HotelRoomSearchResponse.success(Collections.emptyList());
        when(iwtxHotelRoomSearchService.searchHotelRooms(any(HotelRoomSearchRequest.class)))
                .thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(post("/api/unified/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testSearchHotelRooms_X3_Success() throws Exception {
        // Given
        validRequest.setApiId(15); // X3
        HotelRoomSearchResponse mockResponse = HotelRoomSearchResponse.success(Collections.emptyList());
        when(x3HotelRoomSearchService.searchHotelRooms(any(HotelRoomSearchRequest.class)))
                .thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(post("/api/unified/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testSearchHotelRooms_InvalidApiId() throws Exception {
        // Given
        validRequest.setApiId(99); // Invalid API ID

        // When & Then
        mockMvc.perform(post("/api/unified/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("Unsupported API ID")));
    }

    @Test
    void testSearchHotelRooms_MissingApiId() throws Exception {
        // Given
        validRequest.setApiId(null);

        // When & Then
        mockMvc.perform(post("/api/unified/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("API ID is required"));
    }

    @Test
    void testSearchHotelRooms_ServiceException() throws Exception {
        // Given
        when(iwtxHotelRoomSearchService.searchHotelRooms(any(HotelRoomSearchRequest.class)))
                .thenThrow(new RuntimeException("Service error"));

        // When & Then
        mockMvc.perform(post("/api/unified/hotel-rooms/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("An unexpected error occurred")));
    }

}
