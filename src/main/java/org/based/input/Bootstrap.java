package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.persistence.*;

import java.util.Scanner;

public class Bootstrap {
    public static final String DEFAULT_CONFIGURATION_PATH_SET = "DEFAULT";
    public static final String PROJECT_PATH = "PXI";
    public static final String TASK_PATH = "TXI";
    public static final String USER_PATH = "UXI";

    public static void main(String[] args) {

        System.out.println("project variable initialization");
        FileOperator projectFileOperator = new FileOperator(setFileOperatorConfiguration(PROJECT_PATH));
        System.out.println("initialize task variable");
        FileOperator taskFileOperator = new FileOperator(setFileOperatorConfiguration(TASK_PATH));
        System.out.println("initialize user variable");
        FileOperator userFileOperator = new FileOperator(setFileOperatorConfiguration(USER_PATH));

        ProjectRepository projectRepository = new ProjectRepository(projectFileOperator);
        TaskRepository taskRepository = new TaskRepository(taskFileOperator);
        UserRepository userRepository = new UserRepository(userFileOperator);

        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(taskRepository);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);

        consoleAdapter.startApp();
        projectRepository.write();
        taskRepository.write();
        userRepository.write();
    }
    private static String setFileOperatorConfiguration(String environmentVariable) {
        String configuration = DEFAULT_CONFIGURATION_PATH_SET;
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            System.out.println(configuration);
            return configuration;
        }
        System.out.println(configuration + " was set");
        return configuration;
    }
}
