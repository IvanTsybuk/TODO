package domain;

import lombok.Getter;
import lombok.NonNull;

@NonNull
@Getter
public class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String toString() {
        return "Project(name=" + this.getName() + ")";
    }
}
