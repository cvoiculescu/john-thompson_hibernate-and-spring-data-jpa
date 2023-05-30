package org.voiculescu.sddaojdbc.dao;

import org.springframework.stereotype.Component;
import org.voiculescu.sddaojdbc.entity.Author;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;

    public AuthorDaoImpl(DataSource source) {

        this.source = source;
    }

    @Override
    public Optional<Author> getById(Long id) {

        String sql = "SELECT * FROM author WHERE id = ?";

        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return extractAuthorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Author> getByName(String name) {
        String sql = "SELECT * FROM author WHERE first_name = ? or last_name = ?";

        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, name);
            ps.setString(2, name);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return extractAuthorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private static Optional<Author> extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return Optional.of(new Author()
                .setId(resultSet.getLong("id"))
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name")));
    }
}
