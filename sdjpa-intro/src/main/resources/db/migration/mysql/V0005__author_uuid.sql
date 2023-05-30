DROP TABLE IF EXISTS author_uuid;
CREATE TABLE author_uuid
(
    id         VARCHAR(36)  NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;