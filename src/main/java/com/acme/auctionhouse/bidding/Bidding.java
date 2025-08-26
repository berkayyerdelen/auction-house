package com.acme.auctionhouse.bidding;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Bidding {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant bidTime;
    private BigDecimal bidPrice;
    private UUID auctionId;
    private UUID bidUserId;

    public Bidding() {}

    public Instant getBidTime() {
        return bidTime;
    }

    public void setBidTime(Instant bidTime) {
        this.bidTime = bidTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(UUID auctionId) {
        this.auctionId = auctionId;
    }

    public UUID getBidUserId() {
        return bidUserId;
    }

    public void setBidUserId(UUID bidUserId) {
        this.bidUserId = bidUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bidding bidding = (Bidding) o;
        return Objects.equals(id, bidding.id) && Objects.equals(bidTime, bidding.bidTime) && Objects.equals(bidPrice, bidding.bidPrice) && Objects.equals(auctionId, bidding.auctionId) && Objects.equals(bidUserId, bidding.bidUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bidTime, bidPrice, auctionId, bidUserId);
    }

}





