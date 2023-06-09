CREATE TABLE credit_card_holder
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255)          NULL,
    last_name  VARCHAR(255)          NULL,
    zip_code   VARCHAR(255)          NULL,
    CONSTRAINT pk_creditcardholder PRIMARY KEY (id)
);