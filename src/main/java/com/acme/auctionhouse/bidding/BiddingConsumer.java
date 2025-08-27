package com.acme.auctionhouse.bidding;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BiddingConsumer {

    private final BiddingService biddingService;
    private final RedisTemplate<String, String> redisTemplate;
  
    public BiddingConsumer(BiddingService biddingService, RedisTemplate<String, String> redisTemplate) {
        this.biddingService = biddingService;
        this.redisTemplate = redisTemplate;
    }

    @KafkaListener(topics = "bids", groupId = "auction-group")
    public void consume(Bidding bidding) {
        biddingService.save(bidding);

        String auctionId = bidding.getAuctionId().toString();
        String bidderId = bidding.getBidUserId().toString();
        BigDecimal bidAmount = bidding.getBidPrice();

        // Key format: scoreboard:{auctionId}
        String scoreboardKey = "scoreboard:" + auctionId;

        // Add to sorted set with bid amount as score
        redisTemplate.opsForZSet().add(scoreboardKey, bidderId, bidAmount.doubleValue());
    }
}
