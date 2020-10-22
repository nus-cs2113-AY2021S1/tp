package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.command.subject.AddSubjectCommand;
import seedu.revised.command.subject.DeleteSubjectCommand;
import seedu.revised.command.subject.FindSubjectCommand;
import seedu.revised.command.subject.ListSubjectCommand;
import seedu.revised.command.subject.ResultSubjectCommand;
import seedu.revised.command.subject.ReturnSubjectCommand;
import seedu.revised.command.subject.QuizSubjectCommand;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubjectCommandTest {
    private SubjectList subjects;
    private AddSubjectCommand addCommand;
    private DeleteSubjectCommand deleteCommand;
    private FindSubjectCommand findCommand;
    private ReturnSubjectCommand returnCommand;
    private ListSubjectCommand listCommand;

    @BeforeEach
    void setUp() {
        subjects = new SubjectList(
                new ArrayList<>(List.of(
                        new Subject("English"),
                        new Subject("Chinese"),
                        new Subject("Science")
                )));
    }

    @Test
    public void addSubject_validCommand_returnsSubjectTitle() throws NoSubjectException, RepeatedSubjectException {
        addCommand = new AddSubjectCommand("add Maths");
        addCommand.execute(subjects);
        assertEquals("Maths", subjects.getList().get(3).getTitle());
    }

    @Test
    public void deleteSubject_validCommand_returnsIndex() {
        deleteCommand = new DeleteSubjectCommand("delete 2");
        deleteCommand.execute(subjects);
        assertEquals(Integer.valueOf("2"), subjects.getList().size());
    }

    @Test
    public void findSubject_validCommand_executesMethod() {
        findCommand = new FindSubjectCommand("find English");
        findCommand.execute(subjects);
    }

    @Test
    public void listSubject_validCommand_executesMethod() {
        listCommand = new ListSubjectCommand();
        listCommand.execute(subjects);
    }

    @Test
    public void quizSubject_subjectNotPresent_throwsException() {
        QuizSubjectCommand quizNoSubject = new QuizSubjectCommand("quiz Maths");
        assertThrows(NoSubjectException.class, () -> quizNoSubject.execute(subjects));

    }

    @Test
    public void quizSubject_invalidCommand_throwsException() {
        QuizSubjectCommand quizInvalidCommand = new QuizSubjectCommand("quiz");
        assertThrows(InvalidSubjectException.class, () -> quizInvalidCommand.execute(subjects));

    }

    @Test
    public void resultSubject_subjectNotPresent_throwsException() {
        ResultSubjectCommand resultNoSubject = new ResultSubjectCommand("quiz Maths");
        assertThrows(NoSubjectException.class, () -> resultNoSubject.execute(subjects));

    }

    @Test
    public void resultSubject_invalidCommand_throwsException() {
        ResultSubjectCommand resultInvalidCommand = new ResultSubjectCommand("quiz");
        assertThrows(InvalidSubjectException.class, () -> resultInvalidCommand.execute(subjects));

    }


    @Test
    public void returnSubject_validCommand_returnsSubjectTitle() throws NoSubjectException {
        returnCommand = new ReturnSubjectCommand("subject Chinese");
        Subject testSubject = returnCommand.execute(subjects);
        assertEquals("Chinese", testSubject.getTitle());
    }

}
