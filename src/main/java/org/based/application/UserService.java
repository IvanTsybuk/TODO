package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.User;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {
    private static final String ALREADY_EXIST = "User with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "User with name - %s, is not found";
    @NotNull
    private final Repository<User> userRepository;
    public UserService(@NotNull Repository<User> userRepository) {
        log.info("UserService initialization");
        this.userRepository = userRepository;
    }
    public void save(@NotNull final User user) {
        log.debug(String.format("Save new user:%s", user));
        userRepository.findByName(user.getName())
                .ifPresent(a -> {
                    log.error(String.format("Save Exception-user:%s. ALREADY_EXIST", user));
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        userRepository.save(user);
    }
    @NotNull
    public List<User> findAll() {
        log.debug("Find all tasks");
        return userRepository.findAll();
    }
    @NotNull
    public User findByName(@NotNull final String name) {
        log.debug(String.format("find user by name: %s", name));
        return userRepository.findByName(name).orElseThrow(
                () -> {
                    log.error(String.format("FindByName Exception: NOT_FOUND user name: %s", name));
                    throw  new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name));
                });
    }
    public void deleteByName(@NotNull final String name) {
        log.debug(String.format("delete user by name: %s", name));
        userRepository.deleteByName(name);
    }
    public void update(@NotNull final User user) {
        log.debug(String.format("Update task: %s", user));
        userRepository.findByName(user.getName()).orElseThrow(
                () -> {
                    log.error(String.format("Update Exception: NOT_FOUND-user name: %s",
                            user.getName()));
                    throw new EntityNotFoundException(
                            String.format(ENTITY_NOT_FOUND, user.getName()));
                });
        userRepository.update(user);
    }
}
