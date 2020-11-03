package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class ListProjectCommand extends ProjectCommand {


    private final ProjectManager projectManager;

    public ListProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, false);
        this.projectManager = projectManager;
    }

    public void execute() {
        Ui.showToUserLn("Following are the added projects: ");
        Ui.showToUserLn("\tID Title \t\tDescription");
        TreeMap<Integer, Project> sortedProject = new TreeMap<>(projectManager.getProjectList());
        for (Map.Entry<Integer, Project> entry: sortedProject.entrySet()) {
            Project proj = entry.getValue();
            Integer id = entry.getKey();
            String output = String.format("%d) %s \t\t%s", id, proj.getTitle(), proj.getDescription());
            Ui.showToUserLn("\t" + output);
        }
    }

}
