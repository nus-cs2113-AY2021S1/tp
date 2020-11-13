package seedu.duke.logic.parser;

import seedu.duke.command.Command;

//@@author 1-Karthigeyan-1
/**
 * Represents the abstract class for parsing commands.
 */
public abstract class Parser {
    public String userInput;

    /**
     * Initialises Parser class.
     *
     * @param description user input to be parsed
     */
    public Parser(String description) {
        this.userInput = description;
    }

    /**
     * Abstract command for parsing argument.
     *
     * @return Command class
     */
    public abstract Command parseArgument();

    /**
     * Checks for extra spaces in the userinput.
     *
     * @return trimmed input
     */
    public String checkExtraSpaces() {
        return this.userInput.replaceAll(" +", CommandParser.SPACE).trim();
    }

}
