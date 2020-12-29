CREATE TABLE auction_bids (
    bidID UUID primary key,
    bidAuctionID VARCHAR(15) NOT NULL,
    bidOwnerID VARCHAR(12) NOT NULL,
    bidAmount NUMERIC(10, 2) NOT NULL,
    bidDateTime timestamp NOT NULL,

    FOREIGN KEY (bidOwnerID) REFERENCES ibay_user (userid) ON DELETE SET NULL,
    FOREIGN KEY (bidAuctionID) REFERENCES auctions (auctionID) ON DELETE CASCADE

)