package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


public class DeallocateSprintTaskCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private final ArrayList<Integer> taskIds;
    private String[] userIds;

    public DeallocateSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
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
            deallocateTask();
        } catch (DukeException e) {
            e.printExceptionMessage();
        }
    }

    /**
     * Deallocate all tasks to all users.
     */
    private void deallocateTask() {
        for (int taskId : this.taskIds) {
            for (String userId : this.userIds) {
                Member mem = this.projOwner.getProjectMember().getMember(userId.trim());
                mem.deallocateTask(taskId);
                this.projOwner.getProjectBacklog().getTask(taskId).removeFromMember(mem.getUserId());
            }
            Ui.showToUserLn(this.projOwner.getProjectBacklog().getTask(taskId).getTitle()
                    + " is removed from "
                    + Arrays.toString(this.userIds));
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
            List<String> chosenMember = Arrays.asList(this.userIds);
            if (!task.getMemberList().containsAll(chosenMember)) {
                throw new DukeException("Not all Tasks are allocated to member: " + Arrays.toString(this.userIds));
            }
        }
    }
}
