package persistence;

import domain.Project;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ProjectRepository {

    private final Map<Project, String> projects= new HashMap();

}






