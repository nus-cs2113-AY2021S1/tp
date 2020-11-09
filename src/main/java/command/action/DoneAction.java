//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.jobs.Task;
import exceptions.InvalidCommandException;

/**
 * The type Done action.
 */
public class DoneAction extends Action {

    private int index;

    @Override
    public String act(Data data) throws Exception {
        Item item = data.get(index);
        if (item == null) {
            return Constants.INDEX_OUT;
        } else if (item instanceof Task) {
            ((Task)item).markAsDone();
            data.updateItem(index, item);
            String result = super.act(data);
            return result.replace(Constants.TEXT_PLACEHOLDER, item.toString());
        } else {
            return Constants.NOT_TASK;
        }
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (args.thisData == null || args.thisData.name == null) {
            throw new InvalidCommandException();
        }
        index = getIndex(args.thisData.name);
        assert index > -1 : "index should be same as input";
    }
}
