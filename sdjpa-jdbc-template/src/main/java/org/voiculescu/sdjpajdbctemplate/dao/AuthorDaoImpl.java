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
        Author author = jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
        return Optional.ofNullable(author);
    }

    @Override
    public Optional<Author> getByName(String name) {
        Author author = jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? or last_name= ?", getRowMapper(), name, name);
        return Optional.ofNullable(author);
    }

    @Override
    public Optional<Author> save(Author author) {
        jdbcTemplate.update("INSERT INTO author (first_name,last_name) values (?,?)", author.getFirstName(), author.getLastName());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return getById(createdId);
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
