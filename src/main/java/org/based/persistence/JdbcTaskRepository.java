package org.based.persistence;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.based.domain.Task;

public class JdbcTaskRepository implements RepositoryInterface<Task> {
    private static final  String select = "SELECT * FROM tasks";
    private static final String select_by_name = "SELECT * FROM tasks WHERE name = ?";
    private static final String delete = "DELETE FROM tasks WHERE name = ?";
    private static final String insert = "INSERT INTO tasks (name, description) VALUES (?, ?)";
    private final HikariDataSource dataSource;
    public JdbcTaskRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(Task entity) {
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    @Override
    @SneakyThrows
    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(select);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final Optional<Task> task = extractTasks(resultSet);
            taskList.add(task.get());
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return taskList;
    }
    @Override
    @SneakyThrows
    public void delete(String name) {
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
    @Override
    @SneakyThrows
    public Task findByName(String name) {
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(select_by_name);
        preparedStatement.setString(1, name);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Optional<Task> optional = Optional.empty();
        while (resultSet.next()) {
            optional = extractTasks(resultSet);
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
    private Optional<Task> extractTasks(ResultSet rs) {
        Task task = new Task();
        task.setName(rs.getString("name"));
        task.setDescription(rs.getString("description"));
        return Optional.of(task);
    }
}
