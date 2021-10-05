package org.based.persistence;
import org.based.domain.Project;
import java.util.*;

public class ProjectRepository {

    private final Map<String, Project> projects = new HashMap();

    public void save(Project project) {
        projects.put(project.getName(), project);
    }

    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }
}






