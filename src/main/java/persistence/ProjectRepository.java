package persistence;
import domain.Project;
import java.util.*;

public class ProjectRepository {

    private final Map<Integer, Project> projects = new HashMap();

    public void save(Project project, Integer projectKey) {
        projects.put(projectKey, project);
    }

    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }
}






