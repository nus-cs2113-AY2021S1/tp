package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.ui.Ui;

public class ResultSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ResultSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException, InvalidSubjectException {

        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidSubjectException(Ui.printInvalidSubjectError());
        }
        Subject quizSubject = null;

        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException(Ui.printNoSubjectError());
        }
        assert (quizSubject != null) : "No such subject exists!";
        Ui.printSubjectResults(quizSubject);
        return null;
    }
}
