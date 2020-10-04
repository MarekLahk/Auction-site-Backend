CREATE TABLE auctions (
    auctionID VARCHAR(8) NOT NULL PRIMARY KEY,
    auctionOwner VARCHAR(8) REFERENCES ibay_user (userid) NOT NULL,
    title VARCHAR(150) NOT NULL ,
    description TEXT,
    endDateTime timestamp NOT NULL


)
