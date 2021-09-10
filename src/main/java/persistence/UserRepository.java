package persistence;

import domain.User;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class UserRepository {
    private static double id;
    public static final Map<Integer, User> users = new HashMap();
    User user;

    public UserRepository() {
    }

    public static void createUser(User user) {

        setId((double)((new Random()).nextInt(1000) + 0));
        PrintStream var10000 = System.out;
        String var10001 = user.getUserName();
        var10000.println("New user created:\n" + var10001 + " " + user.getUserSurName() + "\nRemember your id:" + getId());
        users.put((int)getId(), user);
    }

    public void getAllUsers() {
        Iterator var1 = users.entrySet().iterator();

        while(var1.hasNext()) {
            Entry entry = (Entry)var1.next();
            PrintStream var10000 = System.out;
            Object var10001 = entry.getKey();
            var10000.println(var10001 + "--" + entry.getValue());
        }

    }

    public User getUserById(Integer id) {
        return (User)users.get(id);
    }

    public List<User> showAllUsers() {
        return null;
    }

    public static double getId() {
        return id;
    }

    public static void setId(double id) {
        UserRepository.id = id;
    }
}

