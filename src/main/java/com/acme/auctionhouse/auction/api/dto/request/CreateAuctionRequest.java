package com.acme.auctionhouse.auction.api.dto.request;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateAuctionRequest(
        String title,
        BigDecimal startingBid,
        BigDecimal maxBid,
        Instant startTime,
        Instant endTime) {
}
