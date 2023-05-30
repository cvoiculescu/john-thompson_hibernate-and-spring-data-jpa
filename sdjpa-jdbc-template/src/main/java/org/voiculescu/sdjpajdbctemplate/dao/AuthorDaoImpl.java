package org.voiculescu.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpajdbctemplate.entity.Author;

import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Author> getById(Long id) {
        jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
        return Optional.empty();
    }

    @Override
    public Optional<Author> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Author savedAuthor) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
