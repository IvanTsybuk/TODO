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
    public static final String PROJECT_PATH = "PX";
    public static final String TASK_PATH = "TXII";
    public static final String USER_PATH = "UX";

    public static void main(String[] args) {

        AbstractWriter abstractProjectWriter = new AbstractWriter(PROJECT_PATH, Project.class);
        final AbstractWriter projectWriter = abstractProjectWriter.getOperator();

        AbstractWriter abstractTaskWriter = new AbstractWriter(TASK_PATH, Task.class);
        final AbstractWriter taskWriter = abstractTaskWriter.getOperator();

        AbstractWriter abstractUserWriter = new AbstractWriter(USER_PATH, User.class);
        final AbstractWriter userWriter = abstractUserWriter.getOperator();

        Repository<Project> projectRepository = new Repository<>(projectWriter, new TypeReference<>() {
        });
        ProjectService projectService = new ProjectService(projectRepository);
        Repository<Task> taskRepository = new Repository<>(taskWriter, new TypeReference<>() {
        });
        TaskService taskService = new TaskService(taskRepository);
        Repository<User> userRepository = new Repository<>(userWriter, new TypeReference<>() {
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
