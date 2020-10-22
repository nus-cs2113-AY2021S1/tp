package seedu.duke.command.member;

import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class MemberCommand {
    public void addMemberCommand(Hashtable<String, String> userId, ProjectList projectListManager) {
        Member m;
        Project proj = projectListManager.getProject();
        for (int i = 0; i < userId.size(); i++) {
            if (proj.getProjectMember().containMember(new Member(userId.get(Integer.toString(i))))) {
                Ui.showToUserLn(userId.get(Integer.toString(i)) + " is already associated to the project.");
            } else {
                m = new Member(userId.get(Integer.toString(i)));
                proj.getProjectMember().addMember(m);
                Ui.showToUserLn(userId.get(Integer.toString(i)) + " has been added to the project.");
            }
        }
    }

    public void deleteMemberCommand(Hashtable<String, String> userId, ProjectList projectListManager) {
        Project proj;
        proj = projectListManager.getProject();
        for (int i = 0; i < userId.size(); i++) {
            if (proj.getProjectMember().containMember(new Member(userId.get(Integer.toString(i))))) {
                proj.getProjectMember().removeMember(new Member(userId.get(Integer.toString(i))));
                Ui.showToUserLn(userId.get(Integer.toString(i)) + " has been removed from the project.");
            } else {
                Ui.showToUserLn(userId.get(Integer.toString(i)) + " is not associated with the project.");
            }
        }
    }
}
