package org.based.persistence;

import com.google.common.io.Files;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.based.input.Bootstrap;

import java.io.File;

@Getter
@Setter
public class FileOperator {
    public static final String DEFAULT = Bootstrap.DEFAULT_CONFIGURATION_PATH_SET;
    private final String fileConfig;
    private final String PROJECT_DEFAULT_FILE_JSON = "target/projectsII.json";
    private final String PROJECT_DEFAULT_FILE_XML = "target/projects.xml";
    private final String TASK_DEFAULT_FILE_JSON = "target/tasks.json";
    private final String TASK_DEFAULT_FILE_XML = "target/tasks.xml";
    private final String USER_DEFAULT_FILE_JSON = "target/users.json";
    private final String USER_DEFAULT_FILE_XML = "target/users.xml";
    private final String COMMON_DEFAULT_FILE_JSON = "target/data_log.json";

    public FileOperator(String fileConfig) {
        this.fileConfig = fileConfig;
    }
    public String getConfigPath() {
        return COMMON_DEFAULT_FILE_JSON;
    }
    public File setRepositoryFile(String defaultFilePath) {
        final String fileConfig = getFileConfig();
        if (fileConfig.equals(DEFAULT)||fileConfig.isEmpty()) {
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
    public String getExtension(File file) {
        return Files.getFileExtension(file.getName());
    }
    public boolean verifyConfig() {
        return fileConfig.isEmpty() || fileConfig.isBlank();
    }
}
