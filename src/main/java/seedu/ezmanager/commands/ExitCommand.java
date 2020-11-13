package seedu.ezmanager.commands;


import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import java.util.ArrayList;
import seedu.ezmanager.ui.Ui;

/**
 * Displays goodbye message when application is terminated by the user.
 */
public class ExitCommand extends Command {
    /**
     * Print goodbye message.
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print goodbye message.
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        return printGoodbyeMessage();
    }

    public Boolean isExit() {
        return true;
    }

    private String printGoodbyeMessage() {
        return Ui.printGoodbyeMessage();
    }
}
