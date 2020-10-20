package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.ui.Ui;

public class QuizSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public QuizSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException, InvalidSubjectException {
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
        return quizSubject;

    }

    public boolean isExit() {
        return false;
    }
}
