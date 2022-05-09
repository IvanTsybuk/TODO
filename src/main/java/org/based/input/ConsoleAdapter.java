package org.based.input;

import java.util.Scanner;
import org.based.application.ProjectDataBaseService;
import org.based.application.TaskDataBaseService;
import org.based.application.UserDataBaseService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;

public class ConsoleAdapter {
    private final TaskDataBaseService taskDataBaseService;
    private final UserDataBaseService userDataBaseService;
    private final ProjectDataBaseService projectDataBaseService;
    private final Scanner scanner;
    public ConsoleAdapter(TaskDataBaseService taskDataBaseService,
                          ProjectDataBaseService projectDataBaseService,
                          UserDataBaseService userDataBaseService,
                          Scanner scanner) {
        this.taskDataBaseService = taskDataBaseService;
        this.projectDataBaseService = projectDataBaseService;
        this.userDataBaseService = userDataBaseService;
        this.scanner = scanner;
        System.out.println("Press to start");
    }
    public void startApp() {
        showCommands();
        while (true) {
            String commandNumber;
            commandNumber = scanner.next().trim();
            switch (commandNumber) {
                case "1":
                    fillUser();
                    break;
                case "2":
                    System.out.println("==getAllUsers()===");
                    System.out.println(userDataBaseService.findAll());
                    break;
                case "3":
                    System.out.println("Insert your SurName:");
                    String insertedSurname = scanner.next().trim();
                    User userByDepartment = userDataBaseService.findUserByName(insertedSurname);
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
                default:
                    System.out.println("Wrong command");
            }
        }
    }
    private void doProject() {
        System.out.println("Insert new Project's name:");
        String insertedProjectName = scanner.next();
        System.out.println("Insert  Project's description:");
        String projectDescription = scanner.next();
        projectDataBaseService.createProject(new Project(insertedProjectName, projectDescription));
        System.out.println(projectDataBaseService.getProjects());
    }
    private void takeAwayTask() {
        System.out.println("Delete task:");
        System.out.println(taskDataBaseService.findAll());
        System.out.println("Task to be deleted:");
        String deleteTask = scanner.next();
        taskDataBaseService.removeByName(deleteTask);
        System.out.println("AFTER REMOVE:\n" + taskDataBaseService.findAll());
    }
    private void fillInTask() {
        System.out.println("Task:");
        String taskName = scanner.next();
        System.out.println("Task Description:");
        String taskDescription = scanner.next();
        taskDataBaseService.createTask(new Task(taskName, taskDescription));
        System.out.println("Task List:\n" + taskDataBaseService.findAll());
    }
    private void fillUser() {
        System.out.println("=====Create new USer =====\nEnter your name:");
        String name = scanner.next().trim();
        System.out.println("\n=====Enter your surname:====");
        String surname = scanner.next().trim();
        userDataBaseService.createUser(new User(name, surname));
        System.out.println("Insert command's code");
    }
    private void showCommands() {
        System.out.println("===Select an option:===\n"
                + "0. Finish programme\n"
                + "1. Create new User\n"
                + "2. Show Users\n"
                + "3. Select User\n"
                + "4. Create new project\n"
                + "5. Fill in Task list\n"
                + "6. Remove task from task list\n"
                + "0. Exit");
    }
}
