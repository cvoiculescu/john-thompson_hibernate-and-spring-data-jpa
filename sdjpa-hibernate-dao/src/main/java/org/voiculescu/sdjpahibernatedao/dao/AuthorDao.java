package org.voiculescu.sdjpahibernatedao.dao;

import org.voiculescu.sdjpahibernatedao.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> listAuthorByLastNameLike(String lastName);

    Optional<Author> getById(Long id);

    Optional<Author> getByName(String name);

    Optional<Author> save(Author author);

    Optional<Author> update(Author savedAuthor);

    void deleteById(Long id);
}
