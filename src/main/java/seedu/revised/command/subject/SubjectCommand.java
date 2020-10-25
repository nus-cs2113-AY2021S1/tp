package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subject.RepeatedSubjectException;

public class SubjectCommand extends Command {
    public Subject execute(SubjectList subjectList) throws NoSubjectException,
            RepeatedSubjectException, InvalidSubjectException, FailedParseException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
