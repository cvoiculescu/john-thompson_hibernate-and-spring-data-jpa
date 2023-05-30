DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL
        PRIMARY KEY,
    first_name VARCHAR(255)                            NULL,
    last_name  VARCHAR(255)                            NULL,
    CONSTRAINT pk_author PRIMARY KEY (id)
);