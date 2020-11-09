package command;

import ui.Printer;

/**
 * Command to display the help page.
 */
public class HelpCommand extends Command {
    public static final String invoker = "/help";

    /**
     * Constructor for the HelpCommand.
     *
     * @param printer The printer object to handle user interaction
     */
    public HelpCommand(Printer printer) {
        super(printer);
    }

    /**
     * Prints the help page.
     */
    @Override
    public void execute() {
        printer.printHelpSheet();
    }
}
