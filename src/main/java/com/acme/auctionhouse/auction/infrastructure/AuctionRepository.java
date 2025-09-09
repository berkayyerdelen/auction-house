package com.acme.auctionhouse.auction.infrastructure;

import com.acme.auctionhouse.auction.domain.AccountId;
import com.acme.auctionhouse.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, AccountId> {

}
