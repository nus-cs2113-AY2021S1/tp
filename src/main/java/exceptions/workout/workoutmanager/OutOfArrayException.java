package exceptions.workout.workoutmanager;

import exceptions.SchwarzeneggerException;

import static ui.workout.workoutmanager.WorkoutManagerUi.MESSAGE_OUT_OF_ARRAY;

//@@author wgzesg
/**
 * Represents exception when index is out of array.
 */
public class OutOfArrayException extends SchwarzeneggerException {

    /**
     * Constructs OutOfArrayException object inheriting abstract class SchwarzeneggerException.
     */
    public OutOfArrayException() {
        super(MESSAGE_OUT_OF_ARRAY);
    }
}
