package zeronote.userinterface.command.notebook;

import zeronote.exceptions.IncorrectAppModeException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppState;
import zeronote.userinterface.CliMessages;
import zeronote.userinterface.command.CliCommand;

//@@author Lusi711

/**
 * Class to set a tag to a notebook, section, or page the user is currently in.
 */
public class TagCommandNotebookMode extends CliCommand {
    public static final String COMMAND_WORD = "tag";
    private static final boolean isAutoSave = true;


    public TagCommandNotebookMode(String params, AppState appState) {
        this.appState = appState;
        this.setCommandParams(params);
        assert this.commandParams != null;

        PRINTS_PERSONAL_MESSAGE = true;
    }

    /**
     * Determines the mode of the app and set a tag to the corresponding notebook, section, or page.
     */
    public void execute() {
        CliMessages cliMessages = new CliMessages();
        try {
            switch (appState.getAppMode()) {
            case NOTEBOOK_BOOK:
                Notebook currentNotebook = appState.getCurrentNotebook();
                currentNotebook.setTag(commandParams);
                cliMessages.printTagNotebookMessage(currentNotebook.getTitle(), currentNotebook.getTag());
                break;
            case NOTEBOOK_SECTION:
                Section currentSection = appState.getCurrentSection();
                currentSection.setTag(commandParams);
                cliMessages.printTagNotebookMessage(currentSection.getTitle(), currentSection.getTag());
                break;
            case NOTEBOOK_PAGE:
                Page currentPage = appState.getCurrentPage();
                currentPage.setTag(commandParams);
                cliMessages.printTagNotebookMessage(currentPage.getTitle(), currentPage.getTag());
                break;
            default:
                throw new IncorrectAppModeException();
            }
        } catch (ZeroNoteException zne) {
            zne.printErrorMessage();
        }
    }

    //@@author yAOwzers
    @Override
    public boolean isTriggerAutoSave() {
        return isAutoSave;
    }
}
