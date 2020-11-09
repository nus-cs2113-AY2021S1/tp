package com.scrumptious.command.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.scrumptious.model.project.ProjectManager;
import com.scrumptious.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddMemberCommandTest {

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

    private Hashtable<String, String> generateMembers() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "member1");
        parameters.put("1", "member2");
        return parameters;
    }

    @Test
    void addMember_validCommand() {
        Hashtable<String, String> parameters = generateMembers();
        ProjectManager projectManager = generateProject();

        AddMemberCommand command = new AddMemberCommand(parameters, projectManager);
        command.execute();

        String expectedOutput = "member1 has been added to the project."
                + System.lineSeparator() + "member2 has been added to the project."
                + System.lineSeparator();
        assertTrue(projectManager.getSelectedProject().getMemberList().size() == 2);
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    void addMember_memberAlreadyAdded() {
        Hashtable<String, String> parameters = generateMembers();
        ProjectManager projectManager = generateProject();
        parameters.put("2", "member1");
        AddMemberCommand command = new AddMemberCommand(parameters, projectManager);
        command.execute();

        String expectedOutput = "member1 has been added to the project."
                + System.lineSeparator() + "member2 has been added to the project."
                + System.lineSeparator() + "member1 is already associated to the project."
                + System.lineSeparator();
        assertTrue(projectManager.getSelectedProject().getMemberList().size() == 2);
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
