package persistence;

import domain.Task;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskRepository {

    private final List<Task> taskList = new ArrayList<>();

}
