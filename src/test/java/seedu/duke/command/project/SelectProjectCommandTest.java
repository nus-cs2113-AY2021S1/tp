package seedu.duke.command.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectProjectCommandTest {

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
    void selectProject_validParameters_noError() {

        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "1");

        ProjectManager projectManager;
        projectManager = generateProject();

        SelectProjectCommand command = new SelectProjectCommand(parameters, projectManager);
        command.execute();
        String expectedOutput = "Project 1 has been selected." + System.lineSeparator();
        assertEquals(expectedOutput, getOutput());
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