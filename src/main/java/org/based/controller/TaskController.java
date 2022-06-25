package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.application.TaskService;
import org.based.domain.Task;
import org.jetbrains.annotations.NotNull;
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
    @NotNull
    TaskService taskService;
    public TaskController(@NotNull TaskService taskService) {
        log.info("TaskController initialization");
        this.taskService = taskService;
    }
    @GetMapping
    public @NotNull List<Task> findAll() {
        log.info("GetMapping: find all tasks");
        return taskService.findAll();
    }
    @GetMapping("/{name}")
    public @NotNull Task findByName(@NotNull String name) {
        log.info(String.format("GetMapping: find a task by name- %s", name));
        return taskService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody @NotNull Task task) {
        log.info("PostMapping: save new task");
        taskService.save(task);
    }
    @PutMapping
    private void update(@RequestBody @NotNull Task task) {
        log.info(String.format("PutMapping: update task- %s", task.getName()));
        taskService.update(task);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable @NotNull String name) {
        log.info(String.format("DeleteMapping: delete task by name- %s", name));
        taskService.deleteByName(name);
    }
}
