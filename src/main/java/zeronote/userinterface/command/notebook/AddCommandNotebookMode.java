package zeronote.userinterface.command.notebook;

import zeronote.exceptions.AddCommandNotebookException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppState;
import zeronote.userinterface.command.CliCommand;

//@@author chuckiex3

/**
 * Command class to add a notebook/section/page.
 */
public class AddCommandNotebookMode extends CliCommand {
    public static final String COMMAND_WORD = "add";
    public static final String NOTEBOOK_DELIMITER = "/n";
    public static final String SECTION_DELIMITER = "/s";
    public static final String PAGE_DELIMITER = "/p";
    public static final String CONTENT_DELIMITER = ";";
    private final String title;
    private String content;
    private final NotebookShelf currentBookshelf;
    private final Notebook currentNotebook;
    private final Section currentSection;

    private static final boolean isAutoSave = true;

    public AddCommandNotebookMode(String title, AppState appState) {
        PRINTS_PERSONAL_MESSAGE = true;
        this.appState = appState;
        this.title = title;
        assert title != null;
        currentBookshelf = appState.getCurrentBookShelf();
        currentNotebook = appState.getCurrentNotebook();
        currentSection = appState.getCurrentSection();
    }

    public AddCommandNotebookMode(String title, String content, AppState appState) {
        this(title, appState);
        this.content = content;
    }

    public void execute() throws ZeroNoteException {
        switch (appState.getAppMode()) {
        case NOTEBOOK_SHELF:
            currentBookshelf.addNotebook(title);
            System.out.println("Added notebook with title: " + title);
            break;
        case NOTEBOOK_BOOK:
            currentNotebook.addSection(title);
            System.out.println("Added section with title : " + title);
            break;
        case NOTEBOOK_SECTION:
            currentSection.addPage(title, content);
            System.out.println("Added page with title: " + title);
            break;
        default:
            throw new AddCommandNotebookException(title);
        }
    }

    //@@author yAOwzers
    @Override
    public boolean isTriggerAutoSave() {
        return isAutoSave;
    }
}
