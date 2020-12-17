CREATE TABLE USERS
(
    id        BIGSERIAL,
    email     VARCHAR (80) UNIQUE,
    password     VARCHAR (80) UNIQUE,
    first_name  VARCHAR (40),
    last_name  VARCHAR (40),
    creation_date TIMESTAMP,
    modification_date TIMESTAMP
);