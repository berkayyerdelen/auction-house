package com.acme.auctionhouse.auction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String itemName;
    private BigDecimal startingBid;
    private BigDecimal maxBid;
    private Instant effectiveFrom;
    private Instant effectiveTo;

    // Constructors, getters, and setters
    public Auction() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(BigDecimal startingBid) {
        this.startingBid = startingBid;
    }

    public BigDecimal getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(BigDecimal maxBid) {
        this.maxBid = maxBid;
    }

    public Instant getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Instant effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Instant getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(Instant effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        return Objects.equals(id, auction.id) &&
                Objects.equals(itemName, auction.itemName) &&
                Objects.equals(startingBid, auction.startingBid) &&
                Objects.equals(maxBid, auction.maxBid) &&
                Objects.equals(effectiveFrom, auction.effectiveFrom) &&
                Objects.equals(effectiveTo, auction.effectiveTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, startingBid, maxBid, effectiveFrom, effectiveTo);
    }
}
