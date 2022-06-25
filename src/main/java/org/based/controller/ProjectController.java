package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.application.ProjectService;
import org.based.domain.Project;
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
@RequestMapping(value = "/projects")
@Log4j2
public class ProjectController {
    @NotNull
    ProjectService projectService;
    public ProjectController(@NotNull ProjectService projectService) {
        log.info("ProjectController initialization");
        this.projectService = projectService;
    }
    @GetMapping
    public @NotNull List<Project> findAll() {
        log.info("GetMapping: find all projects");
        return projectService.findAll();
    }
    @GetMapping("/{name}")
    public Project findByName(@PathVariable @NotNull String name) {
        log.info(String.format("GetMapping: find project by name-%s", name));
        return projectService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody @NotNull Project project) {
        log.info("PostMapping: save new project-%s");
        projectService.save(project);
    }
    @PutMapping
    private void update(@RequestBody @NotNull Project project) {
        log.info(String.format("PutMapping: update project-%s", project.getName()));
        projectService.update(project);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable @NotNull String name) {
        log.info(String.format("DeleteMapping: delete project by name-%s", name));
        projectService.deleteByName(name);
    }
}
