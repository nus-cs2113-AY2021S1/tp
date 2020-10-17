package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.card.InvalidSubjectCommand;
import seedu.revised.exception.card.NoSubjectException;
import seedu.revised.ui.Ui;

public class ResultSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ResultSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException,InvalidSubjectCommand {

        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidSubjectCommand();
        }
        Subject quizSubject = null;

        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException();
        }
        assert (quizSubject != null) : "No such subject exists!";
        Ui.printSubjectResults(quizSubject);
        return null;
    }
}
