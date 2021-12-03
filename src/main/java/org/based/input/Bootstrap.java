package org.based.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.io.Files;
import lombok.SneakyThrows;
import org.based.application.ProjectService;
import org.based.application.TaskService;
import org.based.application.UserService;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.*;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Bootstrap {
    public static final String PROJECT_PATH = "PXI";
    public static final String TASK_PATH = "TX";
    public static final String USER_PATH = "UXII";

    public static void main(String[] args) {

        System.out.println("Project variable initialization");
        final File projectFile = getFile(getFileConfigurationPath(PROJECT_PATH, Project.class));
        AbstractOperator abstractProject = getOperator(projectFile);

        System.out.println("initialize task variable");
        final File taskFile = getFile(getFileConfigurationPath(TASK_PATH, Task.class));
        AbstractOperator abstractTask = getOperator(taskFile);

        System.out.println("initialize user variable");
        final File userFile = getFile(getFileConfigurationPath(USER_PATH, User.class));
        AbstractOperator abstractUser = getOperator(userFile);

        Repository<Project> projectRepository = new Repository<>(abstractProject, new TypeReference<>() {
        });
        ProjectService projectService = new ProjectService(projectRepository);
        Repository<Task> taskRepository = new Repository<>(abstractTask, new TypeReference<>() {
        });
        TaskService taskService = new TaskService(taskRepository);
        Repository<User> userRepository = new Repository<>(abstractUser, new TypeReference<>() {
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

    private static String getFileConfigurationPath(String environmentVariable, Object targetNameObject) {
        String configuration = createDefaultFileName(targetNameObject);
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            System.out.println(configuration);
            return configuration;
        }
        System.out.println(configuration + " was set");
        return configuration;
    }
    public static String createDefaultFileName(Object targetNameObject) {
        String nameTemple = "target/%s.json";
        final String CLASS_NAME = String.valueOf(targetNameObject);
        return String.format(nameTemple, CLASS_NAME);
    }

    @SneakyThrows
    private static File getFile(String configurationFilePath) {
        File file = new File(configurationFilePath);
        if (!file.exists()) {
            final File defaultFile = new File(configurationFilePath);
            defaultFile.createNewFile();
            if (defaultFile.length() == 0) {
                FileWriter writer = new FileWriter(defaultFile);
                writer.append("{}");
                writer.flush();
                writer.close();
            }
            return defaultFile;
        }
        return file;
    }

    private static String getExtension(File file) {
        return Files.getFileExtension(file.getName());
    }

    private static AbstractOperator getOperator(File fileExtension) {
        if (fileExtension.exists()) {
            switch (getExtension(fileExtension)) {
                case "json":
                    return new JsonOperator(fileExtension);
                case "xml":
                    return new XmlOperator(fileExtension);
            }
        }
        return new AbstractOperator(fileExtension);
    }
}
