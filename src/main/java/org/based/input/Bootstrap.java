package org.based.input;

import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.persistence.JsonWriter;
import org.based.persistence.ProjectRepository;
import org.based.persistence.TaskRepository;
import org.based.persistence.UserRepository;

import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) {

        ProjectRepository projectRepository = new ProjectRepository(new JsonWriter());
        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(new TaskRepository());
        UserService userService = new UserService(new UserRepository());
        Scanner scanner = new Scanner(System.in);
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService,
                userService, scanner);

        consoleAdapter.startApp();
        projectRepository.writeInfo(projectService.getProjectsMap());
    }
}
