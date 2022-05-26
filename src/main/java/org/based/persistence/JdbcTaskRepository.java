package org.based.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.based.domain.Task;

public class JdbcTaskRepository implements Repository<Task> {
    private static final String select = "SELECT * FROM tasks";
    private static final String select_by_name = "SELECT * FROM tasks WHERE name = ?";
    private static final String delete = "DELETE FROM tasks WHERE name = ?";
    private static final String insert = "INSERT INTO tasks (name, description) VALUES (?, ?)";
    private final DataSource dataSource;
    public JdbcTaskRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(Task entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    @SneakyThrows
    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
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
    public Task findByName(String name) {
        Task task = new Task();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(select_by_name)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    task.setName(resultSet.getString("name"));
                    task.setDescription(resultSet.getString("description"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return task;
        }
    }
}
