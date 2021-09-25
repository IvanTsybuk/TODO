package input;

import application.ProjectService;
import application.TaskService;
import application.UserService;
import domain.Project;
import domain.Task;
import domain.User;


import java.util.Scanner;

public class ConsoleAdapter {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;
    private final Scanner scanner;


    public ConsoleAdapter(TaskService taskService,ProjectService projectService, UserService userService,
                          Scanner scanner) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
        this.scanner = scanner;

    }

    {
        System.out.println("Press to start");
    }

    String commandNumber = null;

    public void startApp() {
        showCommands();

        while (commandNumber != "0") {
            commandNumber = scanner.next().trim();

            switch (commandNumber) {

                case "1":
                    fillUser();
                    break;
                case "2":
                    System.out.println("==getAllUsers()===");
                    System.out.println(userService.showAllUsers());
                    break;
                case "3":
                    System.out.println("Insert your department:");
                    String insertedDepartment = scanner.next();
                    User userByDepartment = userService.showUserByDepartment(insertedDepartment);
                    System.out.println(userByDepartment.toString());
                    System.out.println("Press 4 to get project menu or 0 to exit");
                    break;
                case "4":
                    doProject();
                    break;
                case "5":
                    fillInTask();
                    System.out.println("To add new task, don't forget to insert 5");
                    break;
                case "6":
                    takeAwayTask();
                    break;
                case "0":
                    return;

            }
        }
    }


    private void doProject() {
        System.out.println("Insert new Project's name:");
        String insertedProjectName = scanner.next();
        System.out.println("Insert  Project's description:");
        String insertedProjectDescription = scanner.next();
        System.out.println("Insert  Project's status:");
        String projectStatus = scanner.next();

       projectService.createProject(new Project(insertedProjectName, insertedProjectDescription), projectStatus);

        System.out.println(projectService.findAll());
    }

    private void takeAwayTask() {
        System.out.println("Delete task:");

        System.out.println(taskService.getTaskList());
        System.out.println("Task to be deleted:");
        String deleteTask = scanner.next();
        taskService.removeSelectedTask(deleteTask);
        System.out.println("AFTER REMOVE:\n" + taskService.getTaskList());

    }

    private void fillInTask() {
        System.out.println("Task:");
        String taskName = scanner.next();

        System.out.println("Task Description:");
        String taskDescription = scanner.next();
       taskService.addTask(new Task(taskName, taskDescription));

        System.out.println("Task List:\n" + taskService.getTaskList());
    }

    private void fillUser() {
        System.out.println("=====Enter Department====");
        String departmentName = scanner.next();
        System.out.println("=====Create new USer =====\nEnter your name:");
        String name = scanner.next().trim();
        System.out.println("\n=====Enter your surname:====");
        String surname = scanner.next().trim();
       userService.createNewUser(departmentName, new User(name, surname));

        System.out.println("Insert command's code");
    }

    private void showCommands() {
        System.out.println("===Select an option:===\n" +
                "0. Finish programme\n" +
                "1. Create new User\n" +
                "2. Show Users\n" +
                "3. Select User\n" +
                "4. Create new project\n" +
                "5. Fill in Task list\n" +
                "6. Remove task from task list\n");
    }

}
