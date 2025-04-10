package com.choosenfly.hotelbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ChoosenflyHotelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoosenflyHotelBookingSystemApplication.class, args);
	}

}
