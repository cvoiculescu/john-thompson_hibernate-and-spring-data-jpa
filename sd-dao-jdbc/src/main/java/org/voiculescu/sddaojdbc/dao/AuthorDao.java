package org.voiculescu.sddaojdbc.dao;

import org.voiculescu.sddaojdbc.entity.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getById(Long id);
}
