package com.choosenfly.hotelbookingsystem.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.entities.BlockCheckInAndCheckOut;

public interface BlockCheckinCheckoutRepositoryInterface extends JpaRepository<BlockCheckInAndCheckOut, Long> {

}
