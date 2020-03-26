package model.command;

import model.command.api.OtherCommand;
import model.task.TaskBoard;
import parsing.JsonParser;

public class CreateNewTaskListCommand implements OtherCommand {

    @Override
    public void execute() {
        JsonParser.save(new TaskBoard());
    }
}
