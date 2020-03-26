package test;

import model.Either;
import model.command.AddTaskCommand;
import model.command.api.Command;
import model.error.Error;
import model.error.ParsingError;
import org.junit.Test;
import parsing.CommandParser;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTests {

    @Test
    public void correctInputTest() {
        String[] args = {"add", "New task"};
        Either<Command, Error> parsingResult = CommandParser.parse(args);

        assertTrue(parsingResult.isLeft(), "Parsed input should be Command instance");

        assertTrue(parsingResult.toLeft().getValue() instanceof AddTaskCommand, "Parsed command should be AddTaskCommand instance");
    }

    @Test
    public void incorrectInputTest() {
        String[] args = {"add"};
        Either<Command, Error> parsingResult = CommandParser.parse(args);

        assertTrue(parsingResult.isRight(), "Parsed input should be Error instance");

        assertTrue(parsingResult.toRight().getValue() instanceof ParsingError.MissingStringError, "Parsed error should be MissingStringError instance");
    }
}
