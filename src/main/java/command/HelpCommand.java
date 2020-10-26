package command;

import ui.Printer;

public class HelpCommand extends Command {
    public static final String invoker = "/help";

    public HelpCommand(Printer printer) {
        super(printer);
    }

    @Override
    public void execute() {
        printer.printHelpSheet();
    }
}
