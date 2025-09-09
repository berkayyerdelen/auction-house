package com.acme.auctionhouse.auction.domain.exceptions;

public class DateIsNullException extends RuntimeException {
    public DateIsNullException(String message) {
        super(message);
    }
}