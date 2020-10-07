package seedu.duke.command;

import seedu.duke.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId) {
        Member m;
        for (String s : userId) {
            if (Project.member.memberList.contains(new Member(s))) {
                System.out.println("The user associated with " + s + " is already added to the project");
            } else {
                m = new Member(s);
                Project.member.memberList.add(m);
                System.out.println("The user associated with " + s + " has been added");
            }
        }
    }

    public void deleteMemberCommand(ArrayList<String> userId, Ui ui) {

        for (String s : userId) {
            if (Project.member.memberList.contains(new Member(s))) {
                Project.member.memberList.remove(new Member(s));
                ui.removeMember(s);
            } else {
                ui.memberNotFound(s);
            }
        }
    }
}
