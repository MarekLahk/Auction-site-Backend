DELETE FROM AUCTION_BIDS WHERE TRUE;
DELETE FROM AUCTIONS WHERE TRUE;
DELETE FROM IBAY_USER WHERE TRUE;

INSERT INTO IBAY_USER (USERID, USERNAME, FULL_NAME, EMAIL, REGISTRATION_DATE)
VALUES ( 'BidUser00001', 'username1', 'full_name1', 'email1', {ts '2012-09-17 18:47:52.69'}),
       ( 'BidUser00002', 'username2', 'full_name2', 'email2', {ts '2012-09-17 18:47:52.69'}),
       ( 'BidUser00003', 'username3', 'full_name3', 'email3', {ts '2012-09-17 18:47:52.69'});


INSERT INTO AUCTIONS (AUCTIONID, AUCTIONOWNER, TITLE, DESCRIPTION, CREATETIME, ENDDATETIME, CATEGORY)
 VALUES ( 'BidAuction00001', 'BidUser00001', 'title1', 'description1', {ts '2012-09-17 18:47:52.69'}, {ts '2030-09-17 18:47:52.69'}, 'automotive' );

INSERT INTO AUCTION_BIDS (BIDID, BIDAUCTIONID, BIDOWNERID, BIDAMOUNT, BIDDATETIME)
 VALUES ( 'bd2bb7a3-a575-4fa0-8f36-7205a8209e3c', 'BidAuction00001', 'BidUser00002', '0000100' , {ts '2013-09-17 18:47:52.69'}),
        ('bd2bb7a3-a575-4fa0-8f36-7205a8209e3e', 'BidAuction00001', 'BidUser00002', '0000200' , {ts '2013-09-18 18:47:52.69'});


