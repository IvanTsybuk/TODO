package org.based.controller;

import java.util.List;
import org.based.application.ProjectService;
import org.based.domain.Project;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService;
    public ProjectController(ProjectService projectService) {
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
    @PutMapping("/update")
    public void update(@RequestBody Project project) {
        projectService.update(project);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String name) {
        projectService.deleteByName(name);
    }
}
