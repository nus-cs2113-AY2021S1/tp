package seedu.hdbuy.command;

public abstract class Command {

    /**
     * Execute the Command class, which will be different depends on which subclass is called.
     * By default, the Command class will do nothing and return.
     */
    public abstract void execute();

    /**
     * This method will return the isExit boolean value.
     *
     * @return The boolean value of isExit from the Close Command subclass.
     */
    public boolean isExit() {
        return this instanceof CloseCommand;
    }
}
