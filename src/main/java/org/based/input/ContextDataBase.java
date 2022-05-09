package org.based.input;

import java.util.Scanner;
import org.based.application.ProjectDataBaseService;
import org.based.application.TaskDataBaseService;
import org.based.application.UserDataBaseService;
import org.based.persistence.ConnectionConfiguration;
import org.based.persistence.ProjectDataBaseRepository;
import org.based.persistence.TaskDataBaseRepository;
import org.based.persistence.UserDataBaseRepository;


public class ContextDataBase {
    private final ConsoleAdapter consoleAdapter;
    public ContextDataBase(ConnectionConfiguration connectionConfiguration) {
        ProjectDataBaseRepository projectDataBaseRepository =
                new ProjectDataBaseRepository(connectionConfiguration);
        TaskDataBaseRepository taskDataBaseRepository =
                new TaskDataBaseRepository(connectionConfiguration);
        UserDataBaseRepository userDataBaseRepository =
                new UserDataBaseRepository(connectionConfiguration);
        ProjectDataBaseService projectDataBaseService =
                new ProjectDataBaseService(projectDataBaseRepository);
        TaskDataBaseService taskDataBaseService = new TaskDataBaseService(taskDataBaseRepository);
        UserDataBaseService userDataBaseService = new UserDataBaseService(userDataBaseRepository);
        Scanner scanner = new Scanner(System.in);
        consoleAdapter = new ConsoleAdapter(taskDataBaseService,
                projectDataBaseService,
                userDataBaseService,
                scanner);
    }
    public void startApp() {
        consoleAdapter.startApp();
    }
}
