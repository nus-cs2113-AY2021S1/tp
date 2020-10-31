package anichan.parser;

import anichan.commands.HelpCommand;

/**
 * Handles parsing for Help command.
 */
public class HelpParser extends CommandParser {
    
    private HelpCommand helpCommand;

    /**
     * Creates a new instance of HelpParser.
     */
    public HelpParser() {
        helpCommand = new HelpCommand();
    }
}
