//@@author thatseant

package seedu.ezmanager.commands.task;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.Parser.getHashValue;
import static seedu.ezmanager.Util.HOUR_INDEX_KEY;
import static seedu.ezmanager.Util.INVALID_TASK_ID;
import static seedu.ezmanager.Util.MINUTES_IN_HOUR_INT;
import static seedu.ezmanager.Util.MINUTE_INDEX_KEY;
import static seedu.ezmanager.Util.TASK_INDEX_KEY;
import static seedu.ezmanager.Util.USER_JAVA_INDEX_DIFF;

/**
 * Command that adds the estimated time taken for a specific task.
 */
public class EstimatedTimeCommand extends Command {

    private int taskIndex;
    private int durationInMinutes;
    private int projectIndex;
    HashMap<String, String> params;

    /**
     * Constructor for EstimatedTimeCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public EstimatedTimeCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves task index, estimated time in hours and minutes from parameter hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, TASK_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
            int hours = Integer.parseInt(getHashValue(params, HOUR_INDEX_KEY));
            int minutes = Integer.parseInt(getHashValue(params, MINUTE_INDEX_KEY));
            durationInMinutes = hours * MINUTES_IN_HOUR_INT + minutes;
        } catch (NumberFormatException e) {
            throw new EzExceptions(INVALID_TASK_ID);
        }
    }

    /**
     * Executes command to add estimated time taken to tasks.
     * @param projects list of all projects in program
     * @param members list of all members in program
     * @return duration added UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> members) throws EzExceptions {
        try {
            EzLogger.log(Level.INFO, "Executing Command");
            Project project = projects.get(projectIndex);
            Task task = project.getTask(taskIndex);
            EzLogger.log(Level.INFO, "Task Retrieved");
            task.addEstimate(durationInMinutes);
            EzLogger.log(Level.INFO, "Estimated Time Taken added to task.");
            int hours = task.getEstimate() / MINUTES_IN_HOUR_INT;
            int minutes = task.getEstimate() % MINUTES_IN_HOUR_INT;
            return Ui.printEstimateAddedMessage(task.getDescription(), hours, minutes);
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Task Not Found.");
            throw new EzExceptions(INVALID_TASK_ID);
        }
    }

    /**
     * Checks if command will exit program.
     * @return isExit status.
     */
    public Boolean isExit() {
        return false;
    }

}