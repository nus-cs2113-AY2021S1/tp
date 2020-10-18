package seedu.duke.command.member;

import seedu.duke.exception.DukeException;
import seedu.duke.sprint.Member;
import seedu.duke.project.Project;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class MemberCommand {
    public void addMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) throws DukeException {
        Member m;
        try {
            if (projectList.isEmpty()) {
                throw new DukeException("Please create a project first.");
            }
            if (userId.get(0).isBlank()) {
                throw new DukeException("Missing parameters.");
            }
            Project proj = projectList.get(0);
            for (String s : userId) {
                if (proj.getProjectMember().containMember(new Member(s))) {
                    Ui.showToUserLn(s + " is already associated to the project.");
                } else {
                    m = new Member(s);
                    proj.getProjectMember().addMember(m);
                    Ui.showToUserLn(s + " has been added to the project.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("Required index of project not found. Please create before proceeding.");
        }
    }

    public void deleteMemberCommand(ArrayList<String> userId, ArrayList<Project> projectList) throws DukeException {
        Project proj;
        try {
            if (projectList.isEmpty()) {
                throw new DukeException("Please create a project first.");
            }
            if (userId.get(0).isBlank()) {
                throw new DukeException("Missing parameters.");
            }
            proj = projectList.get(0);
            for (String s : userId) {
                if (proj.getProjectMember().containMember(new Member(s))) {
                    proj.getProjectMember().removeMember(new Member(s));
                    Ui.showToUserLn(s + " has been removed from the project.");
                } else {
                    Ui.showToUserLn(s + " is not associated with the project.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.showError("Required index of project not found. Please create before proceeding.");
        }
    }

}
