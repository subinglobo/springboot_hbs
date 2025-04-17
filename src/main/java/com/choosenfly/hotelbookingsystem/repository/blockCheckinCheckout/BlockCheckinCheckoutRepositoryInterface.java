package com.choosenfly.hotelbookingsystem.repository.blockCheckinCheckout;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choosenfly.hotelbookingsystem.entities.blockcheckincheckout.BlockCheckInAndCheckOut;

public interface BlockCheckinCheckoutRepositoryInterface extends JpaRepository<BlockCheckInAndCheckOut, Long> {

}
