package com.acme.auctionhouse.auction.domain.exceptions;

public class DateInPastException extends RuntimeException {
    public DateInPastException(String message) {
        super(message);
    }
}