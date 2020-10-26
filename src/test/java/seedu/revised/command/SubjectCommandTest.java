package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.command.subject.AddSubjectCommand;
import seedu.revised.command.subject.DeleteSubjectCommand;
import seedu.revised.command.subject.FindSubjectCommand;
import seedu.revised.command.subject.ListSubjectCommand;
import seedu.revised.command.subject.ResultSubjectCommand;
import seedu.revised.command.subject.AccessSubjectCommand;
import seedu.revised.command.subject.QuizSubjectCommand;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.storage.Storage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubjectCommandTest {
    private SubjectList subjects;
    private AddSubjectCommand addCommand;
    private DeleteSubjectCommand deleteCommand;
    private FindSubjectCommand findCommand;
    private AccessSubjectCommand returnCommand;
    private ListSubjectCommand listCommand;
    private Storage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        subjects = new SubjectList(
                new ArrayList<>(List.of(
                        new Subject("English"),
                        new Subject("Chinese"),
                        new Subject("Science")
                )));
        storage = new Storage.StorageBuilder()
                .setBaseDir(tempDir.toString())
                .setExportDir(tempDir.toString())
                .setFlashcardFilename("flashcards.json")
                .setTaskFilename("tasks.txt")
                .setResultFilename("results.json")
                .setExportFilename("data.json")
                .build();
    }

    @Test
    public void addSubject_validCommand_returnsSubjectTitle() throws RepeatedSubjectException, InvalidSubjectException {
        addCommand = new AddSubjectCommand("add Maths");
        addCommand.execute(subjects, storage);
        assertEquals("Maths", subjects.getList().get(3).getTitle());
    }

    @Test
    public void deleteSubject_validCommand_returnsIndex() {
        deleteCommand = new DeleteSubjectCommand("delete 2");
        deleteCommand.execute(subjects, storage);
        assertEquals(Integer.valueOf("2"), subjects.getList().size());
    }

    @Test
    public void findSubject_validCommand_executesMethod() throws InvalidSubjectException {
        findCommand = new FindSubjectCommand("find English");
        findCommand.execute(subjects, storage);
    }

    @Test
    public void listSubject_validCommand_executesMethod() {
        listCommand = new ListSubjectCommand();
        listCommand.execute(subjects, storage);
    }

    @Test
    public void quizSubject_subjectNotPresent_throwsException() {
        QuizSubjectCommand quizNoSubject = new QuizSubjectCommand("quiz Maths");
        assertThrows(NoSubjectException.class, () -> quizNoSubject.execute(subjects, storage));

    }

    @Test
    public void quizSubject_invalidCommand_throwsException() {
        QuizSubjectCommand quizInvalidCommand = new QuizSubjectCommand("quiz");
        assertThrows(InvalidSubjectException.class, () -> quizInvalidCommand.execute(subjects, storage));

    }

    @Test
    public void resultSubject_subjectNotPresent_throwsException() {
        ResultSubjectCommand resultNoSubject = new ResultSubjectCommand("quiz Maths");
        assertThrows(NoSubjectException.class, () -> resultNoSubject.execute(subjects, storage));

    }

    @Test
    public void resultSubject_invalidCommand_throwsException() {
        ResultSubjectCommand resultInvalidCommand = new ResultSubjectCommand("quiz");
        assertThrows(InvalidSubjectException.class, () -> resultInvalidCommand.execute(subjects, storage));

    }

}
