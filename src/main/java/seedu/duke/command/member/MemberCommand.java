package seedu.duke.command.member;

import seedu.duke.command.Command;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public abstract class MemberCommand extends Command {

    ProjectManager projectManager;

    public MemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager, boolean shouldSave) {
        super(parameters, shouldSave);
        this.projectManager = projectManager;
    }

}
