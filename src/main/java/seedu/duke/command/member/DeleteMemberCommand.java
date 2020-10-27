package seedu.duke.command.member;

import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class DeleteMemberCommand extends MemberCommand {


    public DeleteMemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, projectManager, true);
    }

    public void execute() {
        Project proj;
        proj = projectManager.getSelectedProject();
        for (int i = 0; i < parameters.size(); i++) {
            // Check if the member is associated with the project.
            if (proj.getProjectMember().containMember(new Member(parameters.get(Integer.toString(i))))) {
                proj.getProjectMember().removeMember(new Member(parameters.get(Integer.toString(i))));
                Ui.showToUserLn(parameters.get(Integer.toString(i))
                        + " has been removed from the project.");
            } else {
                Ui.showToUserLn(parameters.get(Integer.toString(i))
                        + " is not associated with the project.");
            }
        }
    }
}
