package seedu.duke.command;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;

/**
 * Various commands to use depending on the various inputs given by the user.
 */
public abstract class Command {

    public abstract boolean isExit();
}