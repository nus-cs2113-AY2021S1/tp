package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.command.Command;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.RepeatedSubjectException;

public class SubjectCommand extends Command {
    public Subject execute(SubjectList subjectList) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
