package org.based.persistence;

import com.google.common.io.Files;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;

@Getter
@Setter
public class FileOperator {
    private final String fileConfig;
    private final String PROJECT_DEFAULT_FILE_JSON = "target/projectsII.json";
    private final String PROJECT_DEFAULT_FILE_XML = "target/projects.xml";
    private final String TASK_DEFAULT_FILE_JSON = "target/tasks.json";
    private final String TASK_DEFAULT_FILE_XML = "target/tasks.xml";
    private final String USER_DEFAULT_FILE_JSON = "target/users.json";
    private final String USER_DEFAULT_FILE_XML = "target/users.xml";

    public FileOperator(String fileConfig) {
        this.fileConfig = fileConfig;
    }
    public String showConfigPath() {
        String projectConfig = System.getenv(fileConfig);
        if (System.getenv(fileConfig) == null || projectConfig.isEmpty()) {
            return "Default File was set";
        }
        return projectConfig;
    }

    public File setRepositoryFile(String defaultFilePath) {
        final String fileConfig = getFileConfig();
        if (fileConfig.equals("DEFAULT")) {
            return createDefaultRepositoryFile(defaultFilePath);
        }
        return new File(fileConfig);
    }
    @SneakyThrows
    public File createDefaultRepositoryFile(String defaultFilePath) {
        File defaultFile = new File(defaultFilePath);
        if (!defaultFile.exists()) {
            defaultFile.createNewFile();
        }
        return defaultFile;
    }
    //ByGuava
    public String getExtension(File file) {
        return Files.getFileExtension(file.getName());
    }
    public boolean verifyConfig() {
        return fileConfig.isEmpty() || fileConfig.isBlank();
    }
}
