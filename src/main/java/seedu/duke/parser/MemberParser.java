package seedu.duke.parser;

import seedu.duke.command.member.AddMemberCommand;
import seedu.duke.command.member.DeleteMemberCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.DELETE;

public class MemberParser implements ExceptionsParser {

    @Override
    public void parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                ProjectManager projectListManager)
            throws DukeException {
        assert parameters.get("0") != null : "Invalid Input";
        if (parameters.get("0") == null) {
            Ui.showError("Please do not enter dashes.");
            return;
        }
        if (!parameters.containsKey("0")) {
            throw new DukeException("Missing name!");
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
