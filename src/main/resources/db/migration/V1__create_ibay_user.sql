CREATE TABLE ibay_user (
    id VARCHAR(8) NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    registration_date TIMESTAMP,

    constraint id_correct_check CHECK ( id ~ '^[0-9a-zA-Z]{8}$' ),
    constraint username_unique UNIQUE (username),
    constraint email_unique UNIQUE (email)
);