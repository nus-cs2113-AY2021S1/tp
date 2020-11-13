package seedu.ezmanager.commands.project;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;
import static seedu.ezmanager.Util.INDEX_NON_INTEGER;
import static seedu.ezmanager.Util.WRONG_DATE_FORMAT;
import static seedu.ezmanager.Util.INVALID_PROJECT_ID;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Add deadline to a specified Project object.
 */
public class ProjectDeadlineCommand extends Command {
    private int projectIndex;
    private LocalDate date;
    HashMap<String, String> params;

    public ProjectDeadlineCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    /**
     * Parse user parameter inputs for execution.
     *
     * @throws EzExceptions Invalid index when parameter values entered is not an integer.
     *     Invalid DateTime format when parameter values entered is not in YYYY-MM-DD format.
     */
    public void parse() throws EzExceptions {
        try {
            projectIndex = Integer.parseInt(getHashValue(params, "p")) - 1;
            date = LocalDate.parse(getHashValue(params, "d"));
        } catch (NumberFormatException e) {
            EzLogger.log(Level.WARNING, INDEX_NON_INTEGER);
            throw new EzExceptions(INDEX_NON_INTEGER);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            EzLogger.log(Level.WARNING, "Wrong date format");
            throw new EzExceptions(WRONG_DATE_FORMAT);
        }
    }

    /**
     * Add deadline to a specified project.
     * 1. Sorts the ArrayList of Projects by deadline.
     * 2. Projects without deadline will be shifted to the back of the list.
     * 3. Prints project deadline added message as well as new home view display.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Prints project deadline added message together with new home view display.
     * @throws EzExceptions Invalid Project ID when parameter values provided
     *     is outside the range of the Project list.
     */
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        try {
            Project project = projects.get(projectIndex);
            project.addProjectDeadline(date);
            EzLogger.log(Level.INFO, "Deadline added to Project");
            projects = shiftProjectWithNoDeadlineToBackOfList(projects);
            EzLogger.log(Level.INFO, "Project with no deadline shifted to the back");
            Collections.sort(projects);
            EzLogger.log(Level.INFO, "Project sorted by deadline");
            return Ui.printProjectDeadlineAddedMessage(projects, project, date, teamMembers);
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Invalid project ID");
            throw new EzExceptions(INVALID_PROJECT_ID);
        }
    }

    private ArrayList<Project> shiftProjectWithNoDeadlineToBackOfList(ArrayList<Project> projects) {
        int projectCounter = 0;
        int i = 0;
        while (projectCounter < projects.size()) {
            if (projects.get(i).getProjectDeadline() == null) {
                Project projectWithNullDeadline = projects.get(i);
                projects.remove(i);
                projects.add(projectWithNullDeadline);
            } else {
                i++;
            }
            projectCounter++;
        }
        return projects;
    }

    public Boolean isExit() {
        return false;
    }

}
