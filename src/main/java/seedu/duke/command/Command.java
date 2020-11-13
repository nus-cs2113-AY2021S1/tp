package seedu.duke.command;

import seedu.duke.model.DayMap;

import java.io.IOException;
import java.util.logging.Logger;

//@@author chewyang
/**
 * Execute command.
 */
public class Command {
    protected DayMap dayMap;
    protected boolean canBeChained = false;

    /**
     * This method is to be override by the specific commands.
     */
    public void execute() {
    }

    /**
     * Sets data.
     *
     * @param dayMap date
     */
    public void setData(DayMap dayMap) {
        this.dayMap = dayMap;
    }

    /**
     * Checks if command can be chained.
     *
     * @return true or false
     */
    public boolean getCanBeChained() {
        return canBeChained;
    }
}
