package zeronote.userinterface.command.notebook;

import org.junit.jupiter.api.Test;
import zeronote.exceptions.DuplicateFoundException;
import zeronote.exceptions.InvalidCommandException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppMode;
import zeronote.userinterface.AppState;
import zeronote.userinterface.InputParser;
import zeronote.userinterface.command.CliCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author chuckiex3

public class AddNotebookTest {
    @Test
    void addNotebook_wrongFormat_expectException() {
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);
        InputParser parser = new InputParser();
        String inputString = "add notebook";
        assertThrows(InvalidCommandException.class, () -> {
            parser.getCommandFromInput(inputString, appState);
        });
    }

    @Test
    void addNotebook_wrongMode_expectException() {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);
        String inputString = "add /nNOTEBOOK";
        assertThrows(InvalidCommandException.class, () -> {
            parser.getCommandFromInput(inputString, appState);
        });
    }

    @Test
    void addNotebook_SectionPage_expectException() {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_PAGE);
        String inputString = "add /nNOTEBOOK /sSection /p";
        assertThrows(ZeroNoteException.class, () -> {
            parser.getCommandFromInput(inputString, appState);
        });
    }

    @Test
    void addDuplicate_page_expectException() {
        NotebookShelf ns = new NotebookShelf();
        Notebook notebook = new Notebook("test_notebook");
        Section section = new Section("section");
        String page = "page";
        String content = "content";

        ns.addNotebook(notebook);
        notebook.addSection(section);

        try {
            section.addPage(page, content);
        } catch (DuplicateFoundException e) {
            e.printErrorMessage();
        }

        assertThrows(ZeroNoteException.class, () -> {
            section.addPage(page, content);
        });
    }

    @Test
    public void addNotebook_withSemicolon_expectException() {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        String inputString = "add /nNotebook ;";

        assertThrows(ZeroNoteException.class, () -> {
            parser.getCommandFromInput(inputString, appState);
        });
    }

    @Test
    void addNotebook_successfully() throws ZeroNoteException {
        InputParser parser = new InputParser();
        AppState appState = new AppState();
        appState.setAppMode(AppMode.NOTEBOOK_SHELF);
        String inputString = "add /nNotebook 365";
        CliCommand command = parser.getCommandFromInput(inputString, appState);
        command.execute();
        NotebookShelf bookshelf = appState.getCurrentBookShelf();
        assertEquals(1, bookshelf.getNotebooksArrayList().size());
    }
}
