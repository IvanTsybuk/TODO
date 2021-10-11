package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.application.JsonWriter;
import org.based.persistence.ProjectRepository;
import org.based.persistence.TaskRepository;
import org.based.persistence.UserRepository;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) {

        JsonWriter jsonWriter = new JsonWriter();
        ProjectService projectService = new ProjectService(new ProjectRepository());
        TaskService taskService = new TaskService(new TaskRepository());
        UserService userService = new UserService(new UserRepository());
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, jsonWriter, scanner);

        consoleAdapter.startApp();
    }
}
