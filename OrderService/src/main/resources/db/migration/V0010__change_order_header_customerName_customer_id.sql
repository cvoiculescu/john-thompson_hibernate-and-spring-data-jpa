CREATE TABLE customer
(
    id                 BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(50),
    shipping_address   VARCHAR(30),
    shipping_city      VARCHAR(30),
    shipping_state     VARCHAR(30),
    shipping_zip_code  VARCHAR(30),
    bill_to_address    VARCHAR(30),
    bill_to_city       VARCHAR(30),
    bill_to_state      VARCHAR(30),
    bill_to_zip_code   VARCHAR(30),
    created_date       datetime NULL,
    last_modified_date datetime NULL
);

ALTER TABLE order_header
    DROP COLUMN customer_name,
    DROP COLUMN shipping_address,
    DROP COLUMN shipping_city,
    DROP COLUMN shipping_state,
    DROP COLUMN shipping_zip_code,
    DROP COLUMN bill_to_address,
    DROP COLUMN bill_to_city,
    DROP COLUMN bill_to_state,
    DROP COLUMN bill_to_zip_code;

TRUNCATE order_header;

ALTER TABLE order_header
    ADD COLUMN customer_id BIGINT NOT NULL;

ALTER TABLE order_header
    ADD CONSTRAINT order_header_customer_fk FOREIGN KEY (customer_id) REFERENCES customer(id);
