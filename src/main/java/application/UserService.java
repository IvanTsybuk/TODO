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
       userRepository.save(str, user);

    }

    public String showAllUsers() {

        return userRepository.show();
    }

    public User showUserByDepartment(String str) {

        return userRepository.showSelected(str);

    }
}
