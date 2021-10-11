package org.based.persistence;
import org.based.domain.User;
import java.util.*;

public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
      users.put(user.getUserSurName(), user);
    }
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    public User findByName(String surName){
        return users.get(surName);
    }
}
