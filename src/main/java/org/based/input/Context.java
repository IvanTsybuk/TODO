package org.based.input;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Scanner;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.JdbcProjectRepository;
import org.based.persistence.JdbcTaskRepository;
import org.based.persistence.JdbcUserRepository;
import org.based.persistence.RepositoryInterface;

public class Context {
    private final ConsoleAdapter consoleAdapter;
    private final HikariDataSource dataSource;
    public Context(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        RepositoryInterface<Project> projectDataBaseRepository =
                new JdbcProjectRepository(dataSource);
        RepositoryInterface<Task> taskDataBaseRepository =
                new JdbcTaskRepository(dataSource);
        RepositoryInterface<User> userDataBaseRepository =
                new JdbcUserRepository(dataSource);
        ProjectService projectService = new ProjectService(projectDataBaseRepository);
        TaskService taskService = new TaskService(taskDataBaseRepository);
        UserService userService = new UserService(userDataBaseRepository);
        Scanner scanner = new Scanner(System.in);
        consoleAdapter = new ConsoleAdapter(taskService,
                projectService,
                userService,
                scanner);
    }
    public void startApp() {
        consoleAdapter.startApp();
    }
    public void closeApp() {
        dataSource.close();
    }
}
