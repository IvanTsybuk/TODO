package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.persistence.ProjectRepository;
import org.based.persistence.TaskRepository;
import org.based.persistence.UserRepository;

import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) throws IOException {

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
//        projectRepository.writeProjectsMapJson();
//        projectRepository.writeProjectsMapXml();
        taskRepository.writeTaskMapToJson();
        taskRepository.writeTaskListToXml();
        userRepository.writeUserMapToJson();

    }
}
