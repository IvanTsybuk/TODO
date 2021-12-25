package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import lombok.SneakyThrows;

public class WriterBuilder<T> {
    private String environmentVariable;
    private String className;
    private Class<T> clazz;
    public WriterBuilder() {
    }
    public WriterBuilder<T> environmentVariable(String environmentVariable) {
        this.environmentVariable = environmentVariable;
        return this;
    }
    public WriterBuilder<T> useClass(Class<T> clazz) {
        className = clazz.getSimpleName();
        this.clazz = clazz;
        return this;
    }
    public Writer<T> build() {
        return new JacksonWriter<>(getWriter(), getFile(), clazz);
    }
    private String getFileConfigurationPath() {
        String configuration = createDefaultFileName(className);
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            return configuration;
        }
        return configuration;
    }
    private ObjectMapper getWriter() {
        switch (getFileExtension(getFileConfigurationPath())) {
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
    private String getFileExtension(String fileName) {
        return Files.getFileExtension(fileName);
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
            switch (getFileExtension(getFileConfigurationPath())) {
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
