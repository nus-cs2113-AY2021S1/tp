package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.exception.card.InvalidSubjectCommand;
import seedu.revised.exception.card.NoSubjectException;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.card.RepeatedSubjectException;

public class SubjectCommand extends Command {
    public Subject execute(SubjectList subjectList) throws NoSubjectException,
            RepeatedSubjectException, InvalidSubjectCommand {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
