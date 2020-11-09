//@@author TomLBZ

package command.action;

import constants.Constants;
import data.Data;
import data.Item;
import exceptions.CommandException;

import java.util.ArrayList;

/**
 * The type Clear action.
 */
public class ClearAction extends Action {

    @Override
    public String act(Data data) throws Exception {
        if (flattenedArgs != null && flattenedArgs.length > 0) {
            if (flattenedArgs[0].name.toLowerCase().contains("fancy")) {
                return Constants.FANCY_CLEARED + Constants.WIN_NEWLINE;
            } else {
                throw new CommandException();
            }
        }
        ArrayList<Item> items = new ArrayList<>(data.getTarget());
        items.forEach(data::removeItem);
        return super.act(data);
    }

}
