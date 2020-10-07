package seedu.duke.command;

import seedu.duke.project.Project;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> names) {

        Project.member.addMember(names);
    }

    public void deleteMemberCommand(ArrayList<String> names) {

        Project.member.deleteMember(names);
    }
}
