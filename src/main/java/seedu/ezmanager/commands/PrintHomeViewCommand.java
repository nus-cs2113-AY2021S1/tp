package seedu.ezmanager.commands;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;

/**
 * Prints the home view display.
 */
public class PrintHomeViewCommand extends Command {

    /**
     * Print home view display.
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print home view display.
     */
    @Override
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        return Ui.printHomeView(projects, teamMembers);
    }

    public Boolean isExit() {
        return false;
    }

}
