package exceptions.workout.workoutsession;

import exceptions.SchwarzeneggerException;

//@@author yujinyang1998
/**
 * Represents exception when add format in WorkoutSession is invalid.
 */
public class AddFormatException extends SchwarzeneggerException {

    /**
     * Constructs AddFormatException object inheriting abstract class SchwarzeneggerException.
     */
    public AddFormatException() {
        super("AddFormatException");
    }
}
