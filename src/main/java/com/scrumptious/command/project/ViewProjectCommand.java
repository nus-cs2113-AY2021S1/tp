package com.scrumptious.command.project;

import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

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
