package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.User;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {
    private static final String ALREADY_EXIST = "User with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "User with name - %s, is not found";
    private final Repository<User> userRepository;
    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }
    public void save(@NotNull final User user) {
        log.info("Saving new user");
        userRepository.findByName(user.getName())
                .ifPresent(a -> {
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        userRepository.save(user);
    }
    @Nullable
    public List<User> findAll() {
        log.info("Finding all tasks");
        return userRepository.findAll();
    }
    public User findByName(@NotNull final String name) {
        return userRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void deleteByName(@NotNull String name) {
        log.info("Deleting a user");
        userRepository.deleteByName(name);
    }
    public void update(@NotNull User user) {
        log.info("Updating a task");
        userRepository.findByName(user.getName()).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, user.getName())));
        userRepository.update(user);
    }
}
