package org.based.persistence;

import com.google.common.io.Files;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;

@Getter
@Setter
public class  FileOperator {
    private final String fileConfig;
    private final String ProjectDefaultFileJson = "target/projectsII.json";
    private final String ProjectDefaultFileXml = "target/projects.xml";
    private final String TaskDefaultFileJson = "target/tasks.json";
    private final String TaskDefaultFileXml = "target/tasks.xml";
    private final String UserDefaultFileJson = "target/users.json";
    private final String UserDefaultFileXml = "target/users.xml";

    public FileOperator(String fileConfig) {
        this.fileConfig = fileConfig;
    }

    public String showConfigPath(){
            String projectConfig = System.getenv(fileConfig);
            if(System.getenv(fileConfig)==null||projectConfig.isEmpty()){
             return "Default File was set";
            }
     return projectConfig.toString();
    }

    public File setRepositoryFile(String defaultFilePath){
        final String fileConfig = getFileConfig();
        if (System.getenv(fileConfig) ==null){
            return createDefaultRepositoryFile(defaultFilePath);
        }
       return new File(System.getenv(fileConfig));
    }

    @SneakyThrows
    public File createDefaultRepositoryFile(String defaultFilePath) {
        File defaultFile = new File(defaultFilePath);
        if (!defaultFile.exists()){
            defaultFile.createNewFile();}
        return defaultFile;
    }
       public String getExtensionByGuava(File file) {
        return Files.getFileExtension(file.getName());
    }
    public boolean verifyConfig(){
        return fileConfig.isEmpty() || fileConfig.isBlank();
    }
}
