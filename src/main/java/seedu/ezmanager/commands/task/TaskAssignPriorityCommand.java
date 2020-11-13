package seedu.ezmanager.commands.task;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.task.Task;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Command that adds priority for a specific task.
 */
public class TaskAssignPriorityCommand extends Command {

    private int taskIndex;
    private int projectIndex;
    private int priority;
    HashMap<String, String> params;

    /**
     * Constructor for TaskAssignPriorityCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskAssignPriorityCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves task index, priority from parameter hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
            priority = Integer.parseInt(getHashValue(params, "p"));
            if (priority < 0) {
                throw new EzExceptions("invalidPriority");
            }
        } catch (NumberFormatException e) {
            throw new EzExceptions("indexNonInteger");
        }
    }

    /**
     * Executes command to add priority to tasks.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return priority added UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        }
        try {
            Task selectedTask = projects.get(projectIndex).getTask(taskIndex);
            selectedTask.setPriority(priority);
            return Ui.printPriorityAssignedToTaskMessage(priority, selectedTask.getTaskDescription());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
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
