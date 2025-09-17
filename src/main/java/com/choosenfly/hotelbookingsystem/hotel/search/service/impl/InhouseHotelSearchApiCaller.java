package com.choosenfly.hotelbookingsystem.hotel.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchRequest;
import com.choosenfly.hotelbookingsystem.hotel.search.dto.HotelSearchResult;
import com.choosenfly.hotelbookingsystem.hotel.search.service.HotelSearchApiCaller;
import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSearchRepository;

@Component
public class InhouseHotelSearchApiCaller implements HotelSearchApiCaller {

    @Autowired
    private HotelSearchRepository hotelSearchRepository;

    private static final Random random = new Random();

    @Override
    public String getApiKey() {
        return "inhouse";
    }

    @Override
    public List<HotelSearchResult> callApi(HotelSearchRequest request) {
        List<HotelSearchResult> results = new ArrayList<>();

        try {
            System.out.println("INHOUSE API: Searching for hotels with countryId=" + request.getDestinationCountryId() + 
                             ", cityId=" + request.getDestinationCityId());
            
            // First, let's test if we can find any hotels at all
            List<Hotel> allHotels = hotelSearchRepository.findAllActiveHotels();
            System.out.println("INHOUSE API: Total active hotels in database: " + allHotels.size());
            
            // Query hotels from database based on country and city
            List<Hotel> hotels = hotelSearchRepository.findHotelsByCountryAndCity(
                Long.valueOf(request.getDestinationCountryId()),
                Long.valueOf(request.getDestinationCityId())
            );
            
            System.out.println("INHOUSE API: Found " + hotels.size() + " hotels matching criteria");

            // Convert Hotel entities to HotelSearchResult DTOs
            for (Hotel hotel : hotels) {
                System.out.println("INHOUSE API: Processing hotel - ID: " + hotel.getHotelId() + 
                                 ", Name: " + hotel.getHotelName() + 
                                 ", Country: " + (hotel.getCountry() != null ? hotel.getCountry().getId() : "null") +
                                 ", Place: " + (hotel.getPlace() != null ? hotel.getPlace().getId() : "null"));
                
                HotelSearchResult result = new HotelSearchResult();
                
                // Map basic hotel information
                result.setHotelCode("INHOUSE-" + hotel.getHotelId());
                result.setHotelName(hotel.getHotelName());
                result.setHotelAddress(hotel.getAddress());
                result.setApiType("INHOUSE");
                
                // Set hotel image (use default if not available)
                if (hotel.getImage360() != null && !hotel.getImage360().trim().isEmpty()) {
                    result.setHotelImage(hotel.getImage360());
                } else {
                    result.setHotelImage("https://b2b.choosenfly.com/assets/details/profilepic/hotel/hoteldefault.jpg");
                }
                
                // Set star rating from hotel category
                if (hotel.getHotelCategory() != null) {
                    // Assuming hotel category has a rating field - adjust based on your entity structure
                    result.setStarRating(generateStarRating(hotel));
                } else {
                    result.setStarRating(3); // Default rating
                }
                
                // Calculate base rate based on hotel and search criteria
                Double baseRate = calculateBaseRate(hotel, request);
                result.setBaseRate(baseRate);
                
                System.out.println("INHOUSE API: Created result - Code: " + result.getHotelCode() + 
                                 ", Name: " + result.getHotelName() + 
                                 ", Rate: " + result.getBaseRate());
                
                results.add(result);
            }

            System.out.println("Inhouse API found " + results.size() + " hotels for city: " + 
                             request.getDestinationCityId() + ", country: " + request.getDestinationCountryId());

        } catch (Exception e) {
            System.err.println("Error in InhouseHotelSearchApiCaller: " + e.getMessage());
            e.printStackTrace();
        }

        return results;
    }

    private Integer generateStarRating(Hotel hotel) {
        // If hotel category has a specific rating system, use it
        // For now, generating based on hotel category or using random for demo
        if (hotel.getHotelCategory() != null) {
            String categoryName = hotel.getHotelCategory().getName();
            if (categoryName != null) {
                categoryName = categoryName.toLowerCase();
                if (categoryName.contains("luxury") || categoryName.contains("5")) {
                    return 5;
                } else if (categoryName.contains("premium") || categoryName.contains("4")) {
                    return 4;
                } else if (categoryName.contains("standard") || categoryName.contains("3")) {
                    return 3;
                } else if (categoryName.contains("budget") || categoryName.contains("2")) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        // Default random rating between 3-5 for demo purposes
        return 3 + random.nextInt(3);
    }

    private Double calculateBaseRate(Hotel hotel, HotelSearchRequest request) {
        // This is a simplified rate calculation for inhouse hotels
        // In a real scenario, you would query contract rates, special rates, etc.
        
        try {
            // Base rate calculation logic
            double baseRate = 100.0; // Starting base rate
            
            // Adjust rate based on hotel category/star rating
            if (hotel.getHotelCategory() != null) {
                String categoryName = hotel.getHotelCategory().getName();
                if (categoryName != null) {
                    categoryName = categoryName.toLowerCase();
                    if (categoryName.contains("luxury") || categoryName.contains("5")) {
                        baseRate = 300.0 + random.nextDouble() * 200.0; // 300-500 AED
                    } else if (categoryName.contains("premium") || categoryName.contains("4")) {
                        baseRate = 200.0 + random.nextDouble() * 100.0; // 200-300 AED
                    } else if (categoryName.contains("standard") || categoryName.contains("3")) {
                        baseRate = 150.0 + random.nextDouble() * 50.0;  // 150-200 AED
                    } else if (categoryName.contains("budget") || categoryName.contains("2")) {
                        baseRate = 100.0 + random.nextDouble() * 50.0;  // 100-150 AED
                    }
                }
            } else {
                // Random rate for hotels without category
                baseRate = 150.0 + random.nextDouble() * 100.0; // 150-250 AED
            }
            
            // Multiply by number of rooms
            int numberOfRooms = Integer.parseInt(request.getNoOfRooms());
            baseRate = baseRate * numberOfRooms;
            
            // Round to 2 decimal places
            return Math.round(baseRate * 100.0) / 100.0;
            
        } catch (Exception e) {
            System.err.println("Error calculating base rate for hotel " + hotel.getHotelId() + ": " + e.getMessage());
            // Return default rate
            return 150.0 * Integer.parseInt(request.getNoOfRooms());
        }
    }
}
