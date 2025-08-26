package com.acme.auctionhouse.auction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

    private static final Logger log = LoggerFactory.getLogger(AuctionController.class);
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/{id}")
    public Auction getAuctionById(@PathVariable UUID id) {
        return auctionService.findAuctionById(id);
    }

    @PostMapping
    public ResponseEntity<UUID> createAuction(@RequestBody Auction auction) {
        log.info("Creating auction for item: {}", auction.getItemName());
        return new ResponseEntity<>(auctionService.createAuction(auction), HttpStatus.CREATED);
    }

}
