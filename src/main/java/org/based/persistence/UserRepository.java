package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.based.domain.User;

import java.util.*;

public class UserRepository {

    @JsonDeserialize( using = BodyDeserializer.class)
    private final Map<String, User> users;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator = new FileOperator();
    private final TypeReference<HashMap<String, User>> typeReference=new TypeReference<>() {};

    public UserRepository() {
        users = readUsersFromJson();
//        users = readUsersFromXml();
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
    public Map<String, User> readUsersFromJson() {
        return jsonOperator.readFile(fileOperator.getFileUserJSon(), typeReference);
    }
    public Map readUsersFromXml(){
        return xmlOperator.readFile(fileOperator.getFileUserXml(), typeReference);
    }
}
