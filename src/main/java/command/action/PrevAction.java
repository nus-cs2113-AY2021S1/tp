//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import exceptions.CommandException;

/**
 * The type Prev action.
 */
public class PrevAction extends Action {

    protected String arg = "a";

    @Override
    public String act(Data data) throws Exception {
        String result = getTargetString(Constants.optionalParamMap.get(args.name), arg);
        result = result + "-1";
        return superAct(data).replace(Constants.TEXT_PLACEHOLDER, result);
    }

    protected String superAct(Data data) throws Exception {
        return super.act(data);
    }

    /**
     * Gets target string.
     *
     * @param params the params
     * @param arg    the arg
     * @return the target string
     */
    protected String getTargetString(String[] params, String arg) throws Exception {
        String result = "";
        String i = params[0];
        String s = params[1];
        String a = params[2];
        if (arg.equals(i)) {
            result = Constants.BMP_LIST_SWITCH;
        } else if (arg.equals(s)) {
            result = Constants.BMP_SEL_SWITCH;
        } else if (arg.equals(a)) {
            result = Constants.BMP_LIST_SWITCH + Constants.BMP_SEL_SWITCH;
        } else {
            throw new CommandException();
        }
        return result;
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        arg = "a";
        super.prepare(args);
        int len = flattenedArgs.length;
        if (len > 0) {
            arg = flattenedArgs[0].name.toLowerCase().trim();
        }
    }
}
