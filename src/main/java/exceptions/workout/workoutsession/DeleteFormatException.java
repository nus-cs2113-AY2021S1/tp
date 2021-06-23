package exceptions.workout.workoutsession;

import exceptions.SchwarzeneggerException;

//@@author yujinyang1998
/**
 * Represents exception when delete format in WorkoutSession is invalid.
 */
public class DeleteFormatException extends SchwarzeneggerException {

    /**
     * Constructs DeleteFormatException object inheriting abstract class SchwarzeneggerException.
     */
    public DeleteFormatException() {
        super("DeleteFormatException");
    }
}
