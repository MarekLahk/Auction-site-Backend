CREATE TABLE ibay_user (
    userID VARCHAR(12) NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    registration_date TIMESTAMP NOT NULL,

    constraint userID CHECK ( userID ~ '^[0-9a-zA-Z]{12}$' ),
    constraint username_unique UNIQUE (username),
    constraint email_unique UNIQUE (email)
);