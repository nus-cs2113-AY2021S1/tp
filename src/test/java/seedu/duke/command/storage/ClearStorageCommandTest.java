package seedu.duke.command.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClearStorageCommandTest {
    /*private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }


    @Test
    void execute() {
        ProjectManager projectManager = generateProjectManager();
        assert projectManager.size() > 0 : "Project Manager is empty!";
        ClearStorageCommand csc = new ClearStorageCommand(null, projectManager);

        setUpOutput();
        String input = "y" + System.lineSeparator();
        provideInput(input);

        csc.execute();
        String expected = "[!WARNING!] Are you sure? This command is irreversible! (y/N) All data has been cleared!"
                + System.lineSeparator();
        assertEquals(expected, getOutput());
    }
    
    *//*@Test
    void execute_emptyInput() {
        String input = "N"+System.lineSeparator();
        String expected = "[!WARNING!] Are you sure? This command is irreversible! (y/N) Data clear aborted." 
                + System.lineSeparator();
        ProjectManager projectManager = generateProjectManager();
        assert projectManager.size() > 0 : "Project Manager is empty!";
        ClearStorageCommand csc = new ClearStorageCommand(null, projectManager);

        setUpOutput();
        provideInput(input);

        csc.execute();
        assertEquals(expected, getOutput());
    }*//*
    
    @AfterEach
    public void restoreSystemInputOutput() throws IOException {
        testIn.close();
        *//*System.out.flush();
        testOut.close();*//*
        System.in.reset();
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
    
    private ProjectManager generateProjectManager() {
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject("Test", "test", 50, 10);
        projectManager.addProject("Test2", "test2", 50, 10);
        return projectManager;
    }*/
}