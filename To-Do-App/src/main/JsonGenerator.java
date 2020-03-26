package main;

import model.task.Task;
import model.task.TaskBoard;
import parsing.JsonParser;

import java.util.ArrayList;
import java.util.Random;

public class JsonGenerator {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            String name = "Task N" + i;
            boolean isDone = new Random().nextBoolean();
            Task task = new Task(i, name, isDone);
            tasks.add(task);
        }

        JsonParser.save(new TaskBoard(tasks));
    }
}
