package org.based.persistence;

import com.google.common.io.Files;

public class WriterBuilder {
    public AbstractWriter getAbstractWriter(String environmentVariable, String className) {
        String configurationFilePath = getFileConfigurationPath(environmentVariable, className);
        switch (extension(configurationFilePath)) {
            case "json":
                return new JsonWriter(configurationFilePath);
            case "xml":
                return new XmlWriter(configurationFilePath);
        }
        return new JsonWriter("NULL_CASEs.json");
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
