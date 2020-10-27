package seedu.duke.command.storage;

import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class ClearStorageCommand extends StorageCommand {
    /**
     * Creates a command for clearing all the projects from the Project Manager.
     *
     * @param parameters Parameters provided by the user.
     * @param projectManager Project manager object of the program.
     */
    public ClearStorageCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, projectManager, true);
    }

    @Override
    public void execute() {
        Ui.showToUser("[!WARNING!] Are you sure? This command is irreversible! (y/N) ");
        String input = Ui.getUserInput();
        if (!input.trim().toLowerCase().equals("y")) {
            Ui.showToUserLn("Data clear aborted.");
            return;
        }
        projectManager.clearProjects();
        Ui.showToUserLn("All data has been cleared!");
    }
}
