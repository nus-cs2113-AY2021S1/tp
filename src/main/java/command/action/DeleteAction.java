//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.SingleModule;
import exceptions.InvalidCommandException;

/**
 * The type Delete action.
 */
public class DeleteAction extends Action {

    private int index;

    @Override
    public String act(Data data) throws Exception {
        if (index < 0 || index >= data.target.size()) {
            return Constants.INDEX_OUT;
        }
        Item item = data.get(index);
        String result = super.act(data);
        data.removeItem(index);
        String notice = "";
        if (item instanceof SingleModule) {
            result = result.replace(Constants.REMOVED, Constants.REMOVE_MOD);
            if (item.isSelected) {
                notice = Constants.WIN_NEWLINE + "The module is still selected, you may want to \"unsel\" it.";
            }
            if (((SingleModule) item).isTaken) {
                notice = notice + Constants.WIN_NEWLINE + "The module is still taken, you may want to \"untake\" it.";
            }
        }
        return replaceStrings(result, item.toString() + notice, data.tasks.size());
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (args.thisData == null || args.thisData.name == null) {
            throw new InvalidCommandException();
        }
        index = getIndex(args.thisData.name);
    }
}
