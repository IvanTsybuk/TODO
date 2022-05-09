package org.based.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.based.domain.Project;

public class ProjectDataBaseRepository {
    private static final String TABLE_NAME = "projects";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private final ConnectionConfiguration connectionConfiguration;
    public ProjectDataBaseRepository(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }
    @SneakyThrows
    public List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        String selectSql = "select * from " + TABLE_NAME;
        final PreparedStatement preparedStatement =
                connectionConfiguration.getConnection().prepareStatement(selectSql);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Project project = new Project();
            project.setName(resultSet.getString(NAME));
            project.setDescription(resultSet.getString(DESCRIPTION));
            projectList.add(project);
        }
        return projectList;
    }
    @SneakyThrows
    public void save(Project project) {
        final String insert = "INSERT INTO " + TABLE_NAME + "(name, description)"
                + "VALUES ('" + project.getName() + "', '" + project.getDescription() + "')";
        final Statement statement =  connectionConfiguration.getConnection().createStatement();
        statement.executeUpdate(insert);
    }
}
