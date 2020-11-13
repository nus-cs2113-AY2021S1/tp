//@@author thatseant

package seedu.ezmanager.commands.task;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.Parser.getHashValue;
import static seedu.ezmanager.Util.DATE_KEY;
import static seedu.ezmanager.Util.EMPTY_PROJECT_LIST;
import static seedu.ezmanager.Util.INVALID_TASK_ID;
import static seedu.ezmanager.Util.TASK_INDEX_KEY;
import static seedu.ezmanager.Util.USER_JAVA_INDEX_DIFF;
import static seedu.ezmanager.Util.WRONG_DATE_FORMAT;

/**
 * Command that adds deadline for a specific task.
 */
public class TaskAssignDeadlineCommand extends Command {
    private int projectIndex;
    private int taskIndex;
    private LocalDate date;
    HashMap<String, String> params;

    /**
     * Constructor for TaskAssignDeadlineCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskAssignDeadlineCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves task index, deadline from parameter hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, TASK_INDEX_KEY)) - USER_JAVA_INDEX_DIFF;
            date = LocalDate.parse(getHashValue(params, DATE_KEY));
        } catch (NumberFormatException e) {
            throw new EzExceptions(INVALID_TASK_ID);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            EzLogger.log(Level.WARNING, "Wrong Date Format: " + date);
            throw new EzExceptions(WRONG_DATE_FORMAT);
        }
    }

    /**
     * Executes command to add deadline to tasks.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return deadline added UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        EzLogger.log(Level.INFO, "Executing Command");
        if (projects.size() == 0) {
            throw new EzExceptions(EMPTY_PROJECT_LIST);
        }
        try {
            Project project = projects.get(projectIndex);
            Task task = project.getTaskList().get(taskIndex);
            EzLogger.log(Level.INFO, "Task Retrieved");
            task.addDeadline(date);
            EzLogger.log(Level.INFO, "Deadline added to task.");
            return Ui.printTaskDeadlineMessage(date, task.getDescription());
        } catch (IndexOutOfBoundsException e) {
            EzLogger.log(Level.WARNING, "Task Not Found.");
            throw new EzExceptions(INVALID_TASK_ID);
        }
    }

    /**
     * Checks if command will exit program.
     * @return exit status.
     */
    public Boolean isExit() {
        return false;
    }
}
