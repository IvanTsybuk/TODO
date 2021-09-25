package application;

import domain.User;
import lombok.Data;
import persistence.UserRepository;

import java.util.List;

@Data
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

       this.userRepository = userRepository;
    }

    public void createNewUser(String department, User user) {
       userRepository.save(department, user);

    }

    public List<User> showAllUsers() {

        return userRepository.show();
    }

    public User showUserByDepartment(String userByDepartment) {

        return userRepository.showSelected(userByDepartment);

    }
}
