package org.based.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.based.domain.User;

public class JdbcUserRepository implements Repository<User> {
    private static final String select = "SELECT * FROM users";
    private static final String select_by_name = "SELECT * FROM users WHERE name = ?";
    private static final String delete = "DELETE FROM users WHERE name = ?";
    private static final String insert = "INSERT INTO users (name, surname) VALUES (?, ?)";
    private final DataSource dataSource;
    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(User entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    @SneakyThrows
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                do {
                    userList.add(resultSetToUser(resultSet));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }
    @Override
    @SneakyThrows
    public void deleteByName(String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    @SneakyThrows
    public User findByName(String name) {
        User user = new User();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(select_by_name)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        user = resultSetToUser(resultSet);
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    @SneakyThrows
    private User resultSetToUser(ResultSet resultSet) {
        final User user = new User();
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        return user;
    }
}
