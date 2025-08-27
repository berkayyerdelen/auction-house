package com.acme.auctionhouse.bidding;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BiddingService {
    private final KafkaTemplate<String, Bidding> kafkaTemplate;
    private final BiddingRepository biddingRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public BiddingService(KafkaTemplate<String, Bidding> kafkaTemplate,
                          BiddingRepository biddingRepository,
                          RedisTemplate<String, String> redisTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.biddingRepository = biddingRepository;
        this.redisTemplate = redisTemplate;
    }

    public void placeBid(Bidding bidding) {
        kafkaTemplate.send("bids", bidding);
    }

    public void save(Bidding bidding) {
        biddingRepository.save(bidding);
    }

    public List<Bidding> getScoreBoard(UUID auctionId) {
        String scoreboardKey = "scoreboard:" + auctionId;

        // Get all entries from the sorted set, highest score (bid amount) first
        Set<ZSetOperations.TypedTuple<String>> scoreSet = redisTemplate.opsForZSet()
                .reverseRangeWithScores(scoreboardKey, 0, -1);

        List<Bidding> scoreboard = new ArrayList<>();

        if (scoreSet != null) {
            for (ZSetOperations.TypedTuple<String> entry : scoreSet) {
                String bidderId = entry.getValue();
                Double amount = entry.getScore();

                if (bidderId != null && amount != null) {
                    Bidding bidding = new Bidding();
                    bidding.setAuctionId(auctionId);
                    bidding.setBidUserId(UUID.fromString(bidderId));
                    bidding.setBidPrice(BigDecimal.valueOf(amount));
                    scoreboard.add(bidding);
                }
            }
        }

        return scoreboard;
    }
}