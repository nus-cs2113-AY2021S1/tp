package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.TrippieData;

/**
 * Superclass for all the other commands.
 */
public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(Ui ui, TrippieData trippieData);
}
