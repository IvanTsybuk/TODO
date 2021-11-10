package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.User;

import java.io.File;
import java.util.*;

public class UserRepository {

    private final Map<String, User> users;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator;
    private final TypeReference<HashMap<String, User>> typeReference=new TypeReference<>() {};

    public UserRepository(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
        users = setTaskRep();
    }
    public void save(User user) {
      users.put(user.getUserSurName(), user);
    }
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    public User findByName(String surName){
        return users.get(surName);
    }

    public Map<String, User> setTaskRep() {
        final File userRepositoryFile =
                fileOperator.setRepositoryFile(fileOperator.getUserDefaultFileJson());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(userRepositoryFile)) {
                case "json":
                    return readJsonMap(userRepositoryFile);
                case "xml":
                    return readXmlMap(userRepositoryFile);
            }
        }
        return new HashMap<>();
    }
    private Map readJsonMap(File repositoryFile) {
        return jsonOperator.readFile(repositoryFile, typeReference);
    }
    private Map readXmlMap(File repositoryFile) {
        return xmlOperator.readFile(repositoryFile, typeReference);
    }
    public void writeUser() {
        final File userRepositoryFile =
                fileOperator.setRepositoryFile(fileOperator.getUserDefaultFileJson());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(userRepositoryFile)) {
                case "json":
                    writeUserMapJson(userRepositoryFile);
                    break;
                case "xml":
                    writeUserMapXml(userRepositoryFile);
                    break;
            }
            if (fileOperator.verifyConfig()) {
                writeUserMapJson(userRepositoryFile);
            }
        }
    }
    private void writeUserMapXml(File userRepositoryFile) {
        xmlOperator.writeToFile(userRepositoryFile, users);
    }
    private void writeUserMapJson(File userRepositoryFile) {
        jsonOperator.writeToFile(userRepositoryFile, users);
    }
}
