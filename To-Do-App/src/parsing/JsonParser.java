package parsing;

import model.Either;
import model.error.Error;
import model.error.ParsingError;
import model.task.Task;
import model.task.TaskBoard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonParser {

    private static final String FILE_NAME = "todo-list.json";

    public static Either<TaskBoard, Error> parse() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            JSONObject obj = new JSONObject(json);

            JSONArray tasks = obj.getJSONArray("tasks");
            ArrayList<Task> result = new ArrayList<>();

            for (int i = 0; i < tasks.length(); i++) {
                long id = tasks.getJSONObject(i).getLong("id");
                String name = tasks.getJSONObject(i).getString("name");
                boolean isDone = tasks.getJSONObject(i).getBoolean("isDone");

                Task task = new Task(id, name, isDone);
                result.add(task);
            }

            return new Either.Left<>(new TaskBoard(result));
        } catch (IOException e) {
            return new Either.Right<>(new ParsingError.JsonParsingError(FILE_NAME));
        }
    }

    public static void save(TaskBoard board) {
        try {
            JSONObject obj = new JSONObject();
            JSONArray tasks = new JSONArray();

            for (Task task : board.getAllTasks()) {
                JSONObject taskObj = new JSONObject();
                taskObj.put("id", task.getId());
                taskObj.put("name", task.getName());
                taskObj.put("isDone", task.isDone());
                tasks.put(taskObj);
            }

            obj.put("tasks", tasks);
            Files.write(Paths.get(FILE_NAME), obj.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
