package org.based.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.based.domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JdbcUserRepository implements Repository<User> {
    private static final String select = "SELECT * FROM users";
    private static final String selectByName = "SELECT * FROM users WHERE name = ?";
    private static final String delete = "DELETE FROM users WHERE name = ?";
    private static final String insert = "INSERT INTO users (name, surname) VALUES (?, ?)";
    private static final String update = "UPDATE users SET name = ?, surname = ? WHERE id = ?";
    @NotNull
    private final DataSource dataSource;
    public JdbcUserRepository(@NotNull DataSource dataSource) {
        log.info("JdbcUserRepository initialization");
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(@NotNull User entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcUserRepository: Save new user- %s in repository",
                    entity.getName()));
        }
    }
    @Override
    @SneakyThrows
    public void update(@NotNull User entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, entity.getSurname());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcUserRepository: Updating user- %s", entity.getName()));
        }
    }
    @Override
    @SneakyThrows
    public @NotNull List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                userList.add(mapToUser(resultSet));
            }
        }
        if (userList.isEmpty()) {
            log.debug("JdbcUserRepository: Empty user list is provided");
            return Collections.emptyList();
        }
        log.debug("JdbcUserRepository: Select all users");
        return userList;
    }
    @Override
    @SneakyThrows
    public void deleteByName(@NotNull String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcUserRepository: Delete user - %s", name));
        }
    }
    @Override
    @SneakyThrows
    public @NotNull Optional<User> findByName(@NotNull String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(selectByName)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = mapToUser(resultSet);
                    log.debug(String.format("JdbcUserRepository: Select user- %s", name));
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }
    @SneakyThrows
    private @NotNull User mapToUser(@NotNull ResultSet resultSet) {
        final User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        log.debug(String.format("JdbcUserRepository: User mapped from resultSet-%s", user));
        return user;
    }
}
