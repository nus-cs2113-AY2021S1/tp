package seedu.revised.command.subjectcommand;

import seedu.revised.command.Command;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcardexception.NoFlashcardException;
import seedu.revised.exception.subjectexception.InvalidSubjectException;
import seedu.revised.exception.subjectexception.NoSubjectException;
import seedu.revised.exception.topicexception.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subjectexception.RepeatedSubjectException;
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
