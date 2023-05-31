package org.voiculescu.sdjpajdbctemplate.dao;

import org.voiculescu.sdjpajdbctemplate.entity.Book;

import java.util.Optional;

public interface BookDao {
    Optional<Book> getById(Long id);

    Optional<Book> getByTitle(String title);

    Optional<Book> save(Book book);

    Optional<Book> update(Book book);

    void deleteById(Long id);
}
