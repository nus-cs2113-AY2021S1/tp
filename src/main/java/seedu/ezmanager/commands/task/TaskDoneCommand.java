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
 * Command that marks a task as done.
 */
public class TaskDoneCommand extends Command {

    private int projectIndex;
    private int taskIndex;
    HashMap<String, String> params;

    /**
     * Constructor for TaskDoneCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskDoneCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves task index from hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        try {
            taskIndex = Integer.parseInt(getHashValue(params, "t")) - 1;
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTaskID");
        }

    }

    /**
     * Executes command to mark task as done within project.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return task done UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Project project = projects.get(projectIndex);
        try {
            Task selectedTask = project.getTask(taskIndex);
            selectedTask.markAsDone();
            return Ui.printTaskDoneMessage(selectedTask.toString());
        }  catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
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
