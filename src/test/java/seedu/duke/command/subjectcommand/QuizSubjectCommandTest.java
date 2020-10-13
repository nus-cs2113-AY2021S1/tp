package seedu.duke.command.subjectcommand;


import org.junit.jupiter.api.Test;
import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.NoSubjectException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuizSubjectCommandTest {
    SubjectList subjectList = new SubjectList(new ArrayList<>());

    @Test
    void execute_command_throwsException() throws NoSubjectException {
        String input="quiz";
        QuizSubjectCommand quiz =new QuizSubjectCommand(input);
        assertThrows(NoSubjectException.class, () ->quiz.execute(subjectList) );

    }

}