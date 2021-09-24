package input;

import application.ProjectService;
import application.TaskService;
import application.UserService;
import lombok.Data;
import persistence.ProjectRepository;
import persistence.TaskRepository;
import persistence.UserRepository;

import java.util.Scanner;

@Data
public class Config {
    private final TaskService taskService = new TaskService(new TaskRepository());
    private final ProjectService projectService = new ProjectService(new ProjectRepository());
    private final UserService userService = new UserService(new UserRepository());
    private final Scanner scanner;

    public Config(Scanner scanner) {
        this.scanner = scanner;

    }


}