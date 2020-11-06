package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.card.quizcard.SubjectQuiz;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subjectexception.InvalidSubjectException;
import seedu.revised.exception.subjectexception.NoSubjectException;
import seedu.revised.storage.Storage;
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
     * @param subjectList An instance of the <code>SubjectList</code> class for the user to quiz on
     * @param storage     Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) throws NoSubjectException, InvalidSubjectException,
            NoTopicException, NoFlashcardException {
        logger.info("Begin finding the subject for which the quiz has to be conducted.");
        String[] message = this.fullcommand.split("\\s+", 2);
        if (message.length <= 1 || message[1].isEmpty()) {
            throw new InvalidSubjectException(Ui.INVALID_SUBJECT_EXCEPTION);
        }
        Subject quizSubject = null;
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().equals(message[1].strip())) {
                quizSubject = subject;
                break;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException(Ui.SUBJECT_NOT_FOUND_EXCEPTION);
        }
        logger.info("Finished reading the command to find the subject for the quiz feature");
        logger.fine(String.format("The subject is %s", quizSubject.getTitle()));

        startQuiz(quizSubject);
    }

    /**
     * Starts the quiz of the subject specified in the param.
     *
     * @param subject               An instance of the <code>Subject</code> class for the user to quiz on
     * @throws NoTopicException     If there are no instances of <code>Topic</code> available for the
     *                              program to quiz from
     * @throws NoFlashcardException If there are no instances of <code>Flashcard</code> available for the
     *                              program to quiz from
     */
    private void startQuiz(Subject subject) throws NoTopicException, NoFlashcardException {
        SubjectQuiz subjectQuiz = new SubjectQuiz(subject);
        subjectQuiz.startQuiz();
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
