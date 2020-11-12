package zeronote.userinterface.command.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import zeronote.notebooks.Notebook;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppMode;
import zeronote.userinterface.AppState;

class TagNotebookTest {

    @Test
    void execute_tagNotebook_tagSet() {
        Notebook n = new Notebook("Notebook 1");
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);
        appState.setCurrentNotebook(n);

        TagCommandNotebookMode t = new TagCommandNotebookMode("test",appState);
        t.execute();
        assertEquals("test",appState.getCurrentNotebook().getTag());
    }

    @Test
    void execute_tagSection_tagSet() {
        Section s = new Section("Section 1");
        AppState appState = new AppState();
        appState.setCurrentSection(s);
        appState.setAppMode(AppMode.NOTEBOOK_SECTION);

        TagCommandNotebookMode t = new TagCommandNotebookMode("test",appState);
        t.execute();
        assertEquals("test",appState.getCurrentSection().getTag());
    }

    @Test
    void execute_tagPage_tagSet() {
        Page p = new Page("Page 1", "test");
        AppState appState = new AppState();
        appState.setCurrentPage(p);
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);

        TagCommandNotebookMode t = new TagCommandNotebookMode("test",appState);
        t.execute();
        assertEquals("test",appState.getCurrentPage().getTag());
    }

    @Test
    void execute_wrongMode_noTagSet() {
        Notebook n = new Notebook("Notebook 1");
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        appState.setCurrentNotebook(n);

        TagCommandNotebookMode t = new TagCommandNotebookMode("test",appState);
        t.execute();
        assertEquals("",appState.getCurrentNotebook().getTag());
    }

    @Test
    void isTriggerAutoSave() {
        AppState appState = new AppState();
        TagCommandNotebookMode t = new TagCommandNotebookMode("", appState);
        assertTrue(t.isTriggerAutoSave());
    }
}