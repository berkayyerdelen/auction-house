package com.acme.auctionhouse.bidding;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BiddingRepository extends JpaRepository<Bidding, UUID> {
}
