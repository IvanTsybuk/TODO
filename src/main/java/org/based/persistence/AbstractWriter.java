package org.based.persistence;

import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
public abstract class AbstractWriter {
    private final FileOperator fileOperator = new FileOperator();

    abstract void writeToFile(File file, Map <?,?> mapToFile);
    abstract Map <?,?> readFile(File file);
}
