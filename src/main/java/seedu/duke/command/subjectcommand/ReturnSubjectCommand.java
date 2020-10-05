package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.RepeatedSubjectException;
import seedu.duke.parser.SubjectParser;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        if (message[1].isEmpty()){
            throw new NoSubjectException();
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle() == message[1]) {
                return subject;
            }
        }
        return null;
    }

    public void goToSubject(Subject subject){
        while(!isExit()){
            // TODO: implement the same thing in Duke run in here, but for Tasks instead
        }
    }

    public boolean isExit() {
        return false;
    }
}
