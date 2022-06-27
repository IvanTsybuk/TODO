package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.Project;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProjectService {
    private static final String ALREADY_EXIST = "Project with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Project with name - %s, is not found";
    @NotNull
    private final Repository<Project> projectRepository;
    public ProjectService(@NotNull Repository<Project> projectRepository) {
        log.info("ProjectService initialization");
        this.projectRepository = projectRepository;
    }
    public void save(@NotNull final Project project) {
        log.debug(String.format("Save new project: %s", project));
        projectRepository.findByName(project.getName())
                .ifPresent(a -> {
                    log.error(String.format("Save Exception-project: %s. ALREADY_EXIST", project));
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        projectRepository.save(project);
    }
    @NotNull
    public List<Project> findAll() {
        log.debug("Find all projects");
        return projectRepository.findAll();
    }
    public void deleteByName(@NotNull final String name) {
        log.debug(String.format("delete project by name: %s", name));
        projectRepository.deleteByName(name);
    }
    @NotNull
    public Project findByName(@NotNull final String name) {
        log.debug(String.format("find project by name: %s", name));
        return projectRepository.findByName(name).orElseThrow(
                () -> {
                    log.error(String.format("FindByName Exception: NOT_FOUND- project name: %s",
                            name));
                    throw  new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name));
                });
    }
    public void update(@NotNull final Project project) {
        log.debug(String.format("Update project: %s", project));
        projectRepository.findByName(project.getName()).orElseThrow(
                () -> {
                    log.error(String.format("Update Exception: NOT_FOUND- project name: %s",
                            project.getName()));
                    throw new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, project.getName()));
                });
        projectRepository.update(project);
    }
}
