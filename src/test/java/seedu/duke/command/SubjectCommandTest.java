package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.command.subjectcommand.AddSubjectCommand;
import seedu.duke.command.subjectcommand.DeleteSubjectCommand;
import seedu.duke.command.subjectcommand.FindSubjectCommand;
import seedu.duke.command.subjectcommand.ListSubjectCommand;
import seedu.duke.command.subjectcommand.ReturnSubjectCommand;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void addSubject() throws NoSubjectException, RepeatedSubjectException {
        addCommand = new AddSubjectCommand("add Maths");
        addCommand.execute(subjects);
        assertEquals(subjects.getList().get(3).getTitle(),"Maths");
    }

    @Test
    public void deleteSubject() {
        deleteCommand = new DeleteSubjectCommand("delete 2");
        deleteCommand.execute(subjects);
        assertEquals(subjects.getList().size(),Integer.valueOf("2"));
    }

    @Test
    public void findSubject() {
        findCommand = new FindSubjectCommand("find English");
        findCommand.execute(subjects);
    }

    @Test
    public void listSubject() {
        listCommand = new ListSubjectCommand();
        listCommand.execute(subjects);
    }

    @Test
    public void returnSubject() throws NoSubjectException {
        returnCommand = new ReturnSubjectCommand("subject Chinese");
        Subject testSubject = returnCommand.execute(subjects);
        assertEquals(testSubject.getTitle(),"Chinese");
    }
}
