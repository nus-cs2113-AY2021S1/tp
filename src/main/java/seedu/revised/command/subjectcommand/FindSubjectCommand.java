package seedu.revised.command.subjectcommand;

import seedu.revised.exception.subjectexception.InvalidSubjectException;
import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class FindSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(FindSubjectCommand.class.getName());

    private String fullcommand;

    public FindSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    /**
     * Find subjects in an instance of the <code>SubjectList</code> that holds a keyword keyed in by the user.
     *
     * @param subjectList   An instance of the <code>SubjectList</code> class for the user to conduct the search on
     * @param storage       Does nothing in this case but needed since this method was implemented
     *                      from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) throws InvalidSubjectException {
        logger.info("Begin checking string command to get the keyword.");
        String[] message = this.fullcommand.split("\\s+", 2);
        if (message.length <= 1 || message[1].isEmpty()) {  // empty argument
            throw new InvalidSubjectException(Ui.INVALID_SUBJECT_EXCEPTION);
        }
        Ui.printFindSubject(subjectList, message[1].strip());
        logger.info("Finished searching for subjects matching the keyword.");
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
