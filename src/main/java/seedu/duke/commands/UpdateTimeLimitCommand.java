package seedu.duke.commands;

import seedu.duke.classes.WatchTime;
import seedu.duke.utility.TimeParser;

import java.util.ArrayList;

import static seedu.duke.utility.Ui.printBadInputException;
import static seedu.duke.utility.Ui.printInvalidTimeInput;

/**
 * Represents a Command to update the watch time limit duration for the user.
 */
public class UpdateTimeLimitCommand extends Command {
    ArrayList<String> inputs;

    public static final Integer VALID_NUMBER_OF_ARGUMENTS = 2;
    public static final Integer SECOND_ARGUMENT_IN_INPUT = 1;
    public static final Integer INVALID_TIME_LIMIT_ENTERED = -1;

    public UpdateTimeLimitCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != VALID_NUMBER_OF_ARGUMENTS) {
            throw new NullPointerException();
        }
    }

    /**
     * Notifies the application that user has updated his daily watch limit to a new duration.
     * @throws IllegalArgumentException when the time input is negative and invalid.
     * @throws NullPointerException when the user input has too few or too many arguments.
     * @throws IndexOutOfBoundsException if time input is invalid or unable to be processed.
     * @throws NumberFormatException     if the time limit input by the user is not a number.
     */
    public void processCommand() {
        try {
            int newTimeLimit = TimeParser.parseTime(inputs.get(SECOND_ARGUMENT_IN_INPUT));
            if (newTimeLimit == INVALID_TIME_LIMIT_ENTERED) {
                throw new IllegalArgumentException();
            }
            WatchTime.watchLimitUpdate(newTimeLimit);
        } catch (NullPointerException e) {
            printBadInputException();
        } catch (NumberFormatException e) {
            printInvalidTimeInput();
        } catch (IllegalArgumentException e) {
            // Exception message is handled in TimeParser class
            return;
        } catch (IndexOutOfBoundsException e) {
            printInvalidTimeInput();
        }
    }


}
