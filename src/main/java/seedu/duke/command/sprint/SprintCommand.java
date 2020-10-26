package seedu.duke.command.sprint;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.sprint.SprintManager;

import java.util.Hashtable;

public abstract class SprintCommand extends Command {
    protected SprintManager sprintList;
    protected ProjectManager projectList;
    protected Project projOwner;
    protected Sprint sprintOwner;

    /**
     * Creates a new Sprint command with arguments.
     */
    public SprintCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters);
        this.projectList = projectList;
    }

    public abstract void execute();

    /**
     * Choose the project to execute command.
     * Validation completed at SprintParser
     */
    protected void chooseProject() {
        int selectedProg;
        if (parameters.containsKey("project")) {
            //select specified project
            selectedProg = Integer.parseInt(parameters.get("project"));
        } else {
            //select default project
            selectedProg = projectList.getSelectedProjectIndex();
        }
        this.projOwner = projectList.getProject(selectedProg);
        this.sprintList = projOwner.getSprintList();
    }

    /**
     * Choose the sprint to execute command.
     * Validation completed at SprintParser
     */
    protected void chooseSprint() {
        int selectedSprint;
        //select specified sprint
        if (parameters.containsKey("sprint")) {
            selectedSprint = Integer.parseInt(parameters.get("sprint"));
        } else if (parameters.containsKey("0")) {
            selectedSprint = Integer.parseInt(parameters.get("0"));
            //select ongoing sprint
        } else {
            sprintList.updateCurrentSprint();
            selectedSprint = sprintList.getCurrentSprintIndex();
        }
        this.sprintOwner = projOwner.getSprintList().getSprint(selectedSprint);
    }

    /**
     * Choose the sprint to execute command.
     * Validation completed at SprintParser
     */
    protected void selectCurrentSprint() {
        sprintList.updateCurrentSprint();
        int selectedSprint = sprintList.getCurrentSprintIndex();
        this.sprintOwner = projOwner.getSprintList().getSprint(selectedSprint);
    }
}
