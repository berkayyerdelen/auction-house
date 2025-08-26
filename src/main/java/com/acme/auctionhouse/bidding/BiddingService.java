package com.acme.auctionhouse.bidding;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BiddingService {
    private final BiddingRepository biddingRepository;
    private final KafkaTemplate<String, Bidding> kafkaTemplate;

    public BiddingService(BiddingRepository biddingRepository, KafkaTemplate<String, Bidding> kafkaTemplate) {
        this.biddingRepository = biddingRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public UUID placeBid(Bidding bidding) {
        kafkaTemplate.send("bids", bidding);
        Bidding savedBidding = biddingRepository.save(bidding);
        return savedBidding.getId();
    }
}
