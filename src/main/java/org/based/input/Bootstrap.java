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
    public static final String PROJECT_PATH = "Project_JSON";
    public static final String TASK_PATH = "Task_JSON";
    public static final String USER_PATH = "User_JSON";

    public static void main(String[] args) {
        WriterBuilder writerBuilder = new WriterBuilder();
        Repository<Project> projectRepository = new Repository<>(writerBuilder.getAbstractWriter(PROJECT_PATH, Project.class.getSimpleName()), new TypeReference<>() {
        });
        ProjectService projectService = new ProjectService(projectRepository);
        Repository<Task> taskRepository = new Repository<>(writerBuilder.getAbstractWriter(TASK_PATH, Task.class.getSimpleName()), new TypeReference<>() {
        });
        TaskService taskService = new TaskService(taskRepository);
        Repository<User> userRepository = new Repository<>(writerBuilder.getAbstractWriter(USER_PATH, User.class.getSimpleName()), new TypeReference<>() {
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
