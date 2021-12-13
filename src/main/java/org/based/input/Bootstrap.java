package org.based.input;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.*;

import java.util.Scanner;

public class Bootstrap {
    public static final String PROJECT_PATH = "Project_XML";
    public static final String TASK_PATH = "Task_XML";
    public static final String USER_PATH = "User_XM";

    public static void main(String[] args) {
        WriterBuilder projectBuilder = new WriterBuilder(PROJECT_PATH, Project.class.getSimpleName());
        Repository<Project> projectRepository = new Repository<>(projectBuilder.build(), new TypeReference<>() {
        });
        ProjectService projectService = new ProjectService(projectRepository);
        WriterBuilder taskBuilder = new WriterBuilder(TASK_PATH, Task.class.getSimpleName());
        Repository<Task> taskRepository = new Repository<>(taskBuilder.build(), new TypeReference<>() {
        });
        TaskService taskService = new TaskService(taskRepository);
        WriterBuilder userBuilder = new WriterBuilder(USER_PATH, User.class.getSimpleName());
        Repository<User> userRepository = new Repository<>(userBuilder.build(), new TypeReference<>() {
        });
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);
        consoleAdapter.startApp();
        projectRepository.sendRepository();
        taskRepository.sendRepository();
        userRepository.sendRepository();
    }
}
