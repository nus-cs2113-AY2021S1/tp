package seedu.hdbuy.command;

public abstract class Command {

    public abstract void execute();

    public boolean isExit() {
        return this instanceof CloseCommand;
    }
}
