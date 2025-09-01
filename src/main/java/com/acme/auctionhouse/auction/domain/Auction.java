package com.acme.auctionhouse.auction.domain;

import com.acme.auctionhouse.auction.domain.exceptions.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Auction {

    @EmbeddedId
    private AccountId id;
    private String itemName;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "starting_bid_amount"))
    })
    private Money startingBid;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "current_bid_amount"))
    })
    private Money currentBid;

    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "max_bid_amount"))
    })
    private Money maxBid;
    private Instant startTime;
    private Instant endTime;

    public Auction() {
        this.id = new AccountId(UUID.randomUUID());
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {

        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        this.itemName = itemName;
    }

    public Instant getStartTime() {
        return startTime;
    }


    public Instant getEndTime() {
        return endTime;
    }

    public void scheduleAuctionPeriod(Instant startTime, Instant endTime) throws DateInPastException, DateIsNullException {
        validateScheduleAuctionPeriod(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void configureAuctionBidLimits(BigDecimal startingBid, BigDecimal maxBid) throws AmountNegativeException {
        validateConfigureAuctionBidLimits(startingBid, maxBid);
        this.startingBid = Money.createMoney(startingBid);
        this.maxBid = Money.createMoney(maxBid);
    }


    public void placeCurrentBid(BigDecimal newAmount) throws InsufficientAmountIncreaseException, AmountNegativeException, BidExceedsLimitException {

        if (this.currentBid != null) {
            validateBid(newAmount);
            this.currentBid = Money.createMoney(newAmount);

        } else {
            this.currentBid = Money.createMoney(this.startingBid.getAmount());
        }

    }

    public Money getStartingBid() {
        return startingBid;
    }

    public Money getMaxBid() {
        return maxBid;
    }


    public Money getCurrentBid() {
        return currentBid;
    }

    private void validateBid(BigDecimal bidAmount) throws InsufficientAmountIncreaseException, BidExceedsLimitException {

        if (currentBid != null && bidAmount.compareTo(currentBid.getAmount()) <= 0) {
            throw new InsufficientAmountIncreaseException("Bid must exceed current bid amount");
        }

        if (bidAmount.compareTo(maxBid.getAmount()) > 0) {
            throw new BidExceedsLimitException("Bid cannot exceed the maximum bid limit");
        }
    }

    private void validateConfigureAuctionBidLimits(BigDecimal startingBid, BigDecimal maxBid) throws AmountNegativeException {
        if (startingBid == null || maxBid == null) {
            throw new IllegalArgumentException("Starting bid and maximum bid cannot be null");
        }

        if (startingBid.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountNegativeException("Starting bid cannot be negative");
        }

        if (maxBid.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountNegativeException("Maximum bid cannot be negative");
        }

        if (maxBid.compareTo(startingBid) < 0) {
            throw new IllegalArgumentException("Maximum bid cannot be less than starting bid");
        }
    }

    private void validateScheduleAuctionPeriod(Instant startTime, Instant endTime) {
        if (startTime == null || endTime == null) {
            throw new DateIsNullException("Start and end times cannot be null");
        }
        if (startTime.isBefore(Instant.now()) || endTime.isBefore(Instant.now())) {
            throw new DateInPastException("Auction times cannot be in the past");
        }
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
    }

    public AccountId getAuctionId() {
        return id;
    }
}
