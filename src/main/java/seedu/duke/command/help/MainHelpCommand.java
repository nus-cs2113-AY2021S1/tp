package seedu.duke.command.help;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class MainHelpCommand extends HelpCommand {

    public MainHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        logExecution();
        Ui.showToUserLn("Here is the list of commands used in SCRUMptious. "
                + "Type \"help /*\", where * is the command number for more information on that specific command.");
        Ui.showToUserLn("1. Project");
        Ui.showToUserLn("2. Member");
        Ui.showToUserLn("3. Task");
        Ui.showToUserLn("4. Sprint");
        Ui.showToUserLn("5. Storage");
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Viewed help menu.");
    }
}
