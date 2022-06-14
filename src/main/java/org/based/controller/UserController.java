package org.based.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.based.application.UserService;
import org.based.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{name}")
    public void findByName(@PathVariable String name) {
        try {
            userService.findByName(name);
        } catch (Exception exception) {
            throw new NoSuchElementException("Not exist");
        }
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists")
    public void save(@RequestBody User user) {
        userService.save(user);
    }
    @PutMapping
    private void update(@RequestBody User user) {
        userService.update(user);
    }
    @DeleteMapping("/{name}")
    public void delete(@RequestParam String name) {
        userService.deleteByName(name);
    }
}
