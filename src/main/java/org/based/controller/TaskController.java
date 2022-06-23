package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.application.TaskService;
import org.based.domain.Task;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@Log4j2
public class TaskController {
    TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public List<Task> findAll() {
        log.info("GetMapping: finding all tasks");
        return taskService.findAll();
    }
    @GetMapping("/{name}")
    public Task findByName(String name) {
        log.info("GetMapping: finding a tasks by name");
        return taskService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody Task task) {
        log.info("PostMapping: saving new task");
        taskService.save(task);
    }
    @PutMapping
    private void update(@RequestBody Task task) {
        log.info("PutMapping: updating task");
        taskService.update(task);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        log.info("DeleteMapping: deleting task");
        taskService.deleteByName(name);
    }
}
