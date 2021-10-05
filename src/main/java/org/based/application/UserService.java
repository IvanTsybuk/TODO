package org.based.application;
import org.based.domain.User;
import org.based.persistence.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserBySurName(String userSurname) {
        return userRepository.findByName(userSurname);
    }
}
