package org.based.input;

import java.util.Scanner;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.Repository;
import org.based.persistence.Writer;

public class Context {
    private final Repository<Project> projectRepository;
    private final Repository<Task> taskRepository;
    private final Repository<User> userRepository;
    private final ConsoleAdapter consoleAdapter;
    public Context(Writer<Project> projectWriter,
                   Writer<User> userWriter,
                   Writer<Task> taskWriter) {
        projectRepository = new Repository<>(projectWriter);
        taskRepository = new Repository<>(taskWriter);
        userRepository = new Repository<>(userWriter);
        ProjectService projectService = new ProjectService(projectRepository);
        TaskService taskService = new TaskService(taskRepository);
        UserService userService = new UserService(userRepository);
        Scanner scanner = new Scanner(System.in);
        consoleAdapter = new ConsoleAdapter(taskService, projectService, userService, scanner);
    }
    public void startApp() {
        consoleAdapter.startApp();
    }
    public void terminate() {
        projectRepository.saveToFile();
        taskRepository.saveToFile();
        userRepository.saveToFile();
    }
}
