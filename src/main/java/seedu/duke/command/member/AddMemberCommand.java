package seedu.duke.command.member;

import seedu.duke.exception.DukeException;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class AddMemberCommand extends MemberCommand {


    public AddMemberCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, projectManager);
    }

    public void execute() {
        if (!parameters.containsKey("0")) {
            Ui.showError("missing name");
        } else if (projectManager.size() == 0) {
            Ui.showError("You currently have no projects created");
        } else {
            Member m;
            Project proj = projectManager.getSelectedProject();
            for (int i = 0; i < parameters.size(); i++) {
                if (proj.getProjectMember().containMember(new Member(parameters.get(Integer.toString(i))))) {
                    Ui.showToUserLn(parameters.get(Integer.toString(i))
                            + " is already associated to the project.");
                } else {
                    m = new Member(parameters.get(Integer.toString(i)));
                    proj.getProjectMember().addMember(m);
                    Ui.showToUserLn(parameters.get(Integer.toString(i)) + " has been added to the project.");
                }
            }
        }
    }
}
