package org.voiculescu.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import org.voiculescu.sdjpajdbctemplate.entity.Author;
import org.voiculescu.sdjpajdbctemplate.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        rs.next();
        Author author = new Author()
                .setId(rs.getLong("id"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"));
        if (rs.getString("book_id") != null) {
            author.setBooks(new ArrayList<>());
            author.getBooks().add(mapBook(rs));
        }
        while (rs.next()) {
            author.getBooks().add(mapBook(rs));
        }
        return author;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        return new Book()
                .setId(rs.getLong("book_id"))
                .setTitle(rs.getString("book.title"))
                .setAuthor(new Author().setId(rs.getLong("id")))
                .setIsbn(rs.getString("book.isbn"))
                .setPublisher(rs.getString("book.publisher"));
    }

}