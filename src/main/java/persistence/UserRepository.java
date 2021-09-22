package persistence;

import domain.User;

import lombok.Getter;

import java.util.*;

@Getter
public class UserRepository {

    @Getter
    private final Map<String, User> users = new HashMap<>();


    public void save(String str, User user) {

        getUsers().put(str, user);
    }

    public String show() {

        return getUsers().toString();
    }

    public User showSelected (String str){
        return getUsers().get(str);
    }

}

