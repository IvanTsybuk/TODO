package application;

import domain.User;
import lombok.Data;
import persistence.UserRepository;

@Data
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

       this.userRepository = userRepository;
    }

    public void createNewUser(String str, User user) {

        getUserRepository().getUsers().put(str, user);
    }

    public String showAllUsers() {

        return getUserRepository().getUsers().toString();
    }

    public User showUserByDepartment(String str) {

        return getUserRepository().getUsers().get(str);

    }
}
