package seedu.duke.parser;


import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.CalendarCommand;
import seedu.duke.command.CheckCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeadlineCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExtractCommand;
import seedu.duke.command.GoalCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.NoteCommand;
import seedu.duke.command.ReminderCommand;
import seedu.duke.command.RepeatCommand;
import seedu.duke.command.SaveCommand;
import seedu.duke.command.UndoneCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.exception.DukeException;


import java.util.ArrayList;
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
            return new HelpCommand(argument);

        case "extract":
            return new ExtractCommand(argument);

        case "note":
            return new NoteCommand(argument);

        case "save":
            return new SaveCommand();

        case "view":
            return new ViewCommand(argument);

        default:
            throw new DukeException("Invalid Command.");
        }
    }

    /**
     * Function helps to split user inputs into separate command inputs.
     * Used to allow users to key in more than one command at a time.
     *
     * @param userInput String separated by pipe characters to indicate separate commands
     * @return String ArrayList containing the command inputs as if they were typed separately
     */
    public ArrayList<String> multiParse(String userInput) {

        String[] commandInputs;
        commandInputs = userInput.split("\\|", -1);
        ArrayList<String> inputList = new ArrayList<>();

        for (String commandInput : commandInputs) {
            commandInput = commandInput.trim();
            inputList.add(commandInput);
        }
        return inputList;
    }

}
