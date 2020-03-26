package test;

import model.task.Task;
import model.task.TaskBoard;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskBoardTests {

    private final TaskBoard board = new TaskBoard();

    @Test
    public void addTaskTest() {
        int oldN = board.getAllTasks().size();
        board.addTask("New task");
        int newN = board.getAllTasks().size();

        assertEquals(oldN + 1, newN, "Adding new task should increment task amount");
    }

    @Test
    public void deleteTaskTest() {
        board.addTask("New task");
        Task newTask = board.getAllTasks().get(board.getAllTasks().size() - 1);

        int oldN = board.getAllTasks().size();
        board.deleteTask(newTask.getId());
        int newN = board.getAllTasks().size();

        assertEquals(oldN - 1, newN, "Deleting task should decrement task amount");
    }

    @Test
    public void checkTaskTest() {
        board.addTask("New task");
        Task newTask = board.getAllTasks().get(board.getAllTasks().size() - 1);

        board.checkTask(newTask.getId());
        assertTrue(newTask.isDone(), "New task should be done after 1st check");

        board.checkTask(newTask.getId());
        assertFalse(newTask.isDone(), "New task should be undone after 2nd check");
    }
}
