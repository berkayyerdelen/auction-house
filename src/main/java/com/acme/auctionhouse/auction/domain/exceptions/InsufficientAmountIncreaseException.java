package com.acme.auctionhouse.auction.domain.exceptions;

public class InsufficientAmountIncreaseException extends Exception {
    public InsufficientAmountIncreaseException(String message) {
        super(message);
    }
}
