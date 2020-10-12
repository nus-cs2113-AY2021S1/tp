package seedu.duke.command.member;

import seedu.duke.model.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.old.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) {
        Member m;
        try {
            Project proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.memberList.contains(new Member(s))) {
                    //Ui.printMemberAlreadyAdded(s);
                } else {
                    m = new Member(s);
                    proj.members.memberList.add(m);
                    //Ui.printAddMember(s);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Ui.printError("Required index of project not found. Please create before proceeding.");
        }
    }

    public void deleteMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) {
        Project proj;
        try {
            proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.memberList.contains(new Member(s))) {
                    proj.members.memberList.remove(new Member(s));
                    // Ui.removeMember(s);
                } else {
                    // Ui.memberNotFound(s);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //ui.printError("Required index of project not found. Please create before proceeding.");
        }
    }

}
