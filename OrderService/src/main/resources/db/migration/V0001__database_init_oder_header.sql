CREATE TABLE order_header
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    customer_name VARCHAR(255)          NULL,
    CONSTRAINT pk_orderheader PRIMARY KEY (id)
);

INSERT INTO order_header (customer_name) value ('Customer');
