package ui;

import exception.CommandException;
import parser.Parser;

public class ParserStub extends Parser {

    public ParserStub() {
        super(null, null, null, null, null,null,null);
    }

    public CommandStub parse(String input) throws CommandException {
        if (input.equals("throw")) {
            throw new CommandException("Please enter a valid command");
        } else if (input.equals("exit")) {
            return new CommandStub(true);
        } else {
            return new CommandStub(false);
        }
    }
}
