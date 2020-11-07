package seedu.duke.command.project;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.ui.Ui;

import java.util.Hashtable;
import java.util.Map;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DESCRIPTION;


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
            if (sd == 0) {
                throw new DukeException("Sprint duration cannot be zero.");
            }
            if (sd > duration || (duration % sd) != 0) {
                throw new DukeException("Project duration must be in multiples of Sprint intervals.");
            }
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

    private void printCreatedProject(Project addProj) {
        Ui.showToUserLn("\tTitle: " + addProj.getTitle());
    }


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
    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Project" + projectManager.getSelectedProject().getTitle() + "successfully added to "
                + "ProjectManager.");

    }
}
