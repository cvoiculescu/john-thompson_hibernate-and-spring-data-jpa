CREATE TABLE credit_card
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    cvv             VARCHAR(255) NULL,
    expiration_date VARCHAR(255) NULL,
    CONSTRAINT pk_creditcard PRIMARY KEY (id)
);