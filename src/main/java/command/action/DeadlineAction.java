//@@author TomLBZ

package command.action;

import command.ParamNode;
import constants.Constants;
import data.Data;
import data.jobs.Deadline;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;

/**
 * The type Deadline action.
 */
public class DeadlineAction extends Action {

    private String description;
    private String by;

    @Override
    public String act(Data data) throws Exception {
        String result = super.act(data);
        Deadline ddl = new Deadline(description, by);
        data.addTask(ddl);
        return replaceStrings(result, ddl.toString(), data.tasks.size());
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (flattenedArgs.length < 2) {
            throw new InvalidCommandException();
        }
        if (flattenedArgs[0] == null) {
            throw new MissingDescriptionException();
        }
        if (flattenedArgs[1].thisData == null) {
            throw new InvalidCommandException();
        }
        by = flattenedArgs[1].thisData.toFlatString();
        description = flattenedArgs[0].toFlatString();
    }
}
