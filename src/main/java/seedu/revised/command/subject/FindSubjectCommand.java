package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class FindSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(FindSubjectCommand.class.getName());

    private String fullcommand;

    public FindSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    /**
     * Find subjects in a <code>SubjectList</code> that hold a keyword keyed in by the user.
     *
     * @param subjectList the <code>SubjectList</code> instance of the <code>SubjectList</code> class for the user to conduct the search on.
     * @return null
     */
    public Subject execute(SubjectList subjectList) {
        logger.info("Begin checking string command to get the keyword to search subject titles in the SubjectList.");
        String[] message = this.fullcommand.split(" ");
        Ui.printFindSubject(subjectList,message[1]);
        logger.info("Finish finding the subjects that match the keyword.");
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
