package model.command;

import model.command.api.TaskBoardCommand;
import model.task.FilterMode;
import model.task.TaskBoard;
import model.task.TaskListPage;
import printing.Printer;

public class ShowTasksCommand implements TaskBoardCommand {

    private FilterMode mode;
    private int pageNumber;
    private int pageCapacity;

    public ShowTasksCommand(FilterMode mode, int pageNumber, int pageCapacity) {
        this.mode = mode;
        this.pageNumber = pageNumber;
        this.pageCapacity = pageCapacity;
    }

    @Override
    public void execute(TaskBoard board) {
        TaskListPage page = board.getTasks(mode, pageNumber, pageCapacity);
        if (page.getPageAmount() == 0) {
            Printer.printMessage("No tasks");
        } else {
            if (pageNumber > page.getPageAmount()) {
                Printer.printMessage("Page number is out of the bound (max = " + page.getPageAmount() + ", input = " + pageNumber + ")");
            } else {
                Printer.print(page.getTasks(), pageNumber, pageCapacity, page.getPageAmount());
            }
        }
    }
}
