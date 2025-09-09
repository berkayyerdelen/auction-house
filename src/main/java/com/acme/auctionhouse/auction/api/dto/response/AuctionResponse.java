package com.acme.auctionhouse.auction.api.dto.response;

import com.acme.auctionhouse.auction.domain.Auction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record AuctionResponse(UUID id, String itemName, Instant startingTime, Instant endTime, BigDecimal startingBid, BigDecimal maxBid) {
    public static AuctionResponse fromDomain(Auction auction) {
        return new AuctionResponse(
                auction.getAuctionId().getId(),
                auction.getItemName(),
                auction.getStartTime(),
                auction.getEndTime(),
                auction.getStartingBid().getAmount(),
                auction.getMaxBid().getAmount()
        );
    }
}
