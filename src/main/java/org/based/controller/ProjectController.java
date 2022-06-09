package org.based.controller;

import java.util.List;
import org.based.application.EntityService;
import org.based.domain.Project;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    EntityService<Project> projectService;
    public ProjectController(EntityService<Project> projectService) {
        this.projectService = projectService;
    }
    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectService.findAll();
    }
    @PostMapping("/save")
    public void save(@RequestBody Project project) {
        projectService.save(project);
    }
}
