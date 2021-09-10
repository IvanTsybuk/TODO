package persistence;

import domain.Project;
import domain.User;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class ProjectRepository {
    public static final Map<User, Project> projects = new HashMap();

    public ProjectRepository() {
    }

    public static void createProject(User user) {
        System.out.println("Insert user's id:");


        System.out.println("Insert new Project's name:");
        Scanner scanner = new Scanner(System.in);
        projects.put(user, new Project(scanner.next()));
        System.out.println(projects);
    }

    public void showProjects(User user) {
        if (projects.containsKey(user)) {
            Iterator var2 = projects.entrySet().iterator();

            while(var2.hasNext()) {
                Entry entry = (Entry)var2.next();
                System.out.println(entry.getValue());
            }
        }

    }
}






