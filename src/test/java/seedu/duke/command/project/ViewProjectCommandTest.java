package seedu.duke.command.project;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewProjectCommandTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        Ui.setOutStream(new PrintStream(testOut));
    }


    private ProjectManager generateProject() {
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject("project1", "project1", 50, 10);
        projectManager.addProject("project2", "project2", 50, 10);
        projectManager.addProject("project3", "project3", 50, 10);
        assert projectManager.getProjectList().size() == 3 : "Dummy projects not added!";
        return projectManager;
    }

    @Test
    void viewProject_invalidCommand_noProjectAdded() {

        ProjectManager projectManager = new ProjectManager();
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");

        ViewProjectCommand command = new ViewProjectCommand(parameters, projectManager);
        command.execute();
        String expectedOutput = "No projects are created." + System.lineSeparator();
        assertEquals(expectedOutput, getOutput());
        assertTrue(projectManager.isEmpty());
    }

    @Test
    void viewProject_validCommand() {

        ProjectManager projectManager = generateProject();
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");

        ViewProjectCommand command = new ViewProjectCommand(parameters, projectManager);
        command.execute();
        String expectedOutput = "============================ PROJECT ============================"

                + System.lineSeparator() + "[ID: 3]" +  System.lineSeparator()
                + "[Title: project3]" + System.lineSeparator()
                + "[Description: project3]" + System.lineSeparator()
                + "[No members added]" + System.lineSeparator()
                + "[Project will start along with the first sprint]" + System.lineSeparator()
                + "[Project backlog is empty]" + System.lineSeparator()
                + "[There are no Sprints]" + System.lineSeparator()
                + "=================================================================" + System.lineSeparator()
                + System.lineSeparator();
        assertEquals(expectedOutput, getOutput());
        assertFalse(projectManager.isEmpty());
    }





    @AfterEach
    public void restoreSystemOutput() throws IOException {
        testOut.close();
        Ui.setOutStream(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }
}