package input;

import application.ProjectService;
import application.TaskService;
import application.UserService;
import persistence.ProjectRepository;
import persistence.TaskRepository;
import persistence.UserRepository;
import savelogic.JsonWriter;

import java.util.Scanner;


public class Bootstrap {


    public static void main(String[] args) {

        ProjectService projectService = new ProjectService(new ProjectRepository());
        TaskService taskService = new TaskService(new TaskRepository());
        UserService userService = new UserService(new UserRepository());
        JsonWriter jsonWriter = new JsonWriter(projectService, taskService, userService);
        Scanner scanner = new Scanner(System.in);

        ConsoleAdapter consoleAdapter = new ConsoleAdapter(taskService, projectService, userService, jsonWriter, scanner);

       try {
           consoleAdapter.startApp();
       }catch (Exception e){e.printStackTrace();}

    }
}


