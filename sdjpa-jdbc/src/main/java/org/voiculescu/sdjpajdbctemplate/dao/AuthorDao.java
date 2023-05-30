package org.voiculescu.sdjpajdbctemplate.dao;

import org.voiculescu.sdjpajdbctemplate.entity.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getById(Long id);

    Optional<Author> getByName(String name);

    Optional<Author> saveNewAuthor(Author author);

    Optional<Author> updateAuthor(Author savedAuthor);

    void deleteAuthorById(Long id);
}
