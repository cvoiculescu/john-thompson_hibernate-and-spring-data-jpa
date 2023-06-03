drop table if exists book;
drop table if exists book_seq;
create table book
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL
        primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255)
);