package seedu.duke.command.member;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class AddMemberCommand extends MemberCommand {


    public AddMemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, projectManager, true);
    }

    public void execute() {
        Member m;
        Project proj = projectManager.getSelectedProject();
        String projMember;
        // adds members provided, can add multiple members at once.
        for (int i = 0; i < parameters.size(); i++) {
            projMember = parameters.get(Integer.toString(i));
            // check if member is already added to the associated projectr
            if (checkMember(proj, projMember)) {
                Ui.showToUserLn(projMember
                        + " is already associated to the project.");
                logExecution("User added member " + projMember + " to project " + proj.getTitle());
            } else {
                m = new Member(projMember);
                proj.getProjectMember().addMember(m);
                Ui.showToUserLn(projMember + " has been added to the project.");
                logExecution("User tried to add a already existing member " + projMember + " to project "
                        + proj.getTitle());
            }
        }
    }

    @Override
    public void logExecution(String loggerMessage) {
        ScrumLogger.LOGGER.info(loggerMessage);
    }
}
