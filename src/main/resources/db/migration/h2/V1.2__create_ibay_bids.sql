CREATE TABLE auction_bids (
    bidID UUID primary key,
    auctionID VARCHAR(15) NOT NULL,
    bidOwnerID VARCHAR(8) NOT NULL,
    bidAmount NUMERIC(10, 2) NOT NULL

)