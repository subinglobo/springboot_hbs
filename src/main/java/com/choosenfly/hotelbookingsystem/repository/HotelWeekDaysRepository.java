package com.choosenfly.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.choosenfly.hotelbookingsystem.entities.hotel.HotelWeekDays;

@Repository
public interface HotelWeekDaysRepository extends JpaRepository<HotelWeekDays, Long> {

}
