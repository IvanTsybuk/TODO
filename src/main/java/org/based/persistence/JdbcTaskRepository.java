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
import org.based.domain.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JdbcTaskRepository implements Repository<Task> {
    private static final String select = "SELECT * FROM tasks";
    private static final String selectByName = "SELECT * FROM tasks WHERE name = ?";
    private static final String delete = "DELETE FROM tasks WHERE name = ?";
    private static final String insert = "INSERT INTO tasks (name, description) VALUES (?, ?)";
    private static final String update = "UPDATE tasks SET name = ?, description = ? WHERE id = ?";
    @NotNull
    private final DataSource dataSource;
    public JdbcTaskRepository(@NotNull DataSource dataSource) {
        log.info("JdbcTaskRepository initialization");
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(@NotNull Task entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcTaskRepository: Save task- %s", entity.getName()));
        }
    }
    @Override
    @SneakyThrows
    public void update(@NotNull Task entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcTaskRepository: Update task - %s", entity.getName()));
        }
    }
    @Override
    @SneakyThrows
    public @NotNull List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                taskList.add(mapToTask(resultSet));
            }
        }
        if (taskList.isEmpty()) {
            log.warn("JdbcTaskRepository: Empty task list is provided");
            return Collections.emptyList();
        }
        log.debug("JdbcTaskRepository: Select all tasks");
        return taskList;
    }
    @Override
    @SneakyThrows
    public void deleteByName(@NotNull String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            log.debug(String.format("JdbcTaskRepository: Delete a task- %s", name));
        }
    }
    @Override
    @SneakyThrows
    public @NotNull Optional<Task> findByName(@NotNull String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(selectByName)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Task task = mapToTask(resultSet);
                    log.debug(String.format("JdbcTaskRepository: Select a task- %s", name));
                    return Optional.of(task);
                }
            }
        }
        return Optional.empty();
    }
    @SneakyThrows
    private @NotNull Task mapToTask(@NotNull ResultSet resultSet) {
        final Task task = new Task();
        task.setId(resultSet.getLong("id"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        log.debug(String.format("JdbcTaskRepository: Task mapped from resultSet-%s", task));
        return task;
    }
}
