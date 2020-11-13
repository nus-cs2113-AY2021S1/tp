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

import static seedu.ezmanager.Util.TASK_NAME_KEY;
import static seedu.ezmanager.Parser.getHashValue;

/**
 * Command that creates a new task within project.
 */
public class TaskCommand extends Command {

    private int projectIndex;
    private String name;
    HashMap<String, String> params;

    /**
     * Constructor for TaskCommand. Calls parse() method.
     * @param params Hashmap of parameters the command requires.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskCommand(HashMap<String, String> params, int projectIndex) throws EzExceptions {
        assert projectIndex >= 0 : "projectIndex must be positive integer!";
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves task name from hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        this.name = getHashValue(params, TASK_NAME_KEY);
    }

    /**
     * Executes command to add task to project.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return task added UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        EzLogger.log(Level.INFO, "Executing Command");
        Project project = projects.get(projectIndex);
        EzLogger.log(Level.INFO, "Project Retrieved");
        Task newTask = new Task(name);
        EzLogger.log(Level.INFO, "Task Created");
        project.addTask(newTask);
        EzLogger.log(Level.INFO, "Task Added");
        return Ui.printTaskCreatedMessage(newTask.toString());
    }

    /**
     * Checks if command will exit program.
     * @return isExit status.
     */
    public Boolean isExit() {
        return false;
    }
}
