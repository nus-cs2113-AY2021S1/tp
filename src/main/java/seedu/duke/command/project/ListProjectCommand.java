package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;
import java.util.Map;

public class ListProjectCommand extends ProjectCommand {


    private final ProjectManager projectManager;

    public ListProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters);
        this.projectManager = projectManager;
    }

    public void execute() {
        Ui.showToUserLn("Following are the added projects: ");
        for (Map.Entry<Integer, Project> entry: projectManager.getProjectList().entrySet()) {
            Project proj = entry.getValue();
            Integer id = entry.getKey();
            String output = String.format("%d title: %s description: %s", id, proj.getTitle(), proj.getDescription());
            Ui.showToUserLn("\t" + output);
        }
    }

}
