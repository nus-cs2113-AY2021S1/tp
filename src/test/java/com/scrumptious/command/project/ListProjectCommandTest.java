package com.scrumptious.command.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListProjectCommandTest {

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
        projectManager.addProject("project3", "project3", 50, 10);
        projectManager.addProject("project1", "project1", 50, 10);
        assert projectManager.getProjectList().size() == 2 : "Dummy projects not added!";
        return projectManager;
    }

    @Test
    void listProject_noError() {

        Hashtable<String, String> parameters = new Hashtable<>();
        ProjectManager projectManager = generateProject();
        parameters.put("0", "1");

        ListProjectCommand command = new ListProjectCommand(parameters, projectManager);
        command.execute();
        assertTrue(projectManager.size() == 2);
        String expectedOutput = "Following are the added projects: " + System.lineSeparator()
                + "\tID Title \t\tDescription" + System.lineSeparator()
                + "\t1) project3 \t\tproject3" + System.lineSeparator()
                + "\t2) project1 \t\tproject1" + System.lineSeparator();

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
