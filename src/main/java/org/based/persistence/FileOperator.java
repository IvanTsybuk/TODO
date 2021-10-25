package org.based.persistence;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class FileOperator {
    private final File fileProjects = new File("target/projects.json");
    private final File fileProjectsXml = new File("target/projects.xml");
    private final File fileTasks = new File("target/projects.json");

    public File getFileProjects() throws IOException {
        if (!fileProjects.exists()){fileProjects.createNewFile();}
        return fileProjects;
    }
    public File getFileProjectsXml() throws IOException {
        if (!fileProjects.exists()){fileProjects.createNewFile();}
        return fileProjectsXml;
    }
    public File getFileTasks() throws IOException {
        if (!fileProjects.exists()){fileProjects.createNewFile();}
        return fileTasks;
    }
}
