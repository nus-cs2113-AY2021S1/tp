package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class QuizSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(QuizSubjectCommand.class.getName());
    private String fullcommand;

    public QuizSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    /**
     * Quiz subjects in a <code>SubjectList</code>.
     *
     * @param subjectList the <code>SubjectList</code> instance of the <code>SubjectList</code> class for the user to quiz on.
     * @return Subject for the user to quiz on.
     */
    public Subject execute(SubjectList subjectList) throws NoSubjectException, InvalidSubjectException {
        logger.info("Begin finding the subject for which the quiz has to be conducted.");
        String[] message = this.fullcommand.split(" ");
        Subject quizSubject = null;
        if (message.length == 1) {
            throw new InvalidSubjectException(Ui.printInvalidSubjectError());
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException(Ui.printNoSubjectError());
        }
        logger.info("Finish reading the command to find the subject for the quiz feature");
        logger.fine(String.format("The subject is %s",quizSubject.getTitle()));
        return quizSubject;

    }

    public boolean isExit() {
        return false;
    }
}
