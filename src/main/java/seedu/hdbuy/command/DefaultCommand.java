package seedu.hdbuy.command;

/**
 * This command is used when the user inputs an invalid command.
 * The user will be notified of their wrong input.]
 */
public class DefaultCommand extends Command {

    protected String input;

    public DefaultCommand(String input) {
        this.input = input;
    }

    @Override public void execute() {
        // TextUi.showInvalidInput(input);
    }
}
