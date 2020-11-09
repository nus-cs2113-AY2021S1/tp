//@@author TomLBZ

package command.action;

import data.Item;

public class DetailAction extends SelectAction {

    @Override
    protected boolean modifyObject(Item item) {
        return true;
    }

    @Override
    protected String getObjectInfo(Item item) {
        return item.getDetails();
    }

}
