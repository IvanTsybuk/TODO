package org.based.persistence;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.based.domain.Project;

public class JdbcProjectRepository implements RepositoryInterface<Project> {
    private static final String insert = "INSERT INTO projects (name, description) VALUES (?,?)";
    private static final String select = "SELECT * FROM projects";
    private final HikariDataSource dataSource;
    public JdbcProjectRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    @SneakyThrows
    public void save(Project entity) {
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
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        final Connection connection = dataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(select);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Optional<Project> optional = extractProjects(resultSet);
            projectList.add(optional.get());
        }
        resultSet.close();
        connection.close();
        return projectList;
    }
    @Override
    public void delete(String name) {
    }
    @Override
    @SneakyThrows
    public Project findByName(String name) {
        Optional<Project> optional = Optional.empty();
        return optional.get();
    }
    @Override
    public void saveToFile() {
    }
    @SneakyThrows
    private Optional<Project> extractProjects(ResultSet rs) {
        Project project = new Project();
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));
        return Optional.of(project);
    }
}
