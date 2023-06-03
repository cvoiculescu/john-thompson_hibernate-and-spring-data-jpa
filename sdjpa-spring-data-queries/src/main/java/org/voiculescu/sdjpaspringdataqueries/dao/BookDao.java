package org.voiculescu.sdjpaspringdataqueries.dao;

import org.voiculescu.sdjpaspringdataqueries.entity.Author;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    Book getByTitle(String title);

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);

    List<Book> findAll();
}
