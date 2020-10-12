package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.timetable.Timetable;
import seedu.duke.storage.StorageManager;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteNoteCommandTest {
    private Notebook deletedNotebook;
    private Notebook expectedNotebook;
    private Notebook notebook = new Notebook();
    private Timetable timetable = new Timetable();
    private TagManager tagManager = new TagManager();
    private StorageManager storageManager = new StorageManager();

    @BeforeEach
    public void setUp() {
        Note javaNote = new Note("Java OOP", "Encapsulation\nAbstraction", true);
        Note cppNote = new Note("C++ Standard Libraries", "Core library\nStandard library", false);

        AddNoteCommand command = null;
        try {
            command = new AddNoteCommand(javaNote);
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        command.setData(notebook, timetable, tagManager, storageManager);
        command.execute();
        deletedNotebook = notebook;

        try {
            command = new AddNoteCommand(cppNote);
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        command.setData(notebook, timetable, tagManager, storageManager);
        command.execute();

        expectedNotebook = notebook;
    }

    @Test
    public void execute_validIndex_personIsDeleted() {
        assertDeletionSuccessfulInteger(1, notebook);
        assertDeletionSuccessfulString("java", notebook);
    }

    /**
     * Asserts that the person at the specified index can be successfully deleted.
     * The addressBook passed in will not be modified (no side effects).
     */
    private void assertDeletionSuccessfulInteger(int targetVisibleIndex, Notebook notebook) {

        String deleteTarget = notebook.getNotes().get(targetVisibleIndex).getTitle();
        String expectedMessage = DeleteNoteCommand.COMMAND_SUCCESSFUL_MESSAGE + deleteTarget;

        DeleteNoteCommand command = createDeleteCommand(targetVisibleIndex, notebook);
        String result = command.execute();

        assertEquals(expectedMessage, result);
        assertEquals(deletedNotebook.getNotes(), notebook.getNotes());
    }

    /**
     * Asserts that the person at the specified index can be successfully deleted.
     * The addressBook passed in will not be modified (no side effects).
     */
    private void assertDeletionSuccessfulString(String targetString, Notebook notebook) {

        ArrayList<Note> deletedListTitle = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s.getTitle().toLowerCase().contains(targetString.toLowerCase()))
                .collect(toList());
        String deleteTarget = deletedListTitle.get(0).getTitle();
        String expectedMessage = DeleteNoteCommand.COMMAND_SUCCESSFUL_MESSAGE + deleteTarget;

        DeleteNoteCommand command = createDeleteCommand(targetString, notebook);
        String result = command.execute();

        assertEquals(expectedMessage, result);
        assertEquals(deletedNotebook.getNotes(), notebook.getNotes());
    }

    @Test
    public void execute_emptyAddressBook_returnsPersonNotFoundMessage() {
        assertDeletionFailsDueToNoSuchPersonInteger(2, notebook);
        assertDeletionFailsDueToNoSuchPersonString("ZZZ", notebook);
    }

    /**
     * Creates a new delete command.
     *
     * @param targetVisibleIndex of the person that we want to delete
     */
    private DeleteNoteCommand createDeleteCommand(int targetVisibleIndex, Notebook notebook) {

        DeleteNoteCommand command = new DeleteNoteCommand(targetVisibleIndex);
        command.setData(notebook, timetable, tagManager, storageManager);

        return command;
    }

    /**
     * Creates a new delete command.
     *
     * @param targetVisibleString of the person that we want to delete
     */
    private DeleteNoteCommand createDeleteCommand(String targetVisibleString, Notebook notebook) {
        DeleteNoteCommand command = new DeleteNoteCommand(targetVisibleString);
        command.setData(notebook, timetable, tagManager, storageManager);

        return command;
    }

    /**
     * Asserts that the person at the specified index cannot be deleted, because that person
     * is not in the address book.
     */
    private void assertDeletionFailsDueToNoSuchPersonInteger(int visibleIndex, Notebook notebook) {
        DeleteNoteCommand command = createDeleteCommand(visibleIndex, notebook);
        String result = command.execute();

        assertEquals(DeleteNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE, result);
        assertEquals(expectedNotebook.getNotes(), notebook.getNotes());
    }

    /**
     * Asserts that the person at the specified index cannot be deleted, because that person
     * is not in the address book.
     */
    private void assertDeletionFailsDueToNoSuchPersonString(String title, Notebook notebook) {
        DeleteNoteCommand command = createDeleteCommand(title, notebook);
        String result = command.execute();

        assertEquals(DeleteNoteCommand.COMMAND_UNSUCCESSFUL_MESSAGE, result);
        assertEquals(expectedNotebook.getNotes(), notebook.getNotes());
    }
}
