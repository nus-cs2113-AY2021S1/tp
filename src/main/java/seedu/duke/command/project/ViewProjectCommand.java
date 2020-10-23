package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class ViewProjectCommand extends ProjectCommand {

    private final ProjectList projectManager;

    public ViewProjectCommand(Hashtable<String, String> parameters, ProjectList projectManager) {
        super(parameters);
        this.projectManager = projectManager;
    }

    public void execute() {
        assert projectManager.size() != 0 : "No projects created \n.";

        if (projectManager.isEmpty()) {
            Ui.showError("No projects are created.");
        } else {
            Project project = projectManager.getProject();
            assert project != null : "The project is null";
            Ui.showToUserLn(project.toString());

        }
    }
}
