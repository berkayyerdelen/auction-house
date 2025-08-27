package com.acme.auctionhouse.bidding;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bids")
public class BiddingController {
    private final BiddingService biddingService;

    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @PostMapping
    public void createBid(@RequestBody Bidding bidding) {
         biddingService.placeBid(bidding);
    }


    @GetMapping("/{auctionId}/score-board")
    public ResponseEntity<List<Bidding>> getScoreBoard(@PathVariable UUID auctionId) {
        List<Bidding> scoreBoard = biddingService.getScoreBoard(auctionId);
        return ResponseEntity.ok(scoreBoard);
    }
 
}
