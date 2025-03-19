package com.choosenfly.hotelbookingsystem.repository.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelWeekDays;

@Repository
public interface HotelWeekDaysRepository extends JpaRepository<HotelWeekDays, Long> {

}
