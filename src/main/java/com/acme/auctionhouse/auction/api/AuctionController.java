package com.acme.auctionhouse.auction.api;
import com.acme.auctionhouse.auction.api.dto.request.CreateAuctionRequest;
import com.acme.auctionhouse.auction.api.dto.response.AuctionResponse;
import com.acme.auctionhouse.auction.application.AuctionService;
import com.acme.auctionhouse.auction.domain.exceptions.AmountNegativeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionResponse> getAuctionById(@PathVariable UUID id) {
        return new ResponseEntity<>(auctionService.findAuctionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> createAuction(@RequestBody CreateAuctionRequest auction) throws AmountNegativeException {
        return new ResponseEntity<>(auctionService.createAuction(auction), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable UUID id) {
        auctionService.deleteAuction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
