package command;

import ui.Printer;

public class ExitCommand extends Command {
    public static final String invoker = "/exit";

    public ExitCommand(Printer printer) {
        super(printer);
    }

    @Override
    public void execute() {
        isExitCommand = true;
    }
}
