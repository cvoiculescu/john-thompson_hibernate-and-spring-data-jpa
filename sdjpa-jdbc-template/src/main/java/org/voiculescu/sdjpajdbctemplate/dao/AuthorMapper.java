package org.voiculescu.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import org.voiculescu.sdjpajdbctemplate.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author()
                .setId(rs.getLong("id"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"));
        return author;
    }

}
