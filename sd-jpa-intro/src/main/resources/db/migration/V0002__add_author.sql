DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    CONSTRAINT pk_author PRIMARY KEY (id)
) ENGINE = InnoDB;