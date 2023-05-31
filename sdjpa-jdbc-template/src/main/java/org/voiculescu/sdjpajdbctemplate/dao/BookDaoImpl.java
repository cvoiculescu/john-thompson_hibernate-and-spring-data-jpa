package org.voiculescu.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpajdbctemplate.entity.Book;

import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;
    private final AuthorDao authorDao;

    public BookDaoImpl(JdbcTemplate jdbcTemplate, AuthorDao authorDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getRowMapper(), id));
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getRowMapper(), title));
    }

    @Override
    public Optional<Book> save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title,publisher,isbn,author_id) values (?,?,?,?)",
                book.getTitle(), book.getPublisher(), book.getIsbn(), book.getAuthor().getId());
        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return getById(createdId);
    }

    @Override
    public Optional<Book> update(Book book) {
        jdbcTemplate.update("UPDATE book set title=?,publisher=?,isbn=?,author_id=? where id = ?",
                book.getTitle(), book.getPublisher(), book.getIsbn(), book.getAuthor().getId(),book.getId());
        return getById(book.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    private BookMapper getRowMapper() {
        return new BookMapper(authorDao);
    }
}
