package com.choosenfly.hotelbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(title = "Hotel Booking API", version = "1.0", description = "API for Hotel CRUD")
	)
@SpringBootApplication
@EnableRetry
public class ChoosenflyHotelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoosenflyHotelBookingSystemApplication.class, args);  
	}

}
