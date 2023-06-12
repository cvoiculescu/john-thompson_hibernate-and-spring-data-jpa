DROP TABLE IF EXISTS credit_card_holder;
CREATE TABLE credit_card_holder
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(30)          NULL,
    last_name  VARCHAR(30)          NULL,
    zip_code   VARCHAR(10)          NULL,
    CONSTRAINT pk_creditcardholder PRIMARY KEY (id)
);