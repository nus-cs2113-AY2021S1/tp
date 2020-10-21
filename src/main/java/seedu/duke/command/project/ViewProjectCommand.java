package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class ViewProjectCommand extends ProjectCommand {

    private final ProjectList projectListManager;

    public ViewProjectCommand(Hashtable<String, String> parameters, ProjectList projectListManager) {
        super(parameters);
        this.projectListManager = projectListManager;
    }

    public void execute() {
        assert projectListManager.size() != 0 : "No projects created \n.";

        if (projectListManager.isEmpty()) {
            Ui.showError("No projects are created.");
        } else {
            Project project = projectListManager.getProject();
            assert project != null : "The project is null";
            Ui.showToUserLn(project.toString());

        }
    }
}
