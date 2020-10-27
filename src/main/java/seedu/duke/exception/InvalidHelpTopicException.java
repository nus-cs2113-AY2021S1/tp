package seedu.duke.exception;

public class InvalidHelpTopicException extends DukeException{

    public InvalidHelpTopicException(String topic) {
        super(topic + " is not a valid help command. Type help to see a list of all available help topics");
    }
}
