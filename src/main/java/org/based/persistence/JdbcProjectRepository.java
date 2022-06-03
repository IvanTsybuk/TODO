package org.based.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.based.domain.Project;

public class JdbcProjectRepository implements Repository<Project> {
    private static final String insert = "INSERT INTO projects (name, description) VALUES (?,?)";
    private static final String select = "SELECT * FROM projects";
    private static final String select_by_name = "SELECT * FROM projects WHERE name = ?";
    private static final String delete = "DELETE FROM projects WHERE name = ?";
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    @SneakyThrows
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(select);
             final ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                do {
                    projectList.add(resultSetToProject(resultSet));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projectList;
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
    public Project findByName(String name) {
        Project project = new Project();
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(select_by_name)) {
            preparedStatement.setString(1, name);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        project = resultSetToProject(resultSet);
                    } while (resultSet.next());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return project;
    }
    @SneakyThrows
    private Project resultSetToProject(ResultSet resultSet) {
        final Project project = new Project();
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        return project;
    }
}
