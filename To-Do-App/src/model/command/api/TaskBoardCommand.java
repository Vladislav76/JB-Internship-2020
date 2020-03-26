package model.command.api;

import model.task.TaskBoard;

public interface TaskBoardCommand extends Command {

    void execute(TaskBoard board);
}
