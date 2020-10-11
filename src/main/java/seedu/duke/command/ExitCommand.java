package seedu.duke.command;

import seedu.duke.ui.Ui;

public class ExitCommand extends Command {
    
    public ExitCommand() {
        
    }

    @Override
    public void execute() {
        new Ui().printGoodbyeMessage();
    }
}
