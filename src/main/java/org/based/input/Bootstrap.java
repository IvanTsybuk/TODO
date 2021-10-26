package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.persistence.ProjectRepository;
import org.based.persistence.TaskRepository;
import org.based.persistence.UserRepository;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) {

        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();
        UserRepository userRepository = new UserRepository();

        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(taskRepository);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);

        consoleAdapter.startApp();
        projectRepository.writeProjectsMapJson();
        taskRepository.writeTaskMapToJson();
        userRepository.writeUserMapToJson();

    }
}
