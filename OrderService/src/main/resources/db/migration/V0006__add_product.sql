CREATE TABLE product
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    created_date       datetime              NULL,
    last_modified_date datetime              NULL,
    `description`      VARCHAR(50)           NULL,
    product_status     VARCHAR(30)           NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

-- insert initial product
INSERT INTO product (created_date, last_modified_date, description, product_status)
VALUES (curdate(), curdate(), 'Product', 'NEW');
