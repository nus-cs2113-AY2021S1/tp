package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subjectexception.InvalidSubjectException;
import seedu.revised.exception.subjectexception.NoSubjectException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ResultSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ResultSubjectCommand.class.getName());
    private final String fullCommand;

    public ResultSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Shows results of the quiz of a <code>Subject</code> in a <code>SubjectList</code>.
     *
     * @param subjectList               An instance of the <code>SubjectList</code> class to get the results from
     * @param storage                   Does nothing in this case but needed since this method was implemented
     *                                  from an abstract class
     * @throws InvalidSubjectException  If the program does not detect any subject title in user input
     * @throws NoSubjectException       If the program does not detect a matching subject title in user input
     */
    public void execute(SubjectList subjectList, Storage storage) throws
            NoSubjectException, InvalidSubjectException {
        logger.info("Begin finding the subject for which the results feature has to be called.");
        String[] message = this.fullCommand.split("\\s+", 2);
        if (message.length == 1 || message[1].isEmpty()) {
            throw new InvalidSubjectException(Ui.INVALID_SUBJECT_EXCEPTION);
        }

        Subject resultSubject = null;
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().equals(message[1].strip())) {
                resultSubject = subject;
                break;
            }
        }
        if (resultSubject == null) {
            throw new NoSubjectException(Ui.SUBJECT_NOT_FOUND_EXCEPTION);
        }
        assert (resultSubject != null) : "No such subject exists!";
        Ui.printSubjectResults(resultSubject);
        logger.info("Finished reading the command to find the subject for the results feature.Now, the "
                + "application prints the results.");
        logger.fine(String.format("The subject is %s", resultSubject.getTitle()));
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
