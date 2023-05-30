package org.voiculescu.sddaojdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.voiculescu.sddaojdbc.entity.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {


    @Override
    public Optional<Author> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> saveNewAuthor(Author author) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> updateAuthor(Author savedAuthor) {
        return Optional.empty();
    }

    @Override
    public void deleteAuthorById(Long id) {
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
