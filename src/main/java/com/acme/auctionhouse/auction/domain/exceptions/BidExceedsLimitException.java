package com.acme.auctionhouse.auction.domain.exceptions;

public class BidExceedsLimitException extends Exception {
    public BidExceedsLimitException(String message) {
        super(message);
    }
}



