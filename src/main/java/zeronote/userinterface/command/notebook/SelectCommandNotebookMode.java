package zeronote.userinterface.command.notebook;

import zeronote.exceptions.InvalidSelectCommandException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.userinterface.AppState;
import zeronote.userinterface.InputParser;
import zeronote.userinterface.command.CliCommand;

//@@author chuckiex3

/**
 * Command class to select a notebook/section/page or a combination of the 3.
 * The executable commands will depend on the mode the user is in.
 */
public class SelectCommandNotebookMode extends CliCommand {
    public static final String COMMAND_WORD = "select";
    public static final String SHOW_ALL = "/all";
    private final String argument;

    public SelectCommandNotebookMode(String argument, AppState uiMode) {
        this.setAppState(uiMode);
        this.argument = argument;
        PRINTS_PERSONAL_MESSAGE = false;
    }

    @Override
    public void execute() throws ZeroNoteException {
        InputParser parser = new InputParser();
        switch (appState.getAppMode()) {
        case NOTEBOOK_SHELF:
        case NOTEBOOK_BOOK:
        case NOTEBOOK_SECTION:
        case NOTEBOOK_PAGE:
            parser.extractParams(argument, appState);
            break;
        default:
            throw new InvalidSelectCommandException(argument);
        }
    }
}
