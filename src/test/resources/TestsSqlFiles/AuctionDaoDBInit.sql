DELETE FROM AUCTION_BIDS WHERE TRUE;
DELETE FROM AUCTIONS WHERE TRUE;
DELETE FROM IBAY_USER WHERE TRUE;

INSERT INTO IBAY_USER (USERID, USERNAME, FULL_NAME, EMAIL, REGISTRATION_DATE)
VALUES ( 'AuctionUser1', 'username1', 'full_name1', 'email1', {ts '2012-09-17 18:47:52.69'}),
       ( 'AuctionUser2', 'username2', 'full_name2', 'email2', {ts '2012-09-17 18:47:52.69'}),
       ( 'AuctionUser3', 'username3', 'full_name3', 'email3', {ts '2012-09-17 18:47:52.69'});


INSERT INTO AUCTIONS (AUCTIONID, AUCTIONOWNER, TITLE, DESCRIPTION, CREATETIME, ENDDATETIME, CATEGORY)
VALUES ( 'Auction00000001', 'AuctionUser1', 'title1', 'description1', {ts '2012-09-17 18:47:52.69'}, {ts '2030-09-17 18:47:52.69'}, 'automotive' );