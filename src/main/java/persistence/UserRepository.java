package persistence;

import domain.User;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class UserRepository {

    @Getter
    private final Map<String, User> users = new HashMap<>();


    public void save(String str, User user) {

        getUsers().put(str, user);
    }

    public List<User> show() {


        return users.values().stream().collect(Collectors.toList());
    }

    public User showSelected (String str){
        return getUsers().get(str);
    }

}

