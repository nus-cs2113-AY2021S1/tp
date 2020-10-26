package seedu.revised.command.subject;

import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.storage.Storage;

import java.io.IOException;

public abstract class SubjectCommand extends Command {

    public abstract void execute(SubjectList subjectList, Storage storage) throws NoSubjectException,
            RepeatedSubjectException, InvalidSubjectException, FailedParseException, IOException, NoTopicException,
            NoFlashcardException;

    @Override
    public boolean isExit() {
        return false;
    }

}
