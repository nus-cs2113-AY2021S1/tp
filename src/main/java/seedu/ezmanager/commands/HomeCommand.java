package seedu.ezmanager.commands;

import seedu.ezmanager.Parser;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;

/**
 * Switch back to home view.
 */
public class HomeCommand extends Command {

    private int projectIndex;

    public HomeCommand(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    /**
     * Switch back to home view.
     * Print home view display.
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Prints home view display.
     */
    @Override
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        if (projectIndex == -1) {
            return Ui.printHomeView(projects, teamMembers);
        } else {
            Parser.setProjectIndex(-1);
            return Ui.printHomeView(projects, teamMembers);
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
