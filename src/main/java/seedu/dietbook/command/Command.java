package seedu.dietbook.command;

import seedu.dietbook.exception.DietException;
import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

public abstract class Command {
    public abstract void execute(Manager manager, Ui ui) throws DietException;
}
