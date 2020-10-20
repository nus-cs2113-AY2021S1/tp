package seedu.duke.parser;

import seedu.duke.command.member.MemberCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;

public class MemberParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ArrayList<Project> projectList)
            throws DukeException {
        if (parameters.get("0").isBlank()) {
            throw new DukeException("missing name");
        }
        switch (action.toLowerCase()) {
        case ADD:
            new MemberCommand().addMemberCommand(parameters, projectList);
            break;
        case DELETE:
            new MemberCommand().deleteMemberCommand(parameters, projectList);
            break;
        default:
            throw new DukeException("Invalid action");
        }
    }
}
