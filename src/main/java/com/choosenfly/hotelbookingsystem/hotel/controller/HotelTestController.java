package com.choosenfly.hotelbookingsystem.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choosenfly.hotelbookingsystem.inventory.entities.Hotel;
import com.choosenfly.hotelbookingsystem.inventory.repository.HotelSearchRepository;

@RestController
@RequestMapping("/test")
public class HotelTestController {

    @Autowired
    private HotelSearchRepository hotelSearchRepository;

    @GetMapping("/hotels/all")
    public ResponseEntity<?> getAllActiveHotels() {
        try {
            List<Hotel> hotels = hotelSearchRepository.findAllActiveHotels();
            System.out.println("TEST: Found " + hotels.size() + " active hotels");
            
            for (Hotel hotel : hotels) {
                System.out.println("Hotel ID: " + hotel.getHotelId() + 
                                 ", Name: " + hotel.getHotelName() + 
                                 ", Country: " + (hotel.getCountry() != null ? hotel.getCountry().getId() : "null") +
                                 ", Place: " + (hotel.getPlace() != null ? hotel.getPlace().getId() : "null") +
                                 ", IsDeleted: " + hotel.getIsDeleted());
            }
            
            return ResponseEntity.ok("Found " + hotels.size() + " hotels. Check console for details.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/hotels/search")
    public ResponseEntity<?> searchHotels(@RequestParam Long countryId, @RequestParam Long cityId) {
        try {
            System.out.println("TEST: Searching for hotels with countryId=" + countryId + ", cityId=" + cityId);
            
            List<Hotel> hotels = hotelSearchRepository.findHotelsByCountryAndCity(countryId, cityId);
            System.out.println("TEST: Found " + hotels.size() + " hotels matching criteria");
            
            for (Hotel hotel : hotels) {
                System.out.println("Matching Hotel - ID: " + hotel.getHotelId() + 
                                 ", Name: " + hotel.getHotelName() + 
                                 ", Country: " + (hotel.getCountry() != null ? hotel.getCountry().getId() : "null") +
                                 ", Place: " + (hotel.getPlace() != null ? hotel.getPlace().getId() : "null"));
            }
            
            return ResponseEntity.ok("Found " + hotels.size() + " hotels for countryId=" + countryId + ", cityId=" + cityId + ". Check console for details.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
