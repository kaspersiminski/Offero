CREATE TABLE ACCOUNT
(
    id        BIGINT NOT NULL PRIMARY KEY,
    email     VARCHAR (80) UNIQUE,
    first_name  VARCHAR (40),
    last_name  VARCHAR (40)
);