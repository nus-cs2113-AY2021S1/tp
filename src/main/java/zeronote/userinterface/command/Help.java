package zeronote.userinterface.command;

import zeronote.userinterface.CliMessages;

// @@author neilbaner

/**
 * This class is to be instantiated when the user enters the keyword "help". The arguments for the command are then
 * passed into the Help object, and executing it will display help appropriately.
 */
public class Help extends CliCommand {
    public static final String COMMAND_WORD = "help";
    // the arguments added by the user
    String commandArgs;

    /**
     * Constructor for Help.
     *
     * @param commandArgs the arguments input by the user. Precondition: these must be all lowercase, and with all
     *                    leading and trailing spaces stripped.
     */
    public Help(String commandArgs) {
        this.commandArgs = commandArgs.toLowerCase();
        PRINTS_PERSONAL_MESSAGE = false;
    }

    /**
     * Executes the command, based on the arguments entered by the user. If the arguments are not recognised, then we
     * print all the help available.
     */
    @Override
    public void execute() {
        CliMessages messages = new CliMessages();
        switch (commandArgs) {
        case "notebook":
            messages.printNotebookModeHelp();
            break;
        case "timetable":
            messages.printTimetableModeHelp();
            break;
        default:
            messages.printAllHelp();
            break;
        }
    }
}
