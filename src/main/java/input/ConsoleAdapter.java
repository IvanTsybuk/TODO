package input;

import application.TaskService;
import domain.Project;
import domain.Task;
import domain.User;
import persistence.ProjectRepository;
import persistence.UserRepository;

import java.util.Scanner;

public class ConsoleAdapter {
    UserRepository userRepository = new UserRepository();

    TaskService taskService = new TaskService();
    ProjectRepository projectRepository = new ProjectRepository();

    Scanner scanner = new Scanner(System.in);
     {
         showCommands();


    int inserted = scanner.nextInt();

        while(inserted != 0) {
        if (inserted == 1) {
            fillUser(scanner, userRepository);
            inserted = scanner.nextInt();
        }

        if (inserted == 2) {
            System.out.println("==getAllUsers()===");

            System.out.println(userRepository.getUsers().toString());

            inserted = scanner.nextInt();
        }

        if (inserted == 3) {
            System.out.println("Insert your id:");
            int insId = scanner.nextInt();
            User userById = userRepository.getUserById(insId);
            System.out.println(userById.toString());
            System.out.println("Press 4 to get project menu or 0 to exit");
            inserted = scanner.nextInt();
        }

        if (inserted == 4) {
            System.out.println("Projects commands:\n5.Create new task\n");
            System.out.println("ATTENTION: Maybe Doesn't work");

            System.out.println("Insert your id:");
            int insId = scanner.nextInt();
            User userById = userRepository.getUserById(insId);
            System.out.println(userById.toString());

            System.out.println("Insert new Project's name:");

            String insertedProjectName = scanner.next();
            projectRepository.createProject(userById, new Project(insertedProjectName));

            System.out.println(projectRepository.getProjects().toString());

            inserted = scanner.nextInt();
        }

        if (inserted == 5) {
            fillInTask(scanner, taskService);
            System.out.println("To add new task, don't forget to insert 5");
            inserted = scanner.nextInt();
        }

        if (inserted == 6) {
            takeAwayTask(scanner, taskService);

            inserted = scanner.nextInt();
        }
    }

        System.out.println("Finish:");
}

    private static void takeAwayTask(Scanner scanner, TaskService taskService) {
        System.out.println("Delete task:");

        System.out.println(taskService.taskList);
        System.out.println("Task to be deleted:");
        String deleteTask = scanner.next();
        taskService.taskList.removeIf(task -> task.getName().equals(deleteTask));
        System.out.println("AFTER REMOVE:\n"+ taskService.taskList);

    }

    private static void fillInTask(Scanner scanner, TaskService taskService) {
        System.out.println("Task:");

        String s = scanner.next();

        taskService.taskList.add(new Task(s));

        System.out.println(taskService.taskList);
    }

    public static void  fillUser(Scanner scanner, UserRepository userRepository) {
        System.out.println("=====Create new USer =====\nEnter your name:");
        String name = scanner.next().trim();
        System.out.println("\n=====Enter your surname:====");
        String surname = scanner.next().trim();
        userRepository.createUser(new User(name, surname));
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
