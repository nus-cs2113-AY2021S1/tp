package zeronote.userinterface.command;

import zeronote.exceptions.ZeroNoteException;
import zeronote.userinterface.AppState;

// @@author neilbaner

public abstract class CliCommand {
    public static final String COMMAND_WORD = "mode";
    protected AppState appState;
    protected String commandParams;
    protected static boolean isAutoSave = false;
    protected static boolean PRINTS_PERSONAL_MESSAGE = true;

    public abstract void execute() throws ZeroNoteException;

    public AppState getAppState() {
        return appState;
    }

    public void setAppState(AppState appState) {
        this.appState = appState;
    }

    //@@author chuckiex3
    public String getCommandParams() {
        return commandParams;
    }

    //@@author neilbaner
    public void setCommandParams(String params) {
        commandParams = params;
    }

    //@@author yAOwzers
    public boolean isTriggerAutoSave() {
        return isAutoSave;
    }

    //@@author neilbaner
    public boolean printsPersonalMessage() {
        return PRINTS_PERSONAL_MESSAGE;
    }
}
