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
                    return Optional.of(extractAuthorFromResultSet(resultSet));
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
                    return Optional.of(extractAuthorFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> saveNewAuthor(Author author) {
        String sql = "INSERT INTO author (first_name,last_name) VALUES (?,?)";
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.execute();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                return getById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> updateAuthor(Author savedAuthor) {
        String sql = "UPDATE author SET first_name = ?, last_name = ? WHERE id = ?";
        try (Connection connection = source.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, savedAuthor.getFirstName());
            ps.setString(2, savedAuthor.getLastName());
            ps.setLong(3, savedAuthor.getId());
            ps.executeUpdate();
            return getById(savedAuthor.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Author extractAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return new Author()
                .setId(resultSet.getLong("id"))
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name"));

    }
}
