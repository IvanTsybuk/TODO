package org.based.persistence;

import java.util.Map;

public interface Writer<T> {
    static <T> WriterBuilder<T> builder() {
        return new WriterBuilder<>();
    }
    void writeToFile(Map<String, T> map);
    Map<String, T> readFile();
}
