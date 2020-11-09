package com.scrumptious.command.sprint;

import com.scrumptious.Scrumptious;
import com.scrumptious.command.Command;
import com.scrumptious.exception.ScrumptiousException;
import com.scrumptious.model.member.Member;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.model.sprint.Sprint;
import com.scrumptious.model.sprint.SprintManager;

import java.time.LocalDateTime;
import java.util.Hashtable;

public abstract class SprintCommand extends Command {
    protected SprintManager sprintList;
    protected ProjectManager projectList;
    protected Project projOwner;
    protected Sprint sprintOwner;

    /**
     * Creates a new Sprint command with arguments.
     */
    public SprintCommand(Hashtable<String, String> parameters, ProjectManager projectList, boolean shouldSave) {
        super(parameters, shouldSave);
        this.projectList = projectList;
    }

    public abstract void execute();

    public abstract void logExecution();

    /**
     * Choose the project to execute command.
     * Validation completed at SprintParser
     */
    protected void chooseProject() throws ScrumptiousException {
        int selectedProg;
        if (parameters.containsKey("project")) {
            //select specified project
            selectedProg = Integer.parseInt(parameters.get("project"));
            checkProjectExist(selectedProg);
        } else {
            //select default project
            selectedProg = projectList.getSelectedProjectIndex();
        }
        this.projOwner = projectList.getProject(selectedProg);
        this.sprintList = projOwner.getSprintList();
    }

    /**
     * Choose the sprint to execute command.
     * Validate the sprint parameter if specified:
     * - If Exist in Sprint Manager
     * - If there is ongoing spring if "sprint" params is not specified
     */
    protected void chooseSprint() throws ScrumptiousException {
        int selectedSprint;
        if (this.parameters.containsKey("sprint")) {
            selectedSprint = Integer.parseInt(this.parameters.get("sprint"));
            if (selectedSprint < 1 || selectedSprint > this.projOwner.getSprintList().size()) {
                throw new ScrumptiousException("Sprint not found: " + selectedSprint);
            }
        } else if (this.parameters.containsKey("0")) {
            selectedSprint = Integer.parseInt(this.parameters.get("0"));
            if (selectedSprint < 1 || selectedSprint > this.projOwner.getSprintList().size()) {
                throw new ScrumptiousException("Sprint not found: " + selectedSprint);
            }
        } else {
            if (this.projOwner.getSprintList().updateCurrentSprint()) {
                selectedSprint = this.projOwner.getSprintList().getCurrentSprintIndex();
            } else {
                throw new ScrumptiousException("No ongoing sprint today ("  
                        + LocalDateTime.now(Scrumptious.getClock()).toLocalDate()
                        + "). Maybe you can specify using -sprint tag");
            }
        }
        this.sprintOwner = this.projOwner.getSprintList().getSprint(selectedSprint);

    }

    /**
     * Choose the sprint to execute command.
     * Validation completed at SprintParser
     */
    protected void selectCurrentSprint() throws ScrumptiousException {
        if (this.sprintList.updateCurrentSprint()) {
            int selectedSprint = this.sprintList.getCurrentSprintIndex();
            this.sprintOwner = this.projOwner.getSprintList().getSprint(selectedSprint);
        } else {
            throw new ScrumptiousException("No ongoing sprint. Maybe you can specify using -sprint tag");
        }
    }

    /**
     * Check if Project Manager contain any project.
     */
    protected void checkProjectExist(int projId) throws ScrumptiousException {
        if (projId != -1) {
            if (!this.projectList.checkExist(projId)) {
                throw new ScrumptiousException("Project not found: " + projId);
            }
        } else {
            if (this.projectList.size() == 0) {
                throw new ScrumptiousException("Please create a project first.");
            }
        }
    }

    /**
     * Check if Sprint Manager contain any sprints.
     */
    protected void checkSprintExist() throws ScrumptiousException {
        if (this.projOwner.getSprintList().size() == 0) {
            throw new ScrumptiousException("Please create a sprint for Project " + this.projOwner.getProjectID() + " first.");
        }
    }


    /**
     * Check if the "task" params is specified and validate the params.
     * Checks:
     * - parsable into an Integer
     * - Exist in backlog (Task Manager)
     */
    protected void checkTasksExist(boolean isAdd)
            throws ScrumptiousException {

        if (this.parameters.containsKey("task")) {
            checkTasks("task", isAdd);
        } else if (this.parameters.containsKey("0")) {
            checkTasks("0", isAdd);
        } else {
            throw new ScrumptiousException("Missing parameter(s): Task ID");
        }
    }

    protected void checkTasks(String tag, boolean isAdd)
            throws ScrumptiousException {
        String[] taskIds;
        if (tag.equals("task")) {
            taskIds = this.parameters.get(tag).split(" ");
        } else {
            taskIds = this.parameters.values().toArray(new String[0]);
        }
        for (String id : taskIds) {
            int taskId = Integer.parseInt(id);

            if (!this.projOwner.getBacklog().checkTaskExist(taskId)) {
                throw new ScrumptiousException("Task not found in backlog: " + taskId);
            }

            if (isAdd == this.sprintOwner.checkTaskExist(taskId)) {
                throw new ScrumptiousException(isAdd
                        ? "Task(s) already exist: " + taskId
                        : "Task not found in sprint: " + taskId);
            }

        }
    }

    /**
     * Check if the tag is specified.
     */
    protected boolean checkTagSpecified(String tag) {
        return parameters.containsKey(tag);
    }

    /**
     * Check if the "user" params is specified and validate the params.
     * Checks:
     * - Exist in MemberManager
     */
    protected void checkUserExist() throws ScrumptiousException {
        String[] userIds = this.parameters.get("user").split(" ");
        for (String id : userIds) {
            Member mem = this.projOwner.getProjectMember().getMember(id.trim());
            if (mem == null) {
                throw new ScrumptiousException("User not found: " + id.trim());
            }
        }
    }
}
