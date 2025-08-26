package com.acme.auctionhouse.auction;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public UUID createAuction(Auction auction) {
        Auction savedAuction = auctionRepository.save(auction);
        return savedAuction.getId();
    }

    public Auction findAuctionById(UUID id) {
        return auctionRepository.findById(id).orElse(null);
    }
}
