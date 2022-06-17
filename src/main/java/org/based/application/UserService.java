package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String ALREADY_EXIST = "User with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "User with name - %s, is not found";
    private final Repository<User> userRepository;
    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }
    public void save(final User user) {
        userRepository.findByName(user.getName())
                .ifPresentOrElse(a -> throwException(a.getName()),
                        () -> userRepository.save(user));
    }
    private void throwException(String entityName) {
        throw new EntityAlreadyExistsException(String.format(ALREADY_EXIST, entityName));
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByName(final String name) {
        return userRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }
    public void update(User user) {
        userRepository.update(user);
    }
}
