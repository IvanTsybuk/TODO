package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Repository<T> {

    protected final Map<String, T> repositoryMap;
    protected final FileOperator fileOperator;
    protected final XmlOperator xmlOperator = new XmlOperator();
    protected final JsonOperator jsonOperator = new JsonOperator();
    protected TypeReference<HashMap<String, T>> typeReference;

    public Repository(FileOperator fileOperator, TypeReference<HashMap<String, T>> typeReference) {
        this.fileOperator = fileOperator;
        this.typeReference = typeReference;
        repositoryMap = setRep();
    }
    public void show() {
        System.out.println(typeReference.getType().toString());
    }
    public Map<String, T> setRep() {
        final File taskRepositoryFile =
                fileOperator.setRepositoryFile(getDefaultFileRepositoryPath());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtension(taskRepositoryFile)) {
                case "json":
                    return readJsonMap(taskRepositoryFile);
                case "xml":
                    return readXmlMap(taskRepositoryFile);
            }
        }
        return new HashMap<>();
    }
    protected String getDefaultFileRepositoryPath() {
        return fileOperator.showConfigPath();
    }
    protected Map readJsonMap(File repositoryFile) {
        return jsonOperator.readFile(repositoryFile, typeReference);
    }
    protected Map readXmlMap(File repositoryFile) {
        return xmlOperator.readFile(repositoryFile, typeReference);
    }
    public void write() {
        final File taskRepositoryFile =
                fileOperator.setRepositoryFile(getDefaultFileRepositoryPath());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtension(taskRepositoryFile)) {
                case "json":
                    writeMapJson(taskRepositoryFile);
                    break;
                case "xml":
                    writeMapXml(taskRepositoryFile);
                    break;
            }
            if (fileOperator.verifyConfig()) {
                writeMapJson(taskRepositoryFile);
            }
        }
    }
    protected void writeMapXml(File taskRepositoryFile) {
        xmlOperator.writeToFile(taskRepositoryFile, repositoryMap);
    }
    protected void writeMapJson(File taskRepositoryFile) {
        jsonOperator.writeToFile(taskRepositoryFile, repositoryMap);
    }
}
