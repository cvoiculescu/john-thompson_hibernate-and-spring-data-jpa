CREATE TABLE author
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255)          NULL,
    last_name  VARCHAR(255)          NULL,
    CONSTRAINT pk_author PRIMARY KEY (id)
);

CREATE TABLE book
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    title     VARCHAR(255)          NULL,
    publisher VARCHAR(255)          NULL,
    isbn      VARCHAR(255)          NULL,
    author_id BIGINT                NULL,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES author (id);
