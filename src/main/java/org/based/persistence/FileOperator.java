package org.based.persistence;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class FileOperator {
    private final File fileProjects = new File("target/projects.json");

    public File getFileProjects() throws IOException {
        if (!fileProjects.exists()){fileProjects.createNewFile();}
        return fileProjects;
    }
}
