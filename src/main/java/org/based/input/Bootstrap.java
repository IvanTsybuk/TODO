package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.persistence.*;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args){
        System.out.println("initialize project variable");
        Scanner initProject = new Scanner(System.in);
        final String projectPath = initProject.next();
        FileOperator projectFileOperator = new FileOperator(projectPath);
        System.out.println(projectFileOperator.showConfigPath());
        System.out.println("initialize task variable");
        Scanner initTask = new Scanner(System.in);
        final String taskPath = initTask.next();
        FileOperator taskFileOperator = new FileOperator(taskPath);
        System.out.println(taskFileOperator.showConfigPath());
        System.out.println("initialize user variable");
        Scanner initUser = new Scanner(System.in);
        final String userPath = initUser.next();
        FileOperator userFileOperator = new FileOperator(userPath);
        userFileOperator.showConfigPath();

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
        projectRepository.writeProject();
        taskRepository.writeTask();
        userRepository.writeUser();
    }
}
