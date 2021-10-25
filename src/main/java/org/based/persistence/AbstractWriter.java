package org.based.persistence;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

@Data
public abstract class AbstractWriter {
    private final FileOperator fileOperator = new FileOperator();

    abstract void writeToFile(Map <?,?> mapToFile);
    abstract Map readFile();

    @SneakyThrows
    protected File getXmlProjectFile() {
        return getFileOperator().getFileProjectsXml();
    }

    @SneakyThrows
    protected File getJsonProjectFile() {
        return getFileOperator().getFileProjects();
    }
}
