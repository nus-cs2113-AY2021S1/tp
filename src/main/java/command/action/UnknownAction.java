//@@author TomLBZ

package command.action;

import constants.Constants;
import data.Data;

/**
 * The type Unknown action.
 */
public class UnknownAction extends Action {

    @Override
    public String act(Data data) throws Exception {
        return Constants.messageMap.getOrDefault(Constants.UNKNOWN, Constants.INVALID);
    }

}
