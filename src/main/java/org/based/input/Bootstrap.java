package org.based.input;

import java.util.Scanner;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.Repository;
import org.based.persistence.Writer;

public class Bootstrap {
    public static final String PROJECT_PATH = "PROJECT_PATH";
    public static final String TASK_PATH = "TASK_PATH";
    public static final String USER_PATH = "USER_PATH";

    public static void main(String[] args) {
        Repository<Project> projectRepository = new Repository<>(Writer.<Project>builder()
                .environmentVariable(PROJECT_PATH)
                .useClass(Project.class)
                .build());
        Repository<Task> taskRepository = new Repository<>(Writer.<Task>builder()
                .environmentVariable(TASK_PATH)
                .useClass(Task.class)
                .build());
        Repository<User> userRepository = new Repository<>(Writer.<User>builder()
                .environmentVariable(USER_PATH)
                .useClass(User.class)
                .build());
        start(projectRepository, taskRepository, userRepository);
        terminate(projectRepository, taskRepository, userRepository);
    }
    public static void start(Repository<Project> projectRepository,
             Repository<Task> taskRepository,
             Repository<User> userRepository) {
        Scanner scanner = new Scanner(System.in);
        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(taskRepository);
        UserService userService = new UserService(userRepository);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);
        consoleAdapter.startApp();
    }
    private static void terminate(Repository<Project> projectRepository,
             Repository<Task> taskRepository,
             Repository<User> userRepository) {
        projectRepository.saveToFile();
        taskRepository.saveToFile();
        userRepository.saveToFile();
    }
}
