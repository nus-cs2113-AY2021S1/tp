package seedu.duke.parser;

import seedu.duke.command.member.AddMemberCommand;
import seedu.duke.command.member.DeleteMemberCommand;
import seedu.duke.command.member.MemberCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;

public class MemberParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectList projectListManager)
            throws DukeException {
        if (parameters.get("0").isBlank()) {
            throw new DukeException("missing name");
        }
        if (projectListManager.isEmpty()) {
            throw new DukeException("You currently have no projects created");
        }
        switch (action.toLowerCase()) {
        case ADD:
            new AddMemberCommand(parameters, projectListManager).execute();
            break;
        case DELETE:
            new DeleteMemberCommand(parameters, projectListManager).execute();
            break;
        default:
            throw new DukeException("Invalid action");
        }
    }
}
