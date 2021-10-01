package savelogic;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class FileOperator {
   private final File fileProjects = new File("target/projects.json");
    private final File fileTasks = new File("target/tasks.json");
    private final File fileUsers = new File("target/users.json");


    public FileOperator() {
    }

    public File getFileProjects() throws IOException {
        if (!fileProjects.exists()){fileProjects.createNewFile();}
        return fileProjects;
    }

    public File getFileTasks() throws IOException {
        if (!fileTasks.exists()){fileTasks.createNewFile();}
        return fileTasks;
    }

    public File getFilUsers() throws IOException {
        if (!fileUsers.exists()){fileUsers.createNewFile();}
        return fileUsers;
    }
}