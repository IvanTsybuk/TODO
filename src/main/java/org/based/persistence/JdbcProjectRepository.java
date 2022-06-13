package org.based.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.based.domain.Project;
import org.springframework.stereotype.Component;

@Component
public class JdbcProjectRepository implements Repository<Project> {
    private static final String select = "SELECT * FROM projects";
    private static final String selectByName = "SELECT * FROM projects WHERE name = ?";
    private static final String delete = "DELETE FROM projects WHERE name = ?";
    private static final String insert = "INSERT INTO projects (name, description) VALUES (?,?)";
    private static final String update = "UPDATE projects"
            + " SET name = ?, description = ? WHERE id = ?";
    private final DataSource dataSource;
    public JdbcProjectRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(Project entity) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    @SneakyThrows
    public void update(Project entity) {
        try (final Connection connection  = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    @SneakyThrows
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                projectList.add(mapToProject(resultSet));
            }
        }
        return projectList;
    }
    @Override
    @SneakyThrows
    public void deleteByName(String name) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        }
    }
    @Override
    @SneakyThrows
    public Project findByName(String name) {
        Project project = new Project();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(selectByName)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    project = mapToProject(resultSet);
                }
            }
        }
        return project;
    }
    @SneakyThrows
    private Project mapToProject(ResultSet resultSet) {
        final Project project = new Project();
        project.setId(resultSet.getLong("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        return project;
    }
}
