package org.voiculescu.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import org.voiculescu.sdjpajdbctemplate.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {


    private final AuthorDao authorDao;

    public BookMapper(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"))
                .setAuthor(authorDao.getById(rs.getLong("author_id")).orElse(null))
                .setIsbn(rs.getString("isbn"))
                .setPublisher(rs.getString("publisher"));
    }

}
