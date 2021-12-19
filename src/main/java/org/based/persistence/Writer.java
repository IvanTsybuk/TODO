package org.based.persistence;

import java.util.Map;

public interface Writer<T> {
    static WriterBuilder builder() {
        return new WriterBuilder();
    }
    void writeToFile(Map<String, T> map);
    Map<String, T> readFile();
}
