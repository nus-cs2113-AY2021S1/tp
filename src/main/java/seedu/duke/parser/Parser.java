package seedu.duke.parser;

import seedu.duke.command.*;
import seedu.duke.exception.DukeException;

import java.util.Arrays;

public class Parser {

    /**
     * Default Constructor for parser.
     */
    public Parser() {

    }

    /**
     * Function takes in user input and then generates the correct command requested from the user input.
     *
     * @param userInput String containing what is typed by the user on the computer
     * @return Command from what is written by user. Return null if incorrect command
     */
    public Command parse(String userInput) throws DukeException {

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
            return ListCommand.parse(argument);

        case "bye":
            return new ByeCommand();

        case "check":
            return new CheckCommand(argument + " ");

        case "repeat":
            return RepeatCommand.parse(argument);

        case "goal":
            return new GoalCommand(argument);

        case "deadline":
            return new DeadlineCommand(argument);

        case "reminder":
            return new ReminderCommand();

        case "done":
            return DoneCommand.parse(argument);

        case "undone":
            return UndoneCommand.parse(argument);

        case "delete":
            return DeleteCommand.parse(argument);

        case "calendar":
            return CalendarCommand.parse(argument);

        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("Invalid Command.");
        }
    }


}
