package com.acme.auctionhouse.bidding;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class BiddingService {
    private final KafkaTemplate<String, Bidding> kafkaTemplate;
    private final BiddingRepository biddingRepository;

    public BiddingService(KafkaTemplate<String, Bidding> kafkaTemplate, BiddingRepository biddingRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.biddingRepository = biddingRepository;
    }

    public void placeBid(Bidding bidding) {
        kafkaTemplate.send("bids", bidding);
    }
    public void save(Bidding bidding) {
        biddingRepository.save(bidding);
    }
}
