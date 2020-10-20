package seedu.duke.command.member;

import seedu.duke.sprint.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class MemberCommand {
    public void addMemberCommand(Hashtable<String, String> userId, ArrayList<Project> projectList) {
        Member m;
        Project proj = projectList.get(0);
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

    public void deleteMemberCommand(Hashtable<String, String> userId, ArrayList<Project> projectList) {
        Project proj;
        proj = projectList.get(0);
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
