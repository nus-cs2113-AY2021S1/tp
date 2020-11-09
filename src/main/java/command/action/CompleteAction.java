//@@author TomLBZ

package command.action;

import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;

public class CompleteAction extends TakeAction {

    @Override
    public String act(Data data) throws Exception {
        successes = 0;
        return super.act(data).replace("taken", "completed")
                .replace(Constants.MODIFY_FAILED, Constants.NOT_COMPLETABLE);
    }

    @Override
    protected boolean modifyObject(Item item) {
        assert item instanceof SingleModule;
        if (((SingleModule) item).complete()) {
            successes++;
            return true;
        } else {
            return false;
        }
    }
}
