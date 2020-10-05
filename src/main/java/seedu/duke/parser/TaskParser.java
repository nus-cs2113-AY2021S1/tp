package seedu.duke.parser;

import seedu.duke.command.taskcommand.*;

public class TaskParser {

    /**
     * Parses the inputs provided by the user
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static TaskCommand parse(String fullCommand){
        if(fullCommand.equals("bye")){
            return new ExitTaskCommand();
        }else if (fullCommand.startsWith("done ")) {
            return new DoneTaskCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListTaskCommand();
        } else if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        }else if (fullCommand.startsWith("delete ")){
            return new DeleteTaskCommand(fullCommand);
        }else if (fullCommand.startsWith("find")) {
            return new FindTaskCommand(fullCommand);
        }else{
            return new SorryTaskCommand();
        }
    }
}
