package anichan.parser;

import anichan.commands.HelpCommand;
import anichan.exception.AniException;

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
    
    public HelpCommand parse(String description) throws AniException {
        
    }
}
