package com.acme.auctionhouse.auction.application;

import com.acme.auctionhouse.auction.api.dto.request.CreateAuctionRequest;
import com.acme.auctionhouse.auction.api.dto.response.AuctionResponse;
import com.acme.auctionhouse.auction.application.mapper.AuctionMapper;
import com.acme.auctionhouse.auction.domain.AccountId;
import com.acme.auctionhouse.auction.domain.Auction;
import com.acme.auctionhouse.auction.domain.exceptions.AmountNegativeException;
import com.acme.auctionhouse.auction.infrastructure.AuctionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    public AuctionService(AuctionRepository auctionRepository, AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    public UUID createAuction(CreateAuctionRequest request) throws AmountNegativeException {
        Auction auction = auctionMapper.toAuction(request);
        Auction savedAuction = auctionRepository.save(auction);
        return savedAuction.getAuctionId().getId();
    }

    public AuctionResponse findAuctionById(UUID id) {
        var auction = auctionRepository.findById(new AccountId(id)).orElse(null);
        return auction != null ? AuctionResponse.fromDomain(auction) : null;
    }
    
    public void deleteAuction(UUID id) {
        auctionRepository.deleteById(new AccountId(id));
    }
}
