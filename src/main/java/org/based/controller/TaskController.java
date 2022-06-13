package org.based.controller;

import java.util.List;
import org.based.application.TaskService;
import org.based.domain.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/all")
    public List<Task> findAll() {
        return taskService.findAll();
    }
    @GetMapping("/{name}")
    public Task findByName(String name) {
        return taskService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody Task task) {
        if (task.getId() != 0) {
            taskService.update(task);
        }
        taskService.save(task);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        taskService.deleteByName(name);
    }
}
