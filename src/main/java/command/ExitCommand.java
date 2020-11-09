package command;

import ui.Printer;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    public static final String invoker = "/exit";

    /**
     * Constructor for ExitCommand.
     *
     * @param printer The printer object handles the user interaction.
     */
    public ExitCommand(Printer printer) {
        super(printer);
    }

    /**
     * Sets the exit flag to true so that in main, the program would escape the while loop.
     */
    @Override
    public void execute() {
        isExitCommand = true;
    }
}
