package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class AddSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(AddSubjectCommand.class.getName());
    private String fullCommand;

    public AddSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds a <code>Subject</code> in a <code>SubjectList</code>.
     *
     * @param subjectList the <code>SubjectList</code> instance of the <code>SubjectList</code> class for the user to add to.
     * @return null
     */
    public Subject execute(SubjectList subjectList) throws NoSubjectException, RepeatedSubjectException {
        logger.info("Begin checking string command to get the title of the subject to be added.");
        int startOfMessage = 4;
        int endOfMessage = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new NoSubjectException(Ui.printNoSubjectError());
        }
        String title = fullCommand.substring(startOfMessage, endOfMessage);
        if (title.isEmpty()) {
            throw new NoSubjectException(Ui.printNoSubjectError());
        }
        assert title != null;
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(title)) {
                throw new RepeatedSubjectException(Ui.printRepeatedSubjectError());
            }
        }
        Subject temp = new Subject(title);
        subjectList.getList().add(temp);
        Ui.printSubject(temp, subjectList);
        logger.info("Finish creating a new subject.");
        logger.fine(String.format("The subject is %s", temp.getTitle()));
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
