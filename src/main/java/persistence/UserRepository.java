package persistence;

import domain.User;
import lombok.Data;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

@Data
public class UserRepository {

    double id;
    public final Map<Integer, User> users = new HashMap();


    public void createUser(User user) {

        setId(((new Random()).nextInt(1000)));
        PrintStream var10000 = System.out;
        String var10001 = user.getUserName();
        var10000.println("New user created:\n" + var10001 + " " + user.getUserSurName() + "\nRemember your id:" + (int) getId());
        users.put((int) getId(), user);
    }



    public User getUserById(Integer id) {
        return users.get(id);
    }


}

