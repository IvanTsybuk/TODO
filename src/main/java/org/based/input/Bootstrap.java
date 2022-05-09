package org.based.input;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.ConnectionConfiguration;
import org.based.persistence.Writer;

public class Bootstrap {
    public static final String PROJECT_PATH = "PROJECT_PATH";
    public static final String TASK_PATH = "TASK_PATH";
    public static final String USER_PATH = "USER_PATH";
    private static final String URL = "jdbc:postgresql://localhost:5432/todoApp";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void main(String[] args) {

        final Writer<Project> projectWriter = Writer.<Project>builder()
                .environmentVariable(PROJECT_PATH)
                .useClass(Project.class)
                .build();
        final Writer<Task> taskWriter = Writer.<Task>builder()
                .environmentVariable(TASK_PATH)
                .useClass(Task.class)
                .build();
        final Writer<User> userWriter = Writer.<User>builder()
                .environmentVariable(USER_PATH)
                .useClass(User.class)
                .build();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        ContextDataBase contextDataBase =
                new ContextDataBase(new ConnectionConfiguration(connection));
        contextDataBase.startApp();
    }
}
