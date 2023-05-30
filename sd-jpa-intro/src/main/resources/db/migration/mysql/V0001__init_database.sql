drop table if exists book;
drop table if exists book_seq;
create table book
(
    id        bigint not null
        primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255)
) engine = InnoDB;

create table book_seq
(
    next_val bigint
) engine = InnoDB;

insert into book_seq
values (1);