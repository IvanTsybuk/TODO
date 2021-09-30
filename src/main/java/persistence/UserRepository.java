package persistence;
import domain.User;
import java.util.*;

public class UserRepository {

    private final Map<Integer, User> users = new HashMap<>();

    public void save(Integer userKey, User user) {
      users.put(userKey, user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findByName(Integer name){
        return users.get(name);
    }

}

