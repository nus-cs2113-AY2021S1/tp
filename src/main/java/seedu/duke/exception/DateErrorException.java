package seedu.duke.exception;

public class DateErrorException extends DukeException {

    public DateErrorException() {
        super("Something is wrong with the date!" + System.lineSeparator()
                + "The accepted formats are: d/m/yyyy, m/yyyy or yyyy. yyyy can be shortened to yy."
                + System.lineSeparator() + "Dashes may be used in place of slashes.");
    }
}
