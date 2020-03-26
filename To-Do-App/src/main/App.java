package main;

import model.Either;
import model.command.api.Command;
import model.command.api.OtherCommand;
import model.command.api.TaskBoardCommand;
import model.error.Error;
import model.task.TaskBoard;
import parsing.CommandParser;
import parsing.JsonParser;
import printing.Printer;

public class App {

    public static void main(String[] args) {
        Either<Command, Error> commandResult = CommandParser.parse(args);
        if (commandResult.isRight()) {
            Error error = commandResult.toRight().getValue();
            Printer.printMessage(error.getMessage());
        } else {
            Command command = commandResult.toLeft().getValue();
            if (command instanceof OtherCommand) {
                ((OtherCommand) command).execute();
            } else if (command instanceof TaskBoardCommand) {
                Either<TaskBoard, Error> jsonResult = JsonParser.parse();
                if (jsonResult.isRight()) {
                    Error error = jsonResult.toRight().getValue();
                    Printer.printMessage(error.getMessage());
                } else {
                    TaskBoard board = jsonResult.toLeft().getValue();
                    ((TaskBoardCommand) command).execute(board);
                }
            }
        }
    }
}
