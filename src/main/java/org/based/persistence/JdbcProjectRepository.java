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
            while (resultSet.next()) {
                Project project = new Project();
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                projectList.add(project);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projectList;
    }
    @Override
    public void deleteByName(String name) {
    }
    @Override
    @SneakyThrows
    public Project findByName(String name) {
        return new Project();
    }
}
