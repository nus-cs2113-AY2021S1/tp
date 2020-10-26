package seedu.duke.command.project;

import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.TITLE;

class CreateProjectCommandTest {

    @Test
    void addProject_validInput_noError() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(SPRINT_DURATION, "20");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        new CreateProjectCommand(parameters, projectManager).execute();
        assertFalse(projectManager.isEmpty());
    }

    @Test
    void addProject_validInput_returnTrue() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(SPRINT_DURATION, "20");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        new CreateProjectCommand(parameters, projectManager).execute();
        assertEquals(projectManager.size(), 1);
    }

    @Test
    void addProject_missingParameters_throwsNullPointer() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");

        ProjectManager projectManager = new ProjectManager();
        assertThrows(NullPointerException.class, () ->  new CreateProjectCommand(parameters, projectManager).execute());
    }

    @Test
    void addProject_invalidParameters_throwsNullPointer() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put(TITLE, "Test Project");
        parameters.put(DESCRIPTION, "To ensure testing of classes");
        parameters.put(DURATION, "60");
        parameters.put(SPRINT_DURATION, "aa");

        ProjectManager projectManager = new ProjectManager();
        assertThrows(NumberFormatException.class, () -> new CreateProjectCommand(parameters, projectManager).execute());
    }

}