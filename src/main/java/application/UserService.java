package application;

import domain.User;
import persistence.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void createNewUser(String department, User user) {
        userRepository.save(department, user);

    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public User findUserByDepartment(String userByDepartment) {

        return userRepository.findByName(userByDepartment);

    }
}
