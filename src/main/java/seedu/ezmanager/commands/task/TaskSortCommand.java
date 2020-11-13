package seedu.ezmanager.commands.task;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Sorts the tasks within a project by priority, actual time, estimated time or deadline.
 */
public class TaskSortCommand extends Command {
    private int projectIndex;
    HashMap<String, String> params;
    private String sortingType;

    /**
     * Constructor for TaskSortCommand. Calls parse() method.
     * @param projectIndex Integer pointer to currently selected project
     * @throws EzExceptions EzException
     */
    public TaskSortCommand(HashMap<String, String> params, int projectIndex)
            throws EzExceptions {
        this.params = params;
        this.projectIndex = projectIndex;
        this.parse();
    }

    /**
     * Retrieves sorting type from hashmap passed to it from constructor.
     * @throws EzExceptions EzException
     */
    public void parse() throws EzExceptions {
        try {
            sortingType = getHashValue(params, "s");
        } catch (NumberFormatException e) {
            throw new EzExceptions("invalidTaskID");
        }
    }

    /**
     * Executes command to sort tasks.
     * @param projects list of all projects in program
     * @param teamMembers list of all members in program
     * @return task sorted UI message.
     * @throws EzExceptions EzException
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) throws EzExceptions {
        Project project = projects.get(projectIndex);
        switch (sortingType) {
        case "p":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getPriority() == 0) {
                        return 1;
                    }

                    if (o2.getPriority() == 0) {
                        return -1;
                    }

                    return o1.getPriority() - o2.getPriority(); //sort based on priority
                }
            });
            System.out.println("Task List sorted based on priority");

            break;
        case "d":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {

                        if (o1.getDeadline() == null) {
                            return 1;
                        }

                        if (o2.getDeadline() == null) {
                            return -1;
                        }
                        return o1.getDeadline().compareTo(o2.getDeadline());

                    }
                });
            System.out.println("Task List sorted based on deadline");
            break;
        case "a":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getActual() == 0) {
                        return 1;
                    }

                    if (o2.getActual() == 0) {
                        return -1;
                    }
                    return o1.getActual() - o2.getActual(); //sort based on actual time
                }
            });
            System.out.println("Task List sorted based on actual time taken");
            break;
        case "e":
            Collections.sort(project.getTaskList(), new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    if (o1.getEstimate() == 0) {
                        return 1;
                    }

                    if (o2.getEstimate() == 0) {
                        return -1;
                    }
                    return o1.getEstimate() - o2.getEstimate(); //sort based on actual time
                }
            });
            System.out.println("Task List sorted based on estimated time taken");
            break;
        default: throw new EzExceptions("InvalidSortingParameter");
        }

        return "";
    }

    /**
     * Checks if command will exit program.
     * @return isExit status.
     */
    public Boolean isExit() {
        return false;
    }

}
