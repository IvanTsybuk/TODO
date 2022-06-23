package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class ProjectController {
    ProjectService projectService;
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public List<Project> findAll() {
        log.info("GetMapping: finding all projects");
        return projectService.findAll();
    }
    @GetMapping("/{name}")
    public Project findByName(@PathVariable String name) {
        log.info("GetMapping: finding project by name");
        return projectService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody Project project) {
        log.info("PostMapping: saving new project");
        projectService.save(project);
    }
    @PutMapping
    private void update(@RequestBody Project project) {
        log.info("PutMapping: updating project");
        projectService.update(project);
    }
    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        log.info("DeleteMapping: deleting project");
        projectService.deleteByName(name);
    }
}
