package model.command;

import model.command.api.TaskBoardCommand;
import model.task.TaskBoard;
import parsing.JsonParser;
import printing.Printer;

public class DeleteTaskCommand implements TaskBoardCommand {

    private long taskID;

    public DeleteTaskCommand(long taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskBoard board) {
        if (board.deleteTask(taskID)) {
            JsonParser.save(board);
            Printer.printMessage("Task (id = " + taskID + ") was deleted");
        } else {
            Printer.printMessage("No such task (id = " + taskID + ")");
        }
    }
}
