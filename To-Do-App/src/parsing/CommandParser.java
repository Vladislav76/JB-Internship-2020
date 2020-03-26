package parsing;

import model.Either;
import model.command.*;
import model.command.api.Command;
import model.error.Error;
import model.error.ParsingError;
import model.task.FilterMode;

public class CommandParser {

    public static Either<Command, Error> parse(String[] args) {
        if (args.length == 0) return new Either.Right<>(new ParsingError.NoCommandError());

        boolean isDeleteCmd = false;

        switch (args[0]) {
            case "add":
                if (args.length > 2) return new Either.Right<>(new ParsingError.TooManyArgumentsError());
                if (args.length < 2) return new Either.Right<>(new ParsingError.MissingStringError(args[0]));
                return new Either.Left<>(new AddTaskCommand(args[1]));
            case "rm":
                isDeleteCmd = true;
            case "ch":
                if (args.length > 2) return new Either.Right<>(new ParsingError.TooManyArgumentsError());
                if (args.length < 2) return new Either.Right<>(new ParsingError.MissingNumberError(args[0]));

                Either<Long, Error> parsingNumberResult = parseLong(args[0], args[1]);
                if (parsingNumberResult.isRight()) return new Either.Right<>(parsingNumberResult.toRight().getValue());

                long taskID = parsingNumberResult.toLeft().getValue();
                if (isDeleteCmd) return new Either.Left<>(new DeleteTaskCommand(taskID));
                else return new Either.Left<>(new ChangeTaskCheckCommand(taskID));
            case "show":
                // default values
                FilterMode mode = FilterMode.ALL;
                int pageNumber = 1;
                int pageCapacity = 50;
                int maxPageCapacity = 1000;

                int k = 1;

                while (k < args.length) {
                    boolean isPageOption = false;
                    switch (args[k]) {
                        case "-a":
                            break;
                        case "-d":
                            mode = FilterMode.DONE;
                            break;
                        case "-u":
                            mode = FilterMode.UNDONE;
                            break;
                        case "-p":
                            isPageOption = true;
                        case "-pc":
                            if (k == args.length - 1) return new Either.Right<>(new ParsingError.MissingNumberError(args[k]));

                            parsingNumberResult = parseLong(args[k], args[++k]);
                            if (parsingNumberResult.isRight()) return new Either.Right<>(parsingNumberResult.toRight().getValue());

                            if (isPageOption) {
                                pageNumber = parsingNumberResult.toLeft().getValue().intValue();
                            } else {
                                pageCapacity = parsingNumberResult.toLeft().getValue().intValue();
                                if (pageCapacity > maxPageCapacity) return new Either.Right<>(new ParsingError.NumberOutOfTheBoundError());
                            }

                            break;
                        default:
                            return new Either.Right<>(new ParsingError.NoSuchOptionError(args[k]));
                    }
                    k++;
                }
                return new Either.Left<>(new ShowTasksCommand(mode, pageNumber, pageCapacity));
            case "help":
                return new Either.Left<>(new HelpCommand());
            case "new":
                return new Either.Left<>(new CreateNewTaskListCommand());
            default:
                return new Either.Right<>(new ParsingError.NoSuchCommandError(args[0]));
        }
    }

    private static Either<Long, Error> parseLong(String prevArg, String arg) {
        try {
            long parsedLong = Long.parseLong(arg);
            if (parsedLong <= 0) return new Either.Right<>(new ParsingError.RequestingPositiveNumberError(prevArg, arg));
            return new Either.Left<>(parsedLong);
        } catch (NumberFormatException e) {
            return new Either.Right<>(new ParsingError.RequestingNumberError(prevArg, arg));
        }
    }
}
