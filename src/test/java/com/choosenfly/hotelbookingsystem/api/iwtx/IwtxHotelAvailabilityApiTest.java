package com.choosenfly.hotelbookingsystem.api.iwtx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.choosenfly.hotelbookingsystem.api.iwtx.dto.availability.request.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.api.iwtx.service.IwtxHotelAvailabilityService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class IwtxHotelAvailabilityApiTest {

    @Autowired
    private IwtxHotelAvailabilityService iwtxHotelAvailabilityService;

    @Test
    public void testCreateSampleRequest() {
        // Test sample request creation
        HotelSearchRequest request = iwtxHotelAvailabilityService.createSampleRequest(
            "101-1256", "20260125", "20260126", "AF");
        
        assertNotNull(request);
        assertNotNull(request.getProfile());
        assertNotNull(request.getSearchCriteria());
        
        assertEquals("101-1256", request.getSearchCriteria().getHotelCode());
        assertEquals("20260125", request.getSearchCriteria().getStartDate());
        assertEquals("20260126", request.getSearchCriteria().getEndDate());
        assertEquals("AF", request.getSearchCriteria().getNationality());
        
        System.out.println("✅ Sample request creation test passed");
        System.out.println("Hotel Code: " + request.getSearchCriteria().getHotelCode());
        System.out.println("Check-in: " + request.getSearchCriteria().getStartDate());
        System.out.println("Check-out: " + request.getSearchCriteria().getEndDate());
    }

    @Test
    public void testServiceConfiguration() {
        // Test that service is properly configured
        assertNotNull(iwtxHotelAvailabilityService);
        
        // Create a sample request to verify configuration
        HotelSearchRequest request = iwtxHotelAvailabilityService.createSampleRequest(
            "TEST-HOTEL", "20260201", "20260203", "US");
        
        assertNotNull(request.getProfile().getPassword());
        assertNotNull(request.getProfile().getCode());
        assertNotNull(request.getProfile().getTokenNumber());
        
        System.out.println("✅ Service configuration test passed");
        System.out.println("Profile configured with credentials");
    }
}
