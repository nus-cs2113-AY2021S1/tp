package seedu.duke.command.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.model.member.Member;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.io.*;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteMemberCommandTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        Ui.setOutStream(new PrintStream(testOut));
    }


    private ProjectManager generateProjectWithMembers() {
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject("project3", "project3", 50, 10);
        projectManager.addProject("project1", "project1", 50, 10);
        assert projectManager.getProjectList().size() == 2 : "Dummy projects not added!";
        projectManager.getSelectedProject().getMemberList().addMember(new Member("member1"));
        projectManager.getSelectedProject().getMemberList().addMember(new Member("member2"));
        projectManager.getSelectedProject().getMemberList().addMember(new Member("member3"));
        return projectManager;
    }

    private Hashtable<String, String> generateMembers() {
        Hashtable<String, String> parameters = new Hashtable<>();
        parameters.put("0", "member1");
        parameters.put("1", "member2");
        return parameters;
    }

    @Test
    void deleteMember_memberPresent() {
        Hashtable<String, String> parameters = generateMembers();
        ProjectManager projectManager = generateProjectWithMembers();

        assertEquals(projectManager.getSelectedProject().getMemberList().size(), 3);

        DeleteMemberCommand command = new DeleteMemberCommand(parameters, projectManager);
        command.execute();
        String expectedOutput = "member1 has been removed from the project."
                + System.lineSeparator() + "member2 has been removed from the project."
                + System.lineSeparator();
        assertEquals(projectManager.getSelectedProject().getMemberList().size(), 1);
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    void deleteMember_memberNotPresent() {
        Hashtable<String, String> parameters = generateMembers();
        ProjectManager projectManager = generateProjectWithMembers();

        assertEquals(projectManager.getSelectedProject().getMemberList().size(), 3);

        parameters.put("2", "member5");
        DeleteMemberCommand command = new DeleteMemberCommand(parameters, projectManager);
        command.execute();

        String expectedOutput = "member1 has been removed from the project."
                + System.lineSeparator() + "member2 has been removed from the project."
                + System.lineSeparator() + "member5 is not associated with the project."
                + System.lineSeparator();
        assertEquals(projectManager.getSelectedProject().getMemberList().size(), 1);
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
