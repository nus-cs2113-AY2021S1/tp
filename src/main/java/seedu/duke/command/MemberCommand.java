package seedu.duke.command;

import seedu.duke.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId, Ui ui, ArrayList<Project> projectList) {
        Member m;
        try {
            Project proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.memberList.contains(new Member(s))) {
                    ui.printMemberAlreadyAdded(s);
                } else {
                    m = new Member(s);
                    proj.members.memberList.add(m);
                    ui.printAddMember(s);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Required index of project not found. Please create before proceeding.");
        }
    }

    public void deleteMemberCommand(ArrayList<String> userId, Ui ui, ArrayList<Project> projectList) {
        Project proj;
        try {
            proj = projectList.get(0);
            for (String s : userId) {
                if (proj.members.memberList.contains(new Member(s))) {
                    proj.members.memberList.remove(new Member(s));
                    ui.removeMember(s);
                } else {
                    ui.memberNotFound(s);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Required index of project not found. Please create before proceeding.");
        }
    }

}
