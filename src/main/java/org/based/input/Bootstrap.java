package org.based.input;

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
    public static final String USER_PATH = "User_XM";
//    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final Writer<Project> projectWriter = Writer.builder()
                .environmentVariable(PROJECT_PATH)
                .useClass(Project.class)
                .build();
        final Writer<Task> taskWriter = Writer.builder()
                .environmentVariable(TASK_PATH)
                .useClass(Task.class)
                .build();
        final Writer<User> userWriter = Writer.builder()
                .environmentVariable(USER_PATH)
                .useClass(User.class)
                .build();
        Repository<Project> projectRepository = new Repository<>(projectWriter);
        ProjectService projectService = new ProjectService(projectRepository);
        Repository<Task> taskRepository = new Repository<>(taskWriter);
        TaskService taskService = new TaskService(taskRepository);
        Repository<User> userRepository = new Repository<>(userWriter);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);
        consoleAdapter.startApp();
        projectRepository.saveToFile();
        taskRepository.saveToFile();
        userRepository.saveToFile();
    }
}
