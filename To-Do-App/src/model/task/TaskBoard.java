package model.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskBoard {

    private ArrayList<Task> tasks;
    private long maxTaskID;

    public TaskBoard() {
        tasks = new ArrayList<>();
        maxTaskID = 1;
    }

    public TaskBoard(ArrayList<Task> tasks) {
        this.tasks = tasks;
        maxTaskID = 0;

        for (Task task : tasks) if (task.getId() > maxTaskID) maxTaskID = task.getId();
        maxTaskID++;
    }

    /* Getters */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /* Task manipulation */
    public void addTask(String taskName) {
        Task newTask = new Task(maxTaskID++, taskName, false);
        tasks.add(newTask);
    }

    public boolean deleteTask(long taskID) {
        int position = findTask(taskID);
        if (position != -1) {
            tasks.remove(position);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkTask(long taskID) {
        int position = findTask(taskID);
        if (position != -1) {
            boolean wasChecked = tasks.get(position).isDone();
            tasks.get(position).setDone(!wasChecked);
            return true;
        } else {
            return false;
        }
    }

    public TaskListPage getTasks(FilterMode mode, int pageNumber, int pageCapacity) {
        List<Task> filteredTasks;
        if (mode != FilterMode.ALL) {
            boolean isChecked = mode == FilterMode.DONE;
            filteredTasks = tasks.stream().filter(
                    new Predicate<Task>() {
                        @Override
                        public boolean test(Task task) {
                            return task.isDone() == isChecked;
                        }
                    }
                    ).collect(Collectors.toList());
        } else {
            filteredTasks = tasks;
        }

        int pageAmount = 0;
        if (filteredTasks.size() > 0) {
            pageAmount = filteredTasks.size() / pageCapacity;
            if (filteredTasks.size() % pageCapacity > 0) pageAmount++;
        }

        int startPosition = (pageNumber - 1) * pageCapacity;
        int endPosition = Math.min(startPosition + pageCapacity, filteredTasks.size());
        List<Task> resultTasks = (startPosition <= endPosition) ? filteredTasks.subList(startPosition, endPosition) : Collections.emptyList();

        return new TaskListPage(resultTasks, pageAmount);
    }

    private int findTask(long taskID) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == taskID) {
                return i;
            }
        }
        return -1;
    }
}
