package NUSchedule.event;


/**
 * Represents the Class events.
 */
public class Class extends Event {

    public Class(String description) {
        super(description);
    }

    /**
     * Convert the information about this Class to a string that is to be stored in a file.
     *
     * @return the result string to be stored.
     */
    public String fileString() {
        return "C//" + (isDone ? 1 : 0) + "//" + description;
    }

    /**
     * Prepare the string to be printed in the list.
     *
     * @return the string required in a certain format.
     * Example of the format: [C][✘]a.
     */
    public String toString() {
        return "[C]" + super.toString();
    }
}
