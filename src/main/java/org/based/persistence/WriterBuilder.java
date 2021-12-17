package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.io.Files;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;

@Data
public class WriterBuilder<T> {
    private  String environmentVariable;
    private  String className;
    private T type;
    public WriterBuilder() {
    }
    public WriterBuilder<T> environmentVariable(String environmentVariable){
        this.environmentVariable = environmentVariable;
        return this;
    }
    public WriterBuilder<T> className(String className){
        this.className = className;
        return this;
    }
    public WriterBuilder<T> typeClass(T type){
        this.type = type;
        return  this;
    }
    public <T> Writer<T> buildWriter(){
        return new JacksonWriter<T>(getWriter(), getFile(), (T) type);
    }
    private String getFileConfigurationPath() {
        String configuration = createDefaultFileName(getClassName());
        if (System.getenv(getEnvironmentVariable()) != null) {
            configuration = System.getenv(getEnvironmentVariable());
            return configuration;
        }
        return configuration;
    }
    private ObjectMapper getWriter() {
        switch (extension(getFileConfigurationPath())) {
            case "json":
                return new ObjectMapper();
            case "xml":
                return new XmlMapper();
        }
        return new ObjectMapper();
    }
    private String createDefaultFileName(String className) {
        String nameTemple = "%s.json";
        return String.format(nameTemple, className);
    }
    private String extension(String finalSetName) {
        return Files.getFileExtension(finalSetName);
    }
    @SneakyThrows
    private File getFile() {
        File file = new File(getFileConfigurationPath());
        verifyFileStructure(file);
        if (!file.exists()) {
            final File defaultFile = new File(getFileConfigurationPath());
            defaultFile.createNewFile();
            verifyFileStructure(defaultFile);
            return defaultFile;
        }
        return file;
    }
    private void verifyFileStructure(File defaultFile) {
        if (defaultFile.length() == 0) {
           switch (extension(getFileConfigurationPath())){
               case "json":
                   setFileStructure(defaultFile, FileAppend.JSON.getAppendType());
                   break;
               case "xml":
                   setFileStructure(defaultFile, FileAppend.XML.getAppendType());
                   break;
           }
        }
    }
    @SneakyThrows
    private void setFileStructure(File defaultFile, String defaultTag) {
        FileWriter writer = new FileWriter(defaultFile);
        writer.append(defaultTag);
        writer.flush();
        writer.close();
    }
}
