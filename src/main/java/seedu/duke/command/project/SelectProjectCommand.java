package seedu.duke.command.project;

import seedu.duke.model.project.ProjectList;

import java.util.Hashtable;

public class SelectProjectCommand extends ProjectCommand {

    private final ProjectList projectListManager;

    public SelectProjectCommand(Hashtable<String, String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        projectListManager.selectProject(Integer.parseInt(parameters.get("0")));
    }

}
