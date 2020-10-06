package seedu.duke.command;

public abstract class Command {
    protected boolean isExit;
    protected String command;

    /**
     * abstract class for commands.
     * @param tasks
     * @param ui
     * @param storage
     */
    //abstract public void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Signal exit program.
     *
     * @return true if user give exit seedu.duke.command
     */
    public boolean isExit() {
        return isExit;
    }
}
