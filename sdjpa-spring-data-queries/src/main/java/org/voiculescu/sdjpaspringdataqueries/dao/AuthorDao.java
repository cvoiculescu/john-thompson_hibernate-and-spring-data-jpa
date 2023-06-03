package org.voiculescu.sdjpaspringdataqueries.dao;

import org.voiculescu.sdjpaspringdataqueries.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author getById(Long id);

    Author getByName(String fistName, String lastName);

    Author save(Author author);

    Author update(Author savedAuthor);

    void deleteById(Long id);

    List<Author> findAll();
}
