//@@author TomLBZ

package command.action;

import data.Item;

public class UnselectAction extends SelectAction {

    @Override
    protected boolean modifyObject(Item item) {
        item.isSelected = false;
        successes++;
        return true;
    }

    @Override
    protected String getObjectInfo(Item item) {
        return item.getName() + ": no longer selected";
    }
}
