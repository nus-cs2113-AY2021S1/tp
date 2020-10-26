package seedu.duke.command.project;

import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

public class SelectProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public SelectProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {
        projectManager.selectProject(Integer.parseInt(parameters.get("0")));
    }

}
