package org.voiculescu.sddaojdbc.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.voiculescu.sddaojdbc.entity.Author;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource source;

    public AuthorDaoImpl(DataSource source) {

        this.source = source;
    }

    @Override
    public Optional<Author> getById(Long id) {
        String sql = "SELECT * FROM author WHERE id=" + id;
        try (Connection connection = source.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return Optional.of(new Author()
                        .setId(id)
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
