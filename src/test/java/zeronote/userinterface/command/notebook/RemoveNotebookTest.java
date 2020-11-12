package zeronote.userinterface.command.notebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import zeronote.notebooks.Notebook;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppMode;
import zeronote.userinterface.AppState;

class RemoveNotebookTest {
    @Test
    void execute_existingNotebookTitleInShelf_notebookDoesNotExist() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        String title = "Notebook 1";
        Notebook n = new Notebook(title);
        appState.getCurrentBookShelf().addNotebook(n);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode(title, "", "", appState);
        r.execute();
        assertEquals(-1, appState.getCurrentBookShelf().findNotebook(title));
    }

    @Test
    void execute_emptyNotebookTitleInShelf_noChangeObserved() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        String title = "Notebook 1";
        Notebook n = new Notebook(title);
        appState.getCurrentBookShelf().addNotebook(n);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "", "", appState);
        r.execute();
        assertEquals(0, appState.getCurrentBookShelf().findNotebook(title));
    }

    @Test
    void execute_invalidNotebookTitleInShelf_notebookDoesNotExist() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        appState.getCurrentBookShelf().addNotebook(n);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 2", "", "", appState);
        r.execute();
        ArrayList<Notebook> notebookArrayList = appState.getCurrentBookShelf().getNotebooksArrayList();
        assertEquals(1, notebookArrayList.size());
    }

    @Test
    void execute_notebookTitleInNotebook_sectionNotRemoved() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 1", "", "", appState);
        r.execute();
        assertEquals(0, appState.getCurrentBookShelf().findNotebook("Notebook 1"));
    }

    @Test
    void execute_existingSectionTitleInShelf_sectionDoesNotExist() {
        AppState appState = new AppState();
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        appState.setCurrentSection(s);
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 1", "Section 1", "", appState);
        r.execute();
        assertEquals(-1, appState.getCurrentNotebook().findSection("Section 1"));
    }

    @Test
    void execute_existingSectionTitleInNotebook_sectionDoesNotExist() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "Section 1", "",
                appState);
        r.execute();
        assertEquals(-1, appState.getCurrentNotebook().findSection("Section 1"));
    }

    @Test
    void execute_invalidSectionTitleInNotebook_noSectionRemoved() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        Page p = new Page("Page 1", "lorem ipsum");
        s.addPage(p);
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "Section 2", "Page 1", appState);
        r.execute();
        ArrayList<Section> sectionArrayList = appState.getCurrentNotebook().getSectionArrayList();
        assertEquals(1, sectionArrayList.size());
    }

    @Test
    void execute_emptySectionTitleAndPageTitleInNotebook_noSectionRemoved() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_BOOK);


        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "", "", appState);
        r.execute();
        ArrayList<Section> sectionArrayList = appState.getCurrentNotebook().getSectionArrayList();
        assertEquals(1, sectionArrayList.size());
    }

    @Test
    void execute_notebookAndSectionTitleInSection_sectionNotRemoved() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_SECTION);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 1", "Section 1",
                "", appState);
        r.execute();
        assertEquals(0, appState.getCurrentNotebook().findSection("Section 1"));
    }

    @Test
    void execute_existingPageTitleInShelf_pageDoesNotExist() {
        AppState appState = new AppState();
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        Page p = new Page("Page 1", "lorem ipsum");
        s.addPage(p);
        appState.setCurrentSection(s);
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 1", "Section 1",
                "Page 1", appState);
        r.execute();
        assertEquals(-1, appState.getCurrentSection().findPage("Page 1"));
    }

    @Test
    void execute_missingSectionTitleInShelf_pageNotRemoved() {
        AppState appState = new AppState();
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        Page p = new Page("Page 1", "lorem ipsum");
        s.addPage(p);
        appState.setCurrentSection(s);
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("Notebook 1", "",
                "Page 1", appState);
        r.execute();
        ArrayList<Page> pageArrayList = appState.getCurrentSection().getPageArrayList();
        assertEquals(1, pageArrayList.size());
    }

    @Test
    void execute_existingPageTitleInSection_pageDoesNotExist() {
        AppState appState = new AppState();
        Notebook n = new Notebook("Notebook 1");
        Section s = new Section("Section 1");
        Page p = new Page("Page 1", "lorem ipsum");
        s.addPage(p);
        appState.setCurrentSection(s);
        n.addSection(s);
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_SECTION);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "", "Page 1", appState);
        r.execute();
        assertEquals(-1, appState.getCurrentSection().findPage("Page 1"));
    }

    @Test
    void execute_pageMode_noChangeObserved() {
        AppState appState = new AppState();
        Notebook n = new Notebook("Notebook 1");
        appState.setCurrentNotebook(n);
        appState.getCurrentBookShelf().addNotebook(n);
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "", "", appState);
        r.execute();
        assertEquals(0, appState.getCurrentBookShelf().findNotebook("Notebook 1"));
    }


    @Test
    void isTriggerAutoSave_correctMode_returnsTrue() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);

        RemoveCommandNotebookMode r = new RemoveCommandNotebookMode("", "", "", appState);
        assertTrue(r.isTriggerAutoSave());
    }
}