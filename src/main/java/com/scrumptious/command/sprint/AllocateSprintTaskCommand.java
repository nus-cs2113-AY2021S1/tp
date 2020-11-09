package com.scrumptious.command.sprint;

import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.task.Task;
import com.scrumptious.ui.Ui;

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
        super(parameters, projectList, true);
        this.taskIds = new ArrayList<>();
        this.userIds = new String[0];
    }

    public void execute() {
        try {
            checkProjectExist(-1);
            chooseProject();
            checkSprintExist();
            chooseSprint();
            checkUserExist();
            checkTasksExist(false);
            prepareParameters();
            checkAllocation();

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            Ui.showToUser(this.sprintOwner.toIdString());
            allocateTask();
            logExecution();
        } catch (ScrumptiousException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
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
                this.projOwner.getBacklog().getTask(taskId).allocateToMember(mem.getUserId());
            }
            Ui.showToUserLn(this.projOwner.getBacklog().getTask(taskId).getTitle()
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
    private void checkAllocation() throws ScrumptiousException {
        for (int taskId : this.taskIds) {
            Task task = this.projOwner.getBacklog().getTask(taskId);
            for (String member : this.userIds) {
                if (task.getMemberList().contains(member)) {
                    throw new ScrumptiousException(member + " is already allocated with Task " + taskId);
                }
            }
        }
    }

    /**
     * Add entry to logger that execution is successful.
     */
    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info(String.format("Allocate task to user - Users: %s | Tasks: %s",
                Arrays.toString(this.userIds), Arrays.toString(this.taskIds.toArray())));
    }
}
