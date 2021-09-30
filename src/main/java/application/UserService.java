package application;
import domain.User;
import persistence.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(Integer userKey, User user) {
        userRepository.save(userKey, user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserByDepartment(Integer userByDepartment) {
        return userRepository.findByName(userByDepartment);
    }
}
