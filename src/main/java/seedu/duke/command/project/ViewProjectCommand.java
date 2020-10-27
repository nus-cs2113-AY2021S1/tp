package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public ViewProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters);
        this.projectManager = projectManager;
    }

    public void execute() {
        assert projectManager.size() != 0 : "No projects created \n.";

        if (projectManager.size() == 0) {
            Ui.showError("No projects are created.");
        } else {
            Project project = projectManager.getSelectedProject();
            assert project != null : "The project is null";
            Ui.showToUserLn(project.toString());

        }
    }
}
