package persistence;
import domain.Project;
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






