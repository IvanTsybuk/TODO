package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Data
public class AbstractOperator {
    private final File fileConfigPath;

    public AbstractOperator(File fileConfigPath) {
        this.fileConfigPath = fileConfigPath;
    }

    @SneakyThrows
    protected void writeToFile (Map < ?, ?>mapToFile){
    }

    protected Map<?, ?> readFile (TypeReference <?>typeReference){
        return new HashMap<>();
    }
    }
