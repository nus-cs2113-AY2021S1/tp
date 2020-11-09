//@@author TomLBZ

package command.action;

import constants.Constants;
import constants.HelpText;
import command.ParamNode;
import data.Data;

/**
 * The type Help action.
 */
public class HelpAction extends Action {

    /**
     * The Is detailed.
     */
    boolean isDetailed = false;
    /**
     * The Help text.
     */
    HelpText helpText = null;

    @Override
    public String act(Data data) throws Exception {
        if (isDetailed && helpText != null) {
            isDetailed = false;
            return helpText.toString();
        } else {
            StringBuilder builder = new StringBuilder(Constants.HELP_HEADING);
            for (HelpText help: HelpText.values()) {
                builder.append("Command: ").append(help.name).append(Constants.SPACE).append(Constants.SPACE).append(
                        "Description: ").append(help.description).append(Constants.WIN_NEWLINE);
            }
            builder.append(Constants.HELP_PROMPT);
            return builder.toString();
        }
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (flattenedArgs.length > 0) {
            String target = flattenedArgs[0].name;
            if (target != null) {
                isDetailed = true;
                helpText = Constants.helpMap.get(target);
            }
        }
    }
}
