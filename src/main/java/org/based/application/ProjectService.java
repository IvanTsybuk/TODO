package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.Project;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProjectService {
    private static final String ALREADY_EXIST = "Project with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Project with name - %s, is not found";
    private final Repository<Project> projectRepository;
    public ProjectService(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void save(@NotNull final Project project) {
        log.info("Saving new project");
        projectRepository.findByName(project.getName())
                .ifPresent(a -> {
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        projectRepository.save(project);
    }
    @Nullable
    public List<Project> findAll() {
        log.info("Searching all projects");
        return projectRepository.findAll();
    }
    public void deleteByName(String name) {
        log.info("Deleting a project");
        projectRepository.deleteByName(name);
    }
    public Project findByName(String name) {
        log.info("Finding a project by name");
        return projectRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void update(@NotNull Project project) {
        log.info("Updating a project");
        projectRepository.findByName(project.getName()).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, project.getName())));
        projectRepository.update(project);
    }
}
