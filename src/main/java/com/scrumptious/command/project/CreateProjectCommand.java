package com.scrumptious.command.project;

import com.scrumptious.exception.DukeException;
import com.scrumptious.model.project.Project;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;
import java.util.Map;

import static com.scrumptious.command.CommandSummary.TITLE;
import static com.scrumptious.command.CommandSummary.DURATION;
import static com.scrumptious.command.CommandSummary.SPRINT_DURATION;
import static com.scrumptious.command.CommandSummary.DESCRIPTION;


public class CreateProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public CreateProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    @Override
    public void execute() {

        boolean projectExists;
        String title;
        title = this.parameters.get(TITLE).trim();
        projectExists = checkTitleExist(title);

        if (projectExists) {
            handleDuplicateProject("User tried to add duplicate project.");
            return;
        }

        String description;
        description = parameters.get(DESCRIPTION).trim();

        int duration;
        duration = Integer.parseInt(parameters.get(DURATION).trim());

        int sd;
        sd = Integer.parseInt(parameters.get(SPRINT_DURATION).trim());

        try {
            checkParameter(sd, duration);
        } catch (DukeException e) {
            e.printExceptionMessage();
            ScrumLogger.LOGGER.warning(e.getMessage());
            return;
        }
        projectManager.addProject(title, description, duration, sd);
        Ui.showToUserLn("Project successfully created.");
        logExecution();
        Project addedProj = projectManager.getSelectedProject();
        printCreatedProject(addedProj);

    }

    /**
     * Displays title of added project to user.
     * @param addProj Project just added by the user.
     */
    private void printCreatedProject(Project addProj) {
        Ui.showToUserLn("\tTitle: " + addProj.getTitle());
    }

    /**
     * Checks for adding duplicate projects to the projectManager.
     * @param title Title of new project to be added
     * @return true if project alr exists, else false.
     */
    public boolean checkTitleExist(String title) {

        Project proj;
        if (projectManager.isEmpty()) {
            return false;
        }

        for (Map.Entry<Integer, Project> entry : projectManager.getProjectList().entrySet()) {
            proj = entry.getValue();
            if (proj.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if sd and duration of project specified agree with the rules.
     * @param sd Duration of each sprint of project
     * @param duration Total duration of projects
     * @throws DukeException When sd==0, or duration is not in multiples of sd
     */
    private void checkParameter(int sd, int duration) throws DukeException {
        if (sd == 0) {
            throw new DukeException("Sprint duration cannot be zero.");
        }
        if (sd > duration || (duration % sd) != 0) {
            throw new DukeException("Project duration must be in multiples of Sprint intervals.");
        }
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Project" + projectManager.getSelectedProject().getTitle() + "successfully added to "
                + "ProjectManager.");

    }
}
