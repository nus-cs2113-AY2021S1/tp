package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.EmptyCommand;
import seedu.duke.command.sprint.CreateSprintCommand;
import seedu.duke.command.sprint.AddSprintTaskCommand;
import seedu.duke.command.sprint.RemoveSprintTaskCommand;
import seedu.duke.command.sprint.ViewSprintCommand;
import seedu.duke.command.sprint.AllocateSprintTaskCommand;
import seedu.duke.command.sprint.EditSprintCommand;
import seedu.duke.command.sprint.DeallocateSprintTaskCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.task.Task;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.EDIT;
import static seedu.duke.command.CommandSummary.ADDTASK;
import static seedu.duke.command.CommandSummary.REMOVETASK;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.ALLOCATE;
import static seedu.duke.command.CommandSummary.DEALLOCATE;

public class SprintParser implements ExceptionsParser {

    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {

        if (!checkProjExist(projectListManager)) {
            throw new DukeException("Please create a project first.");
        }

        switch (action.toLowerCase()) {
        case CREATE:
            if (checkCreateSprintParams(parameters, projectListManager)) {
                return new CreateSprintCommand(parameters, projectListManager);
            }
            break;
        case EDIT:
            if (checkEditSprintParams(parameters, projectListManager)) {
                return new EditSprintCommand(parameters, projectListManager);
            }
            break;
        case ADDTASK:
            if (checkAddTaskParams(parameters, projectListManager)) {
                return new AddSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case REMOVETASK:
            if (checkRemoveTaskParams(parameters, projectListManager)) {
                return new RemoveSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case VIEW:
            if (checkViewSprintParams(parameters, projectListManager)) {
                return new ViewSprintCommand(parameters, projectListManager);
            }
            break;
        case ALLOCATE:
            if (checkAllocateTaskParams(parameters, projectListManager)) {
                return new AllocateSprintTaskCommand(parameters, projectListManager);
            }
            break;
        case DEALLOCATE:
            if (checkDeallocateTaskParams(parameters, projectListManager)) {
                return new DeallocateSprintTaskCommand(parameters, projectListManager);
            }
            break;
        default:
            throw new DukeException("Invalid action!");
        }
        return new EmptyCommand(parameters);
    }

    /**
     * Check if Project Manager contain any project.
     * @return True if ProjectManager.size > 0
     */
    private boolean checkProjExist(ProjectManager projectList) {
        return projectList.size() != 0;
    }

    /**
     * Validate parameters for CreateSprintCommand.
     */
    private boolean checkCreateSprintParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Mandatory fields.
         * - goal
         */
        if (!parameters.containsKey("goal")) {
            throw new DukeException("Please indicate your goal for this sprint.");
        }

        /**
         * Optional fields.
         * - project
         * - start
         */
        checkProjectParam(parameters, projectManager);
        if (parameters.containsKey("start")) {
            try {
                DateTimeParser.parseDate(parameters.get("start"));
            } catch (DukeException e) {
                throw new DukeException("Please indicate your start date in this following format: YYYYMMDD");
            }
        }
        return true;
    }

    /**
     * Validate parameters for EditSprintCommand.
     */
    private boolean checkEditSprintParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Mandatory fields.
         * - goal
         */
        if (!parameters.containsKey("goal")) {
            throw new DukeException("Please indicate your goal for this sprint.");
        }

        /**
         * Optional fields with tags.
         * - project
         * - sprint
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        checkSprintParam(parameters, selectedProject);
        return true;
    }

    /**
     * Validate parameters for AddSprintTaskCommand.
     */
    private boolean checkAddTaskParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Optional fields
         * - project
         * - sprint
         *
         * Mandatory fields
         * - task (with or without tag)
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        Sprint selectedSprint = new Sprint();

        if (parameters.containsKey("task")) {
            selectedSprint = checkSprintParam(parameters, selectedProject);

        } else if (parameters.containsKey("0")) {
            selectedProject.getSprintList().updateCurrentSprint();
            selectedSprint = selectedProject.getSprintList().getCurrentSprint();
        }
        checkTaskParam(parameters, selectedProject, selectedSprint, true);
        return true;
    }

    /**
     * Validate parameters for RemoveSprintTaskCommand.
     */
    private boolean checkRemoveTaskParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Optional fields.
         * - project
         * - sprint
         *
         * Mandatory fields
         * - task (with or without tag)
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        Sprint selectedSprint = new Sprint();

        if (parameters.containsKey("task")) {
            selectedSprint = checkSprintParam(parameters, selectedProject);

        } else if (parameters.containsKey("0")) {
            selectedProject.getSprintList().updateCurrentSprint();
            selectedSprint = selectedProject.getSprintList().getCurrentSprint();
        }
        checkTaskParam(parameters, selectedProject, selectedSprint, false);
        return true;
    }

    /**
     * Validate parameters for ViewSprintCommand.
     */
    private boolean checkViewSprintParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Optional fields with tags.
         * - project
         * - sprint
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        checkSprintParam(parameters, selectedProject);
        return true;
    }

    /**
     * Validate parameters for AllocateSprintTaskCommand.
     */
    private boolean checkAllocateTaskParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Mandatory fields.
         * - user
         * - task
         */
        if (!parameters.containsKey("user") || !parameters.containsKey("task")) {
            throw new DukeException("Please indicate task and user for this command.");
        }

        /**
         * Optional fields with tags.
         * - project
         * - sprint
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        Sprint selectedSprint = checkSprintParam(parameters, selectedProject);
        checkUserParam(parameters, selectedProject);
        checkTaskParam(parameters, selectedProject, selectedSprint, false);
        return true;
    }

    /**
     * Validate parameters for DeallocateSprintTaskCommand.
     */
    private boolean checkDeallocateTaskParams(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        /**
         * Mandatory fields.
         * - user
         * - task
         */
        if (!parameters.containsKey("user") || !parameters.containsKey("task")) {
            throw new DukeException("Please indicate task and user for this command.");
        }

        /**
         * Optional fields with tags.
         * - project
         * - sprint
         */
        Project selectedProject = checkProjectParam(parameters, projectManager);
        Sprint selectedSprint = checkSprintParam(parameters, selectedProject);
        checkUserParam(parameters, selectedProject);
        checkTaskParam(parameters, selectedProject, selectedSprint, false);
        checkAllocation(parameters, selectedProject);
        return true;
    }

    /**
     * Validate the "project" params.
     * Checks:
     * - parsable into an Integer
     * - Exist in Project Manager
     * - Contains sprints in selected Project (Also check when project is not specified)
     */
    private Project checkProjectParam(Hashtable<String, String> parameters, ProjectManager projectManager)
            throws DukeException {
        int selectedProg;
        if (parameters.containsKey("project")) {
            try {
                selectedProg = Integer.parseInt(parameters.get("project"));
            } catch (NumberFormatException error) {
                throw new DukeException("Please include a positive integer for Project ID.");
            }
            if (selectedProg > projectManager.size()) {
                throw new DukeException("Project not found.");
            }
        } else {
            selectedProg = projectManager.getSelectedProjectIndex();
        }
        return projectManager.getProject(selectedProg);
    }

    /**
     * Validate the "sprint" params.
     * Checks:
     * - parsable into an Integer
     * - Exist in Sprint Manager
     * Check if there is ongoing spring if "sprint" params is not specified
     */
    private Sprint checkSprintParam(Hashtable<String, String> parameters, Project proj) throws DukeException {
        if (proj.getSprintList().size() == 0) {
            throw new DukeException("Please create a sprint for Project " + proj.getProjectID() + " first.");
        }
        if (!parameters.isEmpty()) {
            throw new DukeException("Invalid action!");
        }

        int selectedSprint;
        //Specified with tag
        if (parameters.containsKey("sprint")) {
            try {
                selectedSprint = Integer.parseInt(parameters.get("sprint"));
            } catch (NumberFormatException error) {
                throw new DukeException("Please include a positive integer for Sprint ID.");
            }
            if (selectedSprint > proj.getSprintList().size()) {
                throw new DukeException("Sprint not found.");
            }
            //Specified without tag
        } else if (parameters.containsKey("0")) {
            try {
                selectedSprint = Integer.parseInt(parameters.get("0"));
            } catch (NumberFormatException error) {
                throw new DukeException("Please include a positive integer for Sprint ID.");
            }
            if (selectedSprint > proj.getSprintList().size()) {
                throw new DukeException("Sprint not found.");
            }
            //Not Specified: Check if there is ongoing sprint
        } else {
            if (proj.getSprintList().updateCurrentSprint()) {
                selectedSprint = proj.getSprintList().getCurrentSprintIndex();
            } else {
                throw new DukeException("No ongoing sprint. Maybe you can specify using -sprint tag");
            }
        }
        return proj.getSprintList().getSprint(selectedSprint);
    }

    /**
     * Check if the "task" params is specified and validate the params.
     * Checks:
     * - parsable into an Integer
     * - Exist in backlog (Task Manager)
     */
    private void checkTaskParam(Hashtable<String, String> parameters, Project proj, Sprint sprint, boolean isAdd)
            throws DukeException {

        if (parameters.containsKey("task")) {
            checkTaskParamTag(parameters, "task", proj, sprint, isAdd);
        } else if (parameters.containsKey("0")) {
            checkTaskParamTag(parameters, "0", proj, sprint, isAdd);
        } else {
            throw new DukeException("Please indicate task(s) to be allocated.");
        }
    }

    private void checkTaskParamTag(Hashtable<String, String> parameters, String tag, Project proj,
                                   Sprint sprint, boolean isAdd)
            throws DukeException {
        String[] taskIds;
        if (tag.equals("task")) {
            taskIds = parameters.get(tag).split(" ");
        } else {
            taskIds = parameters.values().toArray(new String[0]);
        }
        for (String id : taskIds) {
            int taskId;
            try {
                taskId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                throw new DukeException("Please include positive integer for task IDs.");
            }

            if (!proj.getBacklog().checkTaskExist(taskId)) {
                throw new DukeException("Task(s) not found in backlog.");
            }

            if (isAdd == sprint.checkTaskExist(taskId)) {
                throw new DukeException(isAdd
                        ? "Task(s) already exist"
                        : "Task(s) not found in sprint.");
            }

        }
    }

    /**
     * Check if all "task"  are allocated to "user".
     */
    private void checkAllocation(Hashtable<String, String> parameters, Project proj)
            throws DukeException {
        String[] taskIds = parameters.get("task").split(" ");
        String[] userIds = parameters.get("user").split(" ");

        for (String taskIdInString : taskIds) {
            int taskId = Integer.parseInt(taskIdInString);
            Task task = proj.getBacklog().getTask(taskId);
            for (String mem : task.getMemberList()) {
                for (String userId : userIds) {
                    if (mem.equals(userId)) {
                        return;
                    }
                }
            }
        }
        throw new DukeException("Task(s) not allocated to member(s)");
    }

    /**
     * Check if the "user" params is specified and validate the params.
     * Checks:
     * - Exist in MemberManager
     */
    private void checkUserParam(Hashtable<String, String> parameters, Project proj)
            throws DukeException {
        String[] userIds = parameters.get("user").split(" ");
        for (String id : userIds) {
            Member mem = proj.getProjectMember().getMember(id.trim());
            if (mem == null) {
                throw new DukeException("User(s) not found.");
            }
        }
    }
}