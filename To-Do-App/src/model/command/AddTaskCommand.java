package model.command;

import model.command.api.TaskBoardCommand;
import model.task.TaskBoard;
import parsing.JsonParser;
import printing.Printer;

public class AddTaskCommand implements TaskBoardCommand {

    private String taskName;

    public AddTaskCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskBoard board) {
        board.addTask(taskName);
        JsonParser.save(board);
        Printer.printMessage("New task was added");
    }
}
