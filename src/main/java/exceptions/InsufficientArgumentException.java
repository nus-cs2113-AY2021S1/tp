package exceptions;

import static ui.workout.workoutmanager.WorkoutManagerUi.MESSAGE_INSUFFICIENT_ARGUMENT;

//@@author wgzesg
/**
 * Represents exception when user inputs insufficient arguments to command.
 */
public class InsufficientArgumentException extends SchwarzeneggerException {

    /**
     * Constructs InsufficientArgumentException object inheriting abstract class SchwarzeneggerException.
     *
     * @param format Correct format of command.
     */
    public InsufficientArgumentException(String format) {
        super(String.format(MESSAGE_INSUFFICIENT_ARGUMENT, format));
    }
}
