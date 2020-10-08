CREATE TABLE auctions (
    auctionID VARCHAR(15) NOT NULL PRIMARY KEY,
    auctionOwner VARCHAR(8) REFERENCES ibay_user (userid) NOT NULL,
    title VARCHAR(150) NOT NULL ,
    description TEXT,
    createTime timestamp,
    endDateTime timestamp NOT NULL,

    FOREIGN KEY (auctionOwner) REFERENCES ibay_user (userid)
)
