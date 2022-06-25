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
        log.debug("ProjectService: Save new project");
        projectRepository.findByName(project.getName())
                .ifPresent(a -> {
                    log.error("ProjectService Exception: save new Project. ALREADY_EXIST");
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        projectRepository.save(project);
    }
    public @NotNull List<Project> findAll() {
        log.debug("ProjectService: Find all projects");
        return projectRepository.findAll();
    }
    public void deleteByName(@NotNull String name) {
        log.debug(String.format("ProjectService: Delete project by name-%s", name));
        projectRepository.deleteByName(name);
    }
    public Project findByName(@NotNull String name) {
        log.debug(String.format("ProjectService: Find by name project-%s", name));
        return projectRepository.findByName(name).orElseThrow(
                () -> {
                    log.error(String.format("ProjectService Exception: NOT_FOUND-%s", name));
                    throw  new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name));
                });
    }
    public void update(@NotNull Project project) {
        log.debug(String.format("ProjectService: Update project-%s", project.getName()));
        projectRepository.findByName(project.getName()).orElseThrow(
                () -> {
                    log.error(String.format("ProjectService Exception: NOT_FOUND-%s",
                            project.getName()));
                    throw new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, project.getName()));
                });
        projectRepository.update(project);
    }
}
