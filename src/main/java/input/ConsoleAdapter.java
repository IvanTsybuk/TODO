package input;

import domain.Project;
import domain.Task;
import domain.User;

public class ConsoleAdapter {

    final Config config;

    {
        System.out.println("Press to start");
    }

    public ConsoleAdapter(Config config) {
        this.config = config;
    }


    public void startApp() {

        showCommands();
        String commandNumber = null;

        while (commandNumber != "0") {
            commandNumber = config.getScanner().next();
            switch (commandNumber) {

                case "1":
                    fillUser();
                    break;
                case "2":
                    System.out.println("==getAllUsers()===");
                    System.out.println(config.getUserService().showAllUsers());
                    break;
                case "3":
                    System.out.println("Insert your department:");
                    String insertedDepartment = config.getScanner().next();
                    User userByDepartment = config.getUserService().showUserByDepartment(insertedDepartment);
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
                    System.out.println("Finish:");
                    commandNumber = "0";
                    break;
            }

        }
    }


    private void doProject() {
        System.out.println("Insert new Project's name:");
        String insertedProjectName = config.getScanner().next();
        System.out.println("Insert  Project's description:");
        String insertedProjectDescription = config.getScanner().next();
        System.out.println("Insert  Project's status:");
        String projectStatus = config.getScanner().next();

        config.getProjectService().createProject(new Project(insertedProjectName, insertedProjectDescription), projectStatus);

        System.out.println(config.getProjectService().showProjects());
    }

    private void takeAwayTask() {
        System.out.println("Delete task:");

        System.out.println(config.getTaskService().getTaskList());
        System.out.println("Task to be deleted:");
        String deleteTask = config.getScanner().next();
        config.getTaskService().removeSelectedTask(deleteTask);
        System.out.println("AFTER REMOVE:\n" + config.getTaskService().getTaskList());

    }

    private void fillInTask() {
        System.out.println("Task:");
        String taskName = config.getScanner().next();

        System.out.println("Task:");
        String taskDescription = config.getScanner().next();
        config.getTaskService().addTask(new Task(taskName, taskDescription));


        System.out.println("Task List:\n" + config.getTaskService().getTaskList());
    }

    private void fillUser() {
        System.out.println("=====Enter Department====");
        String departmentName = config.getScanner().next();
        System.out.println("=====Create new USer =====\nEnter your name:");
        String name = config.getScanner().next().trim();
        System.out.println("\n=====Enter your surname:====");
        String surname = config.getScanner().next().trim();
        config.getUserService().createNewUser(departmentName, new User(name, surname));

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
