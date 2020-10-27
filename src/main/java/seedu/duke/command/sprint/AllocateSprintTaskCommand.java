package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class AllocateSprintTaskCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private final ArrayList<Integer> taskIds;
    private String[] userIds;

    public AllocateSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList);
        this.taskIds = new ArrayList<>();
        this.userIds = new String[0];
    }

    public void execute() {
        try {
            checkProjectExist();
            chooseProject();
            checkSprintExist();
            chooseSprint();
            checkUserExist();
            checkTasksExist(false);
            prepareParameters();
            checkAllocation();

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            Ui.showToUserLn(this.sprintOwner.toIdString());
            allocateTask();
        } catch (DukeException e) {
            e.printExceptionMessage();
        }
    }

    /**
     * Allocate all tasks to all users.
     */
    private void allocateTask() {
        for (int taskId : taskIds) {
            for (String userId : userIds) {
                Member mem = this.projOwner.getProjectMember().getMember(userId.trim());
                mem.allocateTask(taskId);
                this.projOwner.getProjectBacklog().getTask(taskId).allocateToMember(mem.getUserId());
            }
            Ui.showToUserLn(this.projOwner.getProjectBacklog().getTask(taskId).getTitle()
                    + " is assigned to "
                    + Arrays.toString(userIds));
        }
    }

    /**
     * Prepare the parameters.
     */
    private void prepareParameters() {
        this.userIds = this.parameters.get("user").split(" ");
        parseParamsToInt(this.parameters.get("task").split(" "));
    }

    /**
     * Parse parameters into Integers.
     */
    private void parseParamsToInt(String[] taskIds) {
        for (String id : taskIds) {
            this.taskIds.add(Integer.parseInt(id));
        }
    }

    /**
     * Check if all tasks are allocated to all users.
     */
    private void checkAllocation() throws DukeException {
        for (int taskId : this.taskIds) {
            Task task = this.projOwner.getBacklog().getTask(taskId);
            for (String member : this.userIds) {
                if (task.getAllocatedMembers().contains(member)) {
                    throw new DukeException(member + " is already allocated with Task " + taskId);
                }
            }
        }
    }
}
