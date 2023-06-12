DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    cvv             VARCHAR(20) NULL,
    expiration_date VARCHAR(20) NULL,
    CONSTRAINT pk_creditcard PRIMARY KEY (id)
);