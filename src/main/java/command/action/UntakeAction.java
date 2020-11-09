//@@author TomLBZ

package command.action;

import data.Item;
import data.SingleModule;

/**
 * The type Untake action.
 */
public class UntakeAction extends TakeAction {

    @Override
    protected boolean modifyObject(Item item) {
        if (((SingleModule)item).isCompleted) {
            return false;
        }
        ((SingleModule)item).isTaken = false;
        ((SingleModule)item).grade = null;
        successes++;
        return true;
    }

    @Override
    protected String getObjectInfo(Item item) {
        return item.getName() + ": no longer taken";
    }
}
