package org.based.persistence;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.based.domain.User;

public class JdbcUserRepository implements RepositoryInterface<User> {
    private static final String select = "SELECT * FROM users";
    private static final String select_by_name = "SELECT * FROM users WHERE name = ?";
    private static final String insert = "INSERT INTO users (name, surname) VALUES (?, ?)";
    private final HikariDataSource dataSource;
    public JdbcUserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(User entity) {
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getUserSurName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    @Override
    @SneakyThrows
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(select);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final Optional<User> user = extractUsers(resultSet);
            userList.add(user.get());
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return userList;
    }
    @Override
    public void delete(String name) {
    }
    @Override
    @SneakyThrows
    public User findByName(String name) {
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(select_by_name);
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Optional<User> optional = Optional.empty();
        while (resultSet.next()) {
            optional = extractUsers(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return optional.get();
    }
    @Override
    public void saveToFile() {
    }
    @SneakyThrows
    private Optional<User> extractUsers(ResultSet rs) {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setUserSurName(rs.getString("surname"));
        return Optional.of(user);
    }
}
