package model.command;

import model.command.api.OtherCommand;
import printing.Printer;

public class HelpCommand implements OtherCommand {

    @Override
    public void execute() {
        Printer.printManual(CommandLibrary.getDescription());
    }
}
