//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import exceptions.CommandException;

/**
 * The type Next action.
 */
public class NextAction extends PrevAction {

    @Override
    public String act(Data data) throws Exception {
        String result = getTargetString(Constants.optionalParamMap.get(args.name), arg);
        result = result + "1";
        return super.superAct(data).replace(Constants.TEXT_PLACEHOLDER, result);
    }

}
