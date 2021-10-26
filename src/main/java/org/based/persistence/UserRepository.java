package org.based.persistence;

import org.based.domain.User;
import java.util.*;

public class UserRepository {

    private final Map<String, User> users;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator = new FileOperator();

    public UserRepository() {
        users = readUsersFromJson();
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

    public void writeUserMapToJson(){
        jsonOperator.writeToFile(fileOperator.getFileUserJSon(), users);
    }
    public void writeUsersToXml(){
        xmlOperator.writeToFile(fileOperator.getFileUserXml(), users);
    }
    public Map<String, User> readUsersFromJson(){
                return new HashMap(jsonOperator.readFile(fileOperator.getFileUserJSon()));
    }
    public Map<String, User> readUsersFromXml(){
        return new HashMap(xmlOperator.readFile(fileOperator.getFileUserXml()));
    }
}
