DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    cvv             VARCHAR(30) NULL,
    expiration_date VARCHAR(30) NULL,
    CONSTRAINT pk_creditcard PRIMARY KEY (id)
);