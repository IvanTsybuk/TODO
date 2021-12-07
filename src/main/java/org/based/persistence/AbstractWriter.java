package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.io.Files;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Data
public class AbstractWriter {
    protected final String environmentVariable;
    protected final Class clazz;
    protected final File fileConfigPath;

    public AbstractWriter(String environmentVariable, Class clazz) {
        this.environmentVariable = environmentVariable;
        this.clazz = clazz;
        fileConfigPath = getFile(getFileConfigurationPath(environmentVariable, clazz));
        }


    protected String getFileConfigurationPath(String environmentVariable, Class clazz) {
        String configuration = createDefaultFileName(clazz);
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            return configuration;
        }
        return configuration;
    }
    protected  String createDefaultFileName(Class clazz) {
        String nameTemple = "target/%s.json";
        final String SIMPLE_CLASS_NAME = clazz.getSimpleName();
        return String.format(nameTemple, SIMPLE_CLASS_NAME);
    }

    @SneakyThrows
    protected File getFile(String configurationFilePath) {
        File file = new File(configurationFilePath);
        if (!file.exists()) {
            final File defaultFile = new File(configurationFilePath);
            defaultFile.createNewFile();
            setJson(defaultFile);
            return defaultFile;
        }
        return file;
    }

    @SneakyThrows
    protected void setJson(File defaultFile)  {
        if (defaultFile.length() == 0) {
            FileWriter writer = new FileWriter(defaultFile);
            writer.append("{}");
            writer.flush();
            writer.close();
        }
    }

    protected String getExtension(File file) {
        return Files.getFileExtension(file.getName());
    }

    public AbstractWriter getOperator() {
        if (fileConfigPath.exists()) {
            switch (getExtension(fileConfigPath)) {
                case "json":
                    return new JsonWriter(environmentVariable, clazz);
                case "xml":
                    return new XmlWriter(environmentVariable, clazz);
            }
        }
        return new AbstractWriter(environmentVariable, clazz);
    }

    @SneakyThrows
    protected void writeToFile (Map < ?, ?>mapToFile){
    }

    protected Map<?, ?> readFile (TypeReference <?>typeReference){
        return new HashMap<>();
    }
    }
