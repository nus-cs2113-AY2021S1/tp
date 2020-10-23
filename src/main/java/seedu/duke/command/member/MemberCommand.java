package seedu.duke.command.member;

import seedu.duke.command.Command;
import seedu.duke.model.project.ProjectList;

import java.util.Hashtable;

public abstract class MemberCommand extends Command {

    ProjectList projectManager;

    public MemberCommand(Hashtable<String, String> parameters, ProjectList projectManager) {
        super(parameters);
        this.projectManager = projectManager;
    }

}
