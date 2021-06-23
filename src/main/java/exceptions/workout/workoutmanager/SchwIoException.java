package exceptions.workout.workoutmanager;

import exceptions.SchwarzeneggerException;

//@@author wgzesg
/**
 * Represents exception when IOException is thrown in WorkoutManagerStorage.
 */
public class SchwIoException extends SchwarzeneggerException {

    /**
     * Constructs SchwIoException object inheriting abstract class SchwarzeneggerException.
     *
     * @param content Content of the error message.
     */
    public SchwIoException(String content) {
        super(content);
    }
}
