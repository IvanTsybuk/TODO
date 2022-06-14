package org.based.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.based.application.TaskService;
import org.based.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }
    @GetMapping("/{name}")
    public Task findByName(String name) {
        try {
            return taskService.findByName(name);
        } catch (Exception exception) {
            throw new NoSuchElementException("Not exist");
        }
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists")
    public void save(@RequestBody Task task) {
        taskService.save(task);
    }
    @PutMapping
    private void update(@RequestBody Task task) {
        taskService.update(task);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        taskService.deleteByName(name);
    }
}
