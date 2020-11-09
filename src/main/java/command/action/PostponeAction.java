//@@author johanesrafael

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.Item;
import data.jobs.Deadline;
import data.jobs.Event;
import data.jobs.ToDo;
import exceptions.CommandException;

/**
 * The type Postpone Action.
 */
public class PostponeAction extends Action {

    private int index;
    private String postponeType;

    @Override
    public String act(Data data) throws Exception {
        Item item = data.get(index);
        if (item == null) {
            return Constants.INDEX_OUT;
        } else if (item instanceof Deadline || item instanceof Event) {
            item.resetDateTime(postponeType);
            data.updateItem(index, item);
            String result = super.act(data);
            return result.replace(Constants.TEXT_PLACEHOLDER, item.toString());
        } else if (item instanceof ToDo) {
            return Constants.NOT_DEADLINE_OR_EVENT;
        } else {
            return Constants.NOT_TASK;
        }
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (args.thisData == null) {
            throw new CommandException();
        }
        String[] param = args.thisData.toFlatString().split(" ");
        if (param.length == 1) {
            index = getIndex(args.thisData.name);
            postponeType = "d"; // set to d for day by default
        } else if (param.length == 2) {
            index = getIndex(param[1]);
            postponeType = param[0];
            String[] options = Constants.optionalParamMap.get(args.name);
            int count = 0;
            for (String opt : options) {
                if (opt.equals(postponeType)) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == options.length) {
                throw new CommandException();
            }
        } else {
            throw new CommandException();
        }
    }
}
