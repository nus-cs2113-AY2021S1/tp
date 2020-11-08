package seedu.duke.command.project;

import seedu.duke.logger.ScrumLogger;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class SelectProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public SelectProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {
        int id = Integer.parseInt(parameters.get("0"));
        assert projectManager.getProject(id) != null : "Project exists in list but null";
        projectManager.selectProject(id);
        Ui.showToUserLn("Project " + parameters.get("0") + " has been selected.");
        logExecution();
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Project" + projectManager.getSelectedProject().getTitle() + "selected by user.");
    }


}
