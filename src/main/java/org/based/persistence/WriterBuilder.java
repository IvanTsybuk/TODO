package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.io.Files;

public class WriterBuilder {
    private final String environmentVariable;
    private final String className;
    public WriterBuilder(String environmentVariable, String className) {
        this.environmentVariable = environmentVariable;
        this.className = className;
    }
    public AbstractWriter build() {
        String configurationFilePath = getFileConfigurationPath(environmentVariable, className);
        switch (extension(configurationFilePath)) {
            case "json":
                return new JsonWriter<>(configurationFilePath, new ObjectMapper());
            case "xml":
                return new XmlWriter<>(configurationFilePath, new XmlMapper());
        }
        return new JsonWriter<>("NULL_CASEs.json", new ObjectMapper());
    }
    private String getFileConfigurationPath(String environmentVariable, String className) {
        String configuration = createDefaultFileName(className);
        if (System.getenv(environmentVariable) != null) {
            configuration = System.getenv(environmentVariable);
            return configuration;
        }
        return configuration;
    }
    private String createDefaultFileName(String className) {
        String nameTemple = "%s.json";
        return String.format(nameTemple, className);
    }
    private String extension(String finalSetName) {
        return Files.getFileExtension(finalSetName);
    }
}
