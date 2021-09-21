package application;

import domain.User;
import persistence.UserRepository;

public class UserService {

    UserRepository userRepository;

    public UserService() {

        userRepository = new UserRepository();
    }

    public void createNewUser(String str, User user) {

        userRepository.getUsers().put(str, user);
    }

    public String showAllUsers() {

        return userRepository.getUsers().toString();
    }

    public User showUserByDepartment(String str) {

        return userRepository.getUsers().get(str);

    }
}
