package com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.inventory.blockCheckinCheckout.entities.BlockCheckInAndCheckOut;

public interface BlockCheckinCheckoutRepositoryInterface extends JpaRepository<BlockCheckInAndCheckOut, Long> {

}
