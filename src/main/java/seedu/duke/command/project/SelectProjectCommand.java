package seedu.duke.command.project;

import seedu.duke.model.project.ProjectList;

import java.util.Hashtable;

public class SelectProjectCommand extends ProjectCommand {

    private final ProjectList projectManager;

    public SelectProjectCommand(Hashtable<String, String> parameters, ProjectList projectManager) {
        super(parameters);
        this.projectManager = projectManager;
    }

    public void execute() {
        projectManager.selectProject(Integer.parseInt(parameters.get("0")));
    }

}
