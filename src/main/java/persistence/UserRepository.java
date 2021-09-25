package persistence;

import domain.User;
import java.util.*;

public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    public void save(String str, User user) {

      users.put(str, user);
    }

    public List<User> findAll() {

        return new ArrayList<>(users.values());
    }

    public User findByName(String name){
        return users.get(name);
    }

}

