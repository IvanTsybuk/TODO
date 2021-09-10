package application;

import domain.Task;

import java.util.ArrayList;
import java.util.List;

public class Taskservice  {

    public static List<Task> taskList = new ArrayList<>();

    public static void addTask(Task task) {
        taskList.add(task);

    }
    public static void removeTask(String str) {

        taskList.removeIf(task-> task.getName().equals(str));

        System.out.println(taskList);
    }


}
