CREATE TABLE auctions (
    auctionID VARCHAR(15) NOT NULL PRIMARY KEY,
    auctionOwner VARCHAR(12),
    title VARCHAR(150) NOT NULL ,
    description TEXT,
    createTime timestamp,
    endDateTime timestamp NOT NULL,
    category VARCHAR NOT NULL,


    constraint auctionIDAlphanumeric CHECK ( REGEXP_LIKE(auctionID ,'^[0-9a-zA-Z]{15}$')),
    FOREIGN KEY (auctionOwner) REFERENCES ibay_user (userid) ON DELETE SET NULL
)
