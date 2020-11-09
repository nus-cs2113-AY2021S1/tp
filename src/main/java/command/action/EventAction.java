//@@author TomLBZ

package command.action;

import command.ParamNode;
import data.Data;
import data.jobs.Event;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;

/**
 * The type Event action.
 */
public class EventAction extends Action {

    private String description;
    private String at;

    @Override
    public String act(Data data) throws Exception {
        String result = super.act(data);
        Event event = new Event(description, at);
        data.addTask(event);
        return replaceStrings(result, event.toString(), data.tasks.size());
    }

    @Override
    public void prepare(ParamNode args) throws Exception {
        super.prepare(args);
        if (flattenedArgs.length <= 1) {
            throw new InvalidCommandException();
        }
        if (flattenedArgs[0] == null) {
            throw new MissingDescriptionException();
        }
        if (flattenedArgs[1].thisData == null) {
            throw new InvalidCommandException();
        }
        at = flattenedArgs[1].thisData.toFlatString();
        description = flattenedArgs[0].toFlatString();
    }
}
