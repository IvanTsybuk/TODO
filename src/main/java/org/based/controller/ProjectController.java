package org.based.controller;

import java.util.List;
import org.based.application.ProjectService;
import org.based.domain.Project;
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
public class ProjectController {
    ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectService.findAll();
    }
    @GetMapping("/projects/{name}")
    public Project findByName(@PathVariable String name) {
        return projectService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody Project project) {
        projectService.save(project);
    }
    @PutMapping
    public void update(@RequestBody Project project) {
        projectService.update(project);
    }
    @DeleteMapping("/projects/{name}")
    public void delete(@PathVariable String name) {
        projectService.deleteByName(name);
    }
}
