package com.acme.auctionhouse.auction.application.mapper;

import com.acme.auctionhouse.auction.api.dto.request.CreateAuctionRequest;
import com.acme.auctionhouse.auction.domain.Auction;
import com.acme.auctionhouse.auction.domain.exceptions.AmountNegativeException;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {
    
    public Auction toAuction(CreateAuctionRequest request) throws AmountNegativeException {
        if (request == null) {
            throw new IllegalArgumentException("CreateAuctionRequest cannot be null");
        }
        Auction auction = new Auction();
        auction.setItemName(request.title());
        auction.scheduleAuctionPeriod(request.startTime(), request.endTime());
        auction.configureAuctionBidLimits(request.startingBid(),request.maxBid());
        
        return auction;
    }
}
