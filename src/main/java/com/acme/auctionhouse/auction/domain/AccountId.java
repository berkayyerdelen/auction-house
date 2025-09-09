package com.acme.auctionhouse.auction.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AccountId implements Serializable {
    private UUID id;

    public AccountId() {
        id = UUID.randomUUID();
    }

    public AccountId(UUID id) {
        this.id = id;
    }
    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(id, accountId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
