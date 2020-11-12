package zeronote.userinterface.command;

import zeronote.userinterface.AppState;
import zeronote.userinterface.CliMessages;

// @@author neilbaner

public class Exit extends CliCommand {
    public static final String COMMAND_WORD = "exit";

    public Exit(String argument, AppState appState) {
        this.setAppState(appState);
        this.setCommandParams(argument);
        isAutoSave = true;
        PRINTS_PERSONAL_MESSAGE = false;
    }

    public void execute() {
        CliMessages messages = new CliMessages();
        messages.printGoodBye();
    }
}
