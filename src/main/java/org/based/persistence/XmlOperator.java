package org.based.persistence;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlOperator extends AbstractWriter {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    @SneakyThrows
    void writeToFile(File file, Map <?, ?> mapToFile) {
        xmlMapper.writeValue(file, mapToFile);
    }

    @Override
    @SneakyThrows
    Map<?, ?> readFile(File file) {
        return xmlMapper.readValue(file, HashMap.class);
    }
}
