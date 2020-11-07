package seedu.duke.command.member;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.text.Format;
import java.util.Hashtable;

public class DeleteMemberCommand extends MemberCommand {

    public DeleteMemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, projectManager, true);
    }

    @Override
    public void execute() {
        Project proj;
        proj = projectManager.getSelectedProject();
        String projMember;
        for (int i = 0; i < parameters.size(); i++) {
            // Check if the member is associated with the project.
            projMember = parameters.get(Integer.toString(i));
            if (proj.getProjectMember().containMember(new Member(projMember))) {
                proj.getProjectMember().removeMember(new Member(projMember));
                Ui.showToUserLn(projMember
                        + " has been removed from the project.");
                logExecution("User removed " + projMember + " from project " + proj.getTitle());
            } else {
                Ui.showToUserLn(projMember
                        + " is not associated with the project.");
                logExecution("User tried to remove unassociated member " + projMember + " from project " + proj.getTitle());
            }
        }
    }

    @Override
    public void logExecution(String loggerMessage) {
        ScrumLogger.LOGGER.info(loggerMessage);
    }

}
