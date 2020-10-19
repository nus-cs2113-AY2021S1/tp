package seedu.duke.command.sprint;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.Parser;
import seedu.duke.project.Project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddSprintTaskCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final ArrayList<Project> projectList = new ArrayList<>();
    private final Parser parser = new Parser();

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    public void setUpProject() {
        Project proj = new Project(null, null, "90", "10");
        projectList.add(proj);
        proj.getAllSprints().addSprint(proj, null, LocalDate.now(), LocalDate.now().plusDays(10));
        proj.getProjectBacklog().addTask("faketask", null, "HIGH");
    }

    @Test
    void addSprintTask_missingFields_returnErrorMessage() {
        setUpStreams();
        setUpProject();
        String command = "sprint /addtask";
        parser.parser(command, projectList);
        String expectedOutput = "Exception found: please give a task number" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        restoreStreams();
    }
}