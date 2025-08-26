package com.acme.auctionhouse.bidding;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BiddingConsumer {

    private final BiddingService biddingService;

    public BiddingConsumer(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @KafkaListener(topics = "bids", groupId = "auction-group")
    public void consume(Bidding bidding) {
        biddingService.placeBid(bidding);
    }
}
