package org.based.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.based.domain.Task;

public class TaskDataBaseRepository {
    private static final String TABLE_NAME = "tasks";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private final ConnectionConfiguration connectionConfiguration;
    public TaskDataBaseRepository(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }
    @SneakyThrows
    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        String selectSql = "select * from " + TABLE_NAME;
        final PreparedStatement preparedStatement = connectionConfiguration
                .getConnection()
                .prepareStatement(selectSql);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Task task = new Task();
            task.setName(resultSet.getString(NAME));
            task.setDescription(resultSet.getString(DESCRIPTION));
            taskList.add(task);
        }
        return taskList;
    }
    @SneakyThrows
    public void save(Task task) {
        final String insert = "INSERT INTO " + TABLE_NAME + "(name, description)"
                + "VALUES ('" + task.getName() + "', '" + task.getDescription() + "')";
        final Statement statement =  connectionConfiguration
                .getConnection()
                .createStatement();
        statement.executeUpdate(insert);
    }
    @SneakyThrows
    public void delete(String name) {
        String deleteStatement = "DELETE FROM tasks WHERE name = ?";
        final PreparedStatement preparedStatement = connectionConfiguration
                .getConnection()
                .prepareStatement(deleteStatement);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }
}
