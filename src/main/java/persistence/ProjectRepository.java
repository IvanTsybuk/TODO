package persistence;

import domain.Project;
import domain.User;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProjectRepository {


    public final Map<User, Project> projects = new HashMap();

    public ProjectRepository() {
    }

    public  void createProject(User user, Project project) {

        projects.put(user, project);

    }


        }






