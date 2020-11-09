package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import seedu.revised.card.Subject;
import seedu.revised.command.subjectcommand.*;
import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskCommandTest {
    private SubjectList subjects;
    private AddSubjectCommand addCommand;
    private DeleteSubjectCommand deleteCommand;
    private FindSubjectCommand findCommand;
    private AccessSubjectCommand accessCommand;
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
}
