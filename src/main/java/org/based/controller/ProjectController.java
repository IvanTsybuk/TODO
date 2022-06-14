package org.based.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.based.application.ProjectService;
import org.based.domain.Project;
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
@RequestMapping(value = "/projects")
public class ProjectController {
    ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }
    @GetMapping("/{name}")
    public Project findByName(@PathVariable String name) {
        try {
            return projectService.findByName(name);
        } catch (Exception exception) {
            throw new NoSuchElementException("Not exist");
        }
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Already exists")
    public void save(@RequestBody Project project) {
        projectService.save(project);
    }
    @PutMapping
    private void update(@RequestBody Project project) {
        projectService.update(project);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        projectService.deleteByName(name);
    }
}
