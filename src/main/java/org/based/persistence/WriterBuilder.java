package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Log4j2
public class WriterBuilder<T> {
    private String environmentVariable;
    private String className;
    private Class<T> clazz;
    public WriterBuilder() {
    }
    public @NotNull WriterBuilder<T> environmentVariable(@Nullable String environmentVariable) {
        this.environmentVariable = environmentVariable;
        return this;
    }
    public @NotNull WriterBuilder<T> useClass(@NotNull Class<T> clazz) {
        className = clazz.getSimpleName();
        this.clazz = clazz;
        return this;
    }
    public @NotNull Writer<T> build() {
        log.info("WriterBuilder: JacksonWriter created");
        log.debug(String.format("Writer:%s, File:%s, Class:%s",
                getWriter().getClass().getName(),
                getFile().getName(),
                className));
        return new JacksonWriter<>(getWriter(), getFile(), clazz);
    }
    private String getFileConfigurationPath() {
        String configuration = createDefaultFileName(className);
        final String innerVariable = System.getenv(environmentVariable);
        if (innerVariable != null) {
            configuration = innerVariable;
            return configuration;
        }
        log.debug(String.format("WriterBuilder: file configuration is set-%s", configuration));
        return configuration;
    }
    private ObjectMapper getWriter() {
        switch (getFileExtension(getFileConfigurationPath())) {
            case "json":
                return new ObjectMapper();
            case "xml":
                return new XmlMapper();
            default:
        }
        return new ObjectMapper();
    }
    private @NotNull String createDefaultFileName(@NotNull String className) {
        String nameTemple = "%s.json";
        return String.format(nameTemple, className);
    }
    private @NotNull String getFileExtension(@NotNull String fileName) {
        return Files.getFileExtension(fileName);
    }
    @SneakyThrows
    private @NotNull File getFile() {
        File file = new File(getFileConfigurationPath());
        verifyFileStructure(file);
        if (!file.exists()) {
            final File defaultFile = new File(getFileConfigurationPath());
            defaultFile.createNewFile();
            verifyFileStructure(defaultFile);
            log.debug("WriterBuilder: default File created and configured");
            return defaultFile;
        }
        return file;
    }
    private void verifyFileStructure(@NotNull File defaultFile) {
        if (defaultFile.length() == 0) {
            log.debug("WriterBuilder: file is empty. Configured");
            switch (getFileExtension(getFileConfigurationPath())) {
                case "json":
                    setFileStructure(defaultFile, FileAppend.JSON.getAppendType());
                    break;
                case "xml":
                    setFileStructure(defaultFile, FileAppend.XML.getAppendType());
                    break;
                default:
            }
        }
    }
    @SneakyThrows
    private void setFileStructure(@NotNull File defaultFile, @NotNull String defaultTag) {
        FileWriter writer = new FileWriter(defaultFile);
        writer.append(defaultTag);
        writer.flush();
        writer.close();
    }
}
