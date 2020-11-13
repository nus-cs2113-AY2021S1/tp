package seedu.ezmanager.commands.project;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;
import static seedu.ezmanager.Util.INDEX_NON_INTEGER;
import static seedu.ezmanager.Util.INVALID_PROJECT_ID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Adds a project description to a specified Project object.
 */
public class ProjectDescriptionCommand extends Command {

    private String projectDescription;
    private int projectIndex;
    HashMap<String, String> params;

    public ProjectDescriptionCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions Invalid index when parameter values entered is not an integer.
     */
    public void parse() throws EzExceptions {
        projectDescription = getHashValue(params, "d");
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
        } catch (NumberFormatException e) {
            EzLogger.log(Level.WARNING, "Input not an integer");
            throw new EzExceptions(INDEX_NON_INTEGER);
        }
    }


    /**
     * Add project description to specified Project object.
     * Print project description added message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Print project description added message.
     * @throws EzExceptions Invalid Project ID if when parameter values provided is outside
     *     the range of the Project list.
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        try {
            Project project = projects.get(projectIndex);
            EzLogger.log(Level.INFO, "Project Retrieved");
            project.addDescription(projectDescription);
            EzLogger.log(Level.INFO, "Description added to project");
            return Ui.printProjectDescriptionAddedMessage(project);
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Invalid project ID");
            throw new EzExceptions(INVALID_PROJECT_ID);
        }
    }

    public Boolean isExit() {
        return false;
    }
}
