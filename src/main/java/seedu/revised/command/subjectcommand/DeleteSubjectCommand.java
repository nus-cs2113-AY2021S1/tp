package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class DeleteSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(DeleteSubjectCommand.class.getName());
    private String fullCommand;

    public DeleteSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes an instance of the <code>Subject</code> class in a <code>SubjectList</code>.
     *
     * @param subjectList               An instance of the <code>SubjectList</code> class for the user to append to
     * @param storage                   Does nothing in this case but needed since this method was implemented
     *                                  from an abstract class
     * @throws NumberFormatException    If the program detects a wrong format for user input
     */
    public void execute(SubjectList subjectList, Storage storage) throws NumberFormatException {
        logger.info("Begin checking string command to get the subject to be deleted.");
        String[] message = this.fullCommand.split(" ");
        int number = Integer.valueOf(message[1]);
        Subject subject = subjectList.getList().get(number - 1);
        assert !(number <= 0 && number > subjectList.getList().size());
        subjectList.getList().remove(number - 1);
        Ui.printSubjectDelete(subject, subjectList.getList().size());
        logger.info("Finished deleting the subject to be deleted.");
        logger.fine(String.format("The subject is %s", subject.getTitle()));
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
