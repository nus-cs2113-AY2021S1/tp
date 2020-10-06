package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.command.CheckCommand;
import seedu.duke.command.RepeatCommand;
import seedu.duke.command.GoalCommand;
import seedu.duke.command.DeadlineCommand;

import java.util.Arrays;

public class Parser {

    /**
     * Default Constructor for parser.
     *
     */
    public Parser() {

    }


    public Command parse(String userInput) {

        String[] words = userInput.split(" ");

        if (words.length == 0) {

            System.out.println("Error, no command entered");
            return null;
        }

        String commandWord = words[0];
        String[] argumentWords = Arrays.copyOfRange(words, 1, words.length);
        String argument = String.join(" ", argumentWords);

        switch (commandWord) {
        case "add":
            return new AddCommand(argument);

        case "list":
            return new ListCommand(argument);

        case "bye":
            return new ByeCommand();

        case "check":
            return new CheckCommand(argument);

        case "repeat":
            return new RepeatCommand(argument);

        case "goal":
            return new GoalCommand(argument);

        case "deadline":
            return new DeadlineCommand(argument);

        default:
            System.out.println("Error! Unrecognised command");
            return null;
        }
    }


}
