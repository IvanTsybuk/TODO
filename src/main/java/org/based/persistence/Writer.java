package org.based.persistence;

import com.google.common.io.Files;

public class Writer {
    private final String environmentVariable;
    private final String className;
    private final String configurationFilePath;
    public Writer(String environmentVariable, String className) {
        this.environmentVariable = environmentVariable;
        this.className = className;
        configurationFilePath = getFileConfigurationPath();
    }
    private String getFileConfigurationPath() {
        String configuration = createDefaultFileName();
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            return configuration;
        }
        return configuration;
    }
    private  String createDefaultFileName() {
        String nameTemple = "%s.json";
        return String.format(nameTemple, className);
    }
    private String extension(String finalSetName){
        return Files.getFileExtension(finalSetName);
    }
    public AbstractWriter getAbstractWriter() {
        switch (extension(configurationFilePath)) {
            case "json":
                return new JsonWriter(configurationFilePath);
            case "xml":
                return new XmlWriter(configurationFilePath);
        }
        return new JsonWriter("NULL_CASEs.json");
    }
}
