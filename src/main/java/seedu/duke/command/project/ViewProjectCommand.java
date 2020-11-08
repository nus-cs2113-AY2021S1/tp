package seedu.duke.command.project;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ViewProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public ViewProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, false);
        this.projectManager = projectManager;
    }

    public void execute() {


        if (projectManager.size() == 0) {
            Ui.showError("No projects are created.");
            ScrumLogger.LOGGER.warning("No project added to the project manager");
        } else {
            assert projectManager.getSelectedProject() != null : "Selected project is null \n.";
            Project project = projectManager.getSelectedProject();
            assert project != null : "The project is null";
            Ui.showToUserLn(project.toString());
            logExecution();
        }
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Current working project details shown to user.");
    }

}
