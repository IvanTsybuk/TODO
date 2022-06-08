package org.based.input;

import java.util.Scanner;
import javax.sql.DataSource;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.JdbcProjectRepository;
import org.based.persistence.JdbcTaskRepository;
import org.based.persistence.JdbcUserRepository;
import org.based.persistence.Repository;

public class Context {
    private final ConsoleAdapter consoleAdapter;
    public Context(DataSource dataSource) {
        Repository<Project> projectRepository = new JdbcProjectRepository(dataSource);
        Repository<Task> taskRepository = new JdbcTaskRepository(dataSource);
        Repository<User> userRepository = new JdbcUserRepository(dataSource);
        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(taskRepository);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        consoleAdapter = new ConsoleAdapter(taskService,
                projectService,
                userService,
                scanner);
    }
    public void startApp() {
        consoleAdapter.startApp();
    }
}
