package com.choosenfly.hotelbookingsystem.api.hotelroom.service.iwtx;

import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxGroupedRoomResponse;
import com.choosenfly.hotelbookingsystem.api.hotelroom.dto.iwtx.IwtxHotelSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Test to demonstrate room grouping functionality
 */
@SpringBootTest
public class RoomGroupingTest {

    @Test
    public void testRoomGroupingExample() {
        // Create sample room details similar to your data
        List<IwtxHotelSearchResponse.IwtxRoomDetail> roomDetails = createSampleRoomDetails();
        
        IwtxApiService service = new IwtxApiService();
        
        try {
            // Use reflection to test the private groupRoomsByCategory method
            java.lang.reflect.Method groupMethod = IwtxApiService.class
                .getDeclaredMethod("groupRoomsByCategory", List.class);
            groupMethod.setAccessible(true);
            
            @SuppressWarnings("unchecked")
            List<IwtxGroupedRoomResponse> groupedRooms = (List<IwtxGroupedRoomResponse>) 
                groupMethod.invoke(service, roomDetails);
            
            System.out.println("=== ROOM GROUPING RESULTS ===");
            System.out.println("Original rooms: " + roomDetails.size());
            System.out.println("Grouped into: " + groupedRooms.size() + " categories");
            System.out.println();
            
            for (IwtxGroupedRoomResponse group : groupedRooms) {
                System.out.println("Room Category: " + group.getRoomCategory());
                System.out.println("Room Type Code: " + group.getRoomTypeCode());
                System.out.println("Available Rates: " + group.getAvailableRates().size());
                
                for (IwtxGroupedRoomResponse.IwtxRateOption rate : group.getAvailableRates()) {
                    System.out.println("  - " + rate.getMealPlan() + 
                        " | $" + rate.getTotalRate() + 
                        " | Refundable: " + !rate.isNonRefundable());
                }
                System.out.println();
            }
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private List<IwtxHotelSearchResponse.IwtxRoomDetail> createSampleRoomDetails() {
        List<IwtxHotelSearchResponse.IwtxRoomDetail> rooms = new ArrayList<>();
        
        // Emirates Villa - Room Only (Non-refundable)
        IwtxHotelSearchResponse.IwtxRoomDetail room1 = new IwtxHotelSearchResponse.IwtxRoomDetail();
        room1.setRoomType("Emirates Villa, 2 Bedroom Villa, Bedroom 1: 1 King, Bedroom 2: 2 Twin, Private pool");
        room1.setRoomTypeCode("114743");
        room1.setMealPlan("Room Only");
        room1.setMealPlanCode("28");
        room1.setCurrCode("USD");
        room1.setTotalRate(new BigDecimal("2730.25"));
        room1.setRateBeforeTax(new BigDecimal("2224.21"));
        room1.setRecommendedRetailPrice(new BigDecimal("2972.94"));
        room1.setRoomStatus("OK");
        room1.setNonRefundable("Y");
        room1.setContractLabel("Dynamic Wholesaler Ap Rate, Dynamic Wholesaler Advance Purchase");
        rooms.add(room1);
        
        // Emirates Villa - Tour Operator Rate (Refundable)
        IwtxHotelSearchResponse.IwtxRoomDetail room2 = new IwtxHotelSearchResponse.IwtxRoomDetail();
        room2.setRoomType("Emirates Villa, 2 Bedroom Villa, Bedroom 1: 1 King, Bedroom 2: 2 Twin, Private pool");
        room2.setRoomTypeCode("114743");
        room2.setMealPlan("Room Only");
        room2.setMealPlanCode("28");
        room2.setCurrCode("USD");
        room2.setTotalRate(new BigDecimal("2676.83"));
        room2.setRateBeforeTax(new BigDecimal("2180.60"));
        room2.setRecommendedRetailPrice(new BigDecimal("3497.72"));
        room2.setRoomStatus("OK");
        room2.setNonRefundable("N");
        room2.setContractLabel("Tour Operator Dynamic Rate, Tour Operator Dynamic, breakfast, lunch, dinner, Book or change or cancel only by Tour Operators");
        rooms.add(room2);
        
        // Bedouin Villa King - Room Only
        IwtxHotelSearchResponse.IwtxRoomDetail room3 = new IwtxHotelSearchResponse.IwtxRoomDetail();
        room3.setRoomType("Bedouin Villa King, 1 Bedroom Villa, 1 King, Private pool");
        room3.setRoomTypeCode("131620");
        room3.setMealPlan("Room Only");
        room3.setMealPlanCode("28");
        room3.setCurrCode("USD");
        room3.setTotalRate(new BigDecimal("851.40"));
        room3.setRateBeforeTax(new BigDecimal("690.45"));
        room3.setRecommendedRetailPrice(new BigDecimal("1112.49"));
        room3.setRoomStatus("OK");
        room3.setNonRefundable("N");
        room3.setContractLabel("Tour Operator Dynamic Rate, Tour Operator Dynamic, breakfast, lunch, dinner, Book or change or cancel only by Tour Operators");
        rooms.add(room3);
        
        // Bedouin Villa King - Full Board
        IwtxHotelSearchResponse.IwtxRoomDetail room4 = new IwtxHotelSearchResponse.IwtxRoomDetail();
        room4.setRoomType("Bedouin Villa King, 1 Bedroom Villa, 1 King, Private pool");
        room4.setRoomTypeCode("131620");
        room4.setMealPlan("Full Board");
        room4.setMealPlanCode("14");
        room4.setCurrCode("USD");
        room4.setTotalRate(new BigDecimal("851.40"));
        room4.setRateBeforeTax(new BigDecimal("690.45"));
        room4.setRecommendedRetailPrice(new BigDecimal("1042.96"));
        room4.setRoomStatus("OK");
        room4.setNonRefundable("N");
        room4.setContractLabel("Breakfast, Lunch and Evening Meal for 2 Adults");
        rooms.add(room4);
        
        return rooms;
    }
}
