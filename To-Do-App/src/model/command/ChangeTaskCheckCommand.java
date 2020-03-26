package model.command;

import model.command.api.TaskBoardCommand;
import model.task.TaskBoard;
import parsing.JsonParser;
import printing.Printer;

public class ChangeTaskCheckCommand implements TaskBoardCommand {

    private long taskID;

    public ChangeTaskCheckCommand(long taskID) {
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskBoard board) {
        if (board.checkTask(taskID)) {
            JsonParser.save(board);
            Printer.printMessage("Task (id = " + taskID + ") check was changed");
        } else {
            Printer.printMessage("No such task (id = " + taskID + ")");
        }
    }
}
