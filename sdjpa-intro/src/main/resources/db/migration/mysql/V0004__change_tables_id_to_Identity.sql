DROP TABLE IF EXISTS book_seq;

ALTER TABLE book
    MODIFY id BIGINT AUTO_INCREMENT;