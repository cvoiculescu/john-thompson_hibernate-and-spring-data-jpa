package org.voiculescu.sdjpajdbctemplate.dao;

import org.voiculescu.sdjpajdbctemplate.entity.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getById(Long id);

    Optional<Author> getByName(String name);

    Optional<Author> save(Author author);

    Optional<Author> update(Author savedAuthor);

    void deleteById(Long id);
}
