package model.task;

import java.util.List;

public class TaskListPage {

    private List<Task> tasks;
    private int pageAmount;

    public TaskListPage(List<Task> tasks, int pageAmount) {
        this.tasks = tasks;
        this.pageAmount = pageAmount;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getPageAmount() {
        return pageAmount;
    }
}
