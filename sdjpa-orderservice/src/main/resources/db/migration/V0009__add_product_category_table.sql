CREATE TABLE category
(
    id                 BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description        VARCHAR(50),
    created_date       TIMESTAMP,
    last_modified_date TIMESTAMP
);

CREATE TABLE product_category
(
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT pc_product_id_fk FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT pc_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id)
);

-- add data -- product

INSERT INTO product (created_date, last_modified_date, description, product_status)
VALUES (NOW(), NOW(), 'PRODUCT1', 'NEW');
INSERT INTO product (created_date, last_modified_date, description, product_status)
VALUES (NOW(), NOW(), 'PRODUCT2', 'NEW');
INSERT INTO product (created_date, last_modified_date, description, product_status)
VALUES (NOW(), NOW(), 'PRODUCT3', 'NEW');
INSERT INTO product (created_date, last_modified_date, description, product_status)
VALUES (NOW(), NOW(), 'PRODUCT4', 'NEW');

-- add data - category

INSERT INTO category (description, created_date, last_modified_date)
VALUES ('CAT1', NOW(), NOW());
INSERT INTO category (description, created_date, last_modified_date)
VALUES ('CAT2', NOW(), NOW());
INSERT INTO category (description, created_date, last_modified_date)
VALUES ('CAT3', NOW(), NOW());

-- add data - product_category

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT1'
  AND c.description = 'CAT1';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT2'
  AND c.description = 'CAT1';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT1'
  AND c.description = 'CAT3';

INSERT INTO product_category (product_id, category_id)
SELECT p.id, c.id
FROM product p,
     category c
WHERE p.description = 'PRODUCT4'
  AND c.description = 'CAT3';
