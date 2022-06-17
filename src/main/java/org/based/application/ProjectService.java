package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private static final String ALREADY_EXIST = "Project with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Project with name - %s, is not found";
    private final Repository<Project> projectRepository;
    public ProjectService(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void save(final Project project) {
        projectRepository.findByName(project.getName())
                .ifPresentOrElse(a -> throwException(a.getName()),
                        () -> projectRepository.save(project));
    }
    private void throwException(String entityName) {
        throw new EntityAlreadyExistsException(String.format(ALREADY_EXIST, entityName));
    }
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public void deleteByName(String name) {
        projectRepository.deleteByName(name);
    }
    public Project findByName(String name) {
        return projectRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void update(Project project) {
        projectRepository.update(project);
    }
}
