package seedu.notus.storage;

import org.junit.jupiter.api.Test;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

//@@author prachi2023

class StorageManagerTest {

    @Test
    void saveNotebook() throws SystemException {
        Notebook notes = new Notebook();
        ArrayList<String> content = new ArrayList<>();
        StorageManager storageManager = new StorageManager();
        content.add("Hello this is a test note");
        content.add("With a few lines");
        content.add("");
        content.add("");
        content.add("This is the end of the note");

        for (int i = 0; i < 10; i++) {
            String title = "testNote_" + i;
            Boolean isPinned = false;
            Boolean isArchived = false;

            if (i % 2 == 0) {
                isPinned = true;
            }
            Note note = new Note(title, content, isPinned, isArchived);
            notes.addNote(note);
        }
        storageManager.saveNotebook(notes);
    }

    @Test
    void saveAll() {
    }

    @Test
    void loadAll() {
    }

    @Test
    void saveNoteContent() {
        ArrayList<String> content = new ArrayList<>();
        StorageManager storageManager = new StorageManager();

        content.add("Hello this is a test note");
        content.add("With a few lines");
        content.add("");
        content.add("");
        content.add("This is the end of the note");

        Note note = new Note("TestNote", content, false, false);
      
        try {
            storageManager.saveNoteContent(note, note.getIsArchived());
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
    }
}
