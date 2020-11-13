package seedu.ezmanager.commands.task;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;

import java.util.ArrayList;
import java.util.HashMap;

import seedu.ezmanager.ui.Ui;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Command that deletes a task within project.
 */
public class TaskDeleteCommand extends Command {

    private int taskIndex;
    private int projectIndex;
    HashMap<String, String> params;

    /**
     * Constructor for TaskDeleteCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskDeleteCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
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
     * Executes command to delete task within project.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return task deleted UI message.
     * @throws EzExceptions EzException
     */
    @Override
    public String executeCommand(ArrayList<Project> projects,
                                 ArrayList<TeamMember> teamMembers) throws EzExceptions {
        if (projects.size() == 0) {
            throw new EzExceptions("emptyProjectList");
        }
        try {
            Project selectedProject = projects.get(projectIndex);
            //Get task before deletion
            String taskToBeDeleted = selectedProject.getTask(taskIndex).getTaskDescription();
            selectedProject.deleteTask(taskIndex);
            return Ui.printTaskDeletedMessage(taskToBeDeleted);
        } catch (IndexOutOfBoundsException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    /**
     * Checks if command will exit program.
     * @return isExit status.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
