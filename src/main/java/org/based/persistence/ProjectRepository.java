package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.Project;

import java.util.*;

public class ProjectRepository extends Repository<Project> {

    public ProjectRepository(FileOperator fileOperator) {
            super(fileOperator, new TypeReference<>() {});
    }
    @Override
    protected String getDefaultFileRepositoryPath() {
        return fileOperator.getPROJECT_DEFAULT_FILE_JSON();
    }
    public void save(Project project) {
        repositoryMap.put(project.getName(), project);
    }
    public List<Project> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
}
