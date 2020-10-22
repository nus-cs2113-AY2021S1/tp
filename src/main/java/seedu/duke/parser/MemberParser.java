package seedu.duke.parser;

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
        switch (action.toLowerCase()) {
        case ADD:
            new MemberCommand().addMemberCommand(parameters, projectListManager);
            break;
        case DELETE:
            new MemberCommand().deleteMemberCommand(parameters, projectListManager);
            break;
        default:
            throw new DukeException("Invalid action");
        }
    }
}
