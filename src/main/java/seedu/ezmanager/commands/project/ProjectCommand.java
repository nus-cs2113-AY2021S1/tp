package seedu.ezmanager.commands.project;

import seedu.ezmanager.EzExceptions;
import seedu.ezmanager.EzLogger;
import seedu.ezmanager.commands.Command;
import seedu.ezmanager.member.TeamMember;
import seedu.ezmanager.project.Project;
import seedu.ezmanager.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import static seedu.ezmanager.Parser.getHashValue;

/**
 * Add a new Project object to the program.
 */
public class ProjectCommand extends Command {

    private String description;
    HashMap<String, String> params;

    public ProjectCommand(HashMap<String, String> params) throws EzExceptions {
        this.params = params;
        this.parse();
    }

    public void parse() throws EzExceptions {
        this.description = getHashValue(params, "n");
    }

    /**
     * Adds a new Project object into the ArrayList of Projects.
     * Prints project created message.
     *
     * @param projects ArrayList of Projects.
     * @param teamMembers ArrayList of TeamMembers in the program.
     * @return Prints project created message.
     */
    public String executeCommand(ArrayList<Project> projects, ArrayList<TeamMember> teamMembers) {
        assert description != null : "description should not be null";
        Project newProject = new Project(description);
        projects.add(newProject);
        EzLogger.log(Level.INFO, "New Project added");
        return Ui.printProjectCreatedMessage(newProject.getProjectName());
    }

    public Boolean isExit() {
        return false;
    }
}
