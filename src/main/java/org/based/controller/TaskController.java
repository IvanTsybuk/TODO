package org.based.controller;

import java.util.List;
import org.based.application.EntityService;
import org.based.domain.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    EntityService<Task> taskService;
    public TaskController(EntityService<Task> taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/tasks")
    public List<Task> findAll() {
        return taskService.findAll();
    }
    @PostMapping("/save")
    public void save(@RequestBody Task task) {
        taskService.save(task);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String name) {
        taskService.deleteByName(name);
    }
}
