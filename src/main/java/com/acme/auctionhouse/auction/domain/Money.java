package com.acme.auctionhouse.auction.domain;

import com.acme.auctionhouse.auction.domain.exceptions.AmountNegativeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Money {

    @Column(name = "amount")
    private BigDecimal amount;
    
    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money() {}

    public static Money createMoney(BigDecimal amount) throws AmountNegativeException {
        validateAmountNotNegative(amount);
        return new Money(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    private static void validateAmountNotNegative(BigDecimal amount) throws AmountNegativeException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AmountNegativeException("Amount cannot be negative");
        }
    }
}

