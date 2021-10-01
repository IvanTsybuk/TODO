package input;

import application.ProjectService;
import application.TaskService;
import application.UserService;
import persistence.ProjectRepository;
import persistence.TaskRepository;
import persistence.UserRepository;

import java.util.Scanner;


public class Bootstrap {


    public static void main(String[] args) {

        ProjectService projectService = new ProjectService(new ProjectRepository());
        TaskService taskService = new TaskService(new TaskRepository());
        UserService userService = new UserService(new UserRepository());
        Scanner scanner = new Scanner(System.in);

        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService, userService, scanner);


           consoleAdapter.startApp();


    }
}


