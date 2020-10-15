
package seedu.duke.project;

import org.junit.jupiter.api.Test;
import seedu.duke.sprint.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTest {

    @Test
    void createProject_invalidDuration_returnsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> new Project("SCRUMPptious", "To deliver a cli interface for agile",
                "512a", "30"));
    }

    @Test
    void createProject_invalidSprintLength_returnsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> new Project("SCRUMPptious", "To deliver a cli interface for agile",
                "90", "4a"));
    }

    @Test
    void getProjectSprint_emptyArrayList_returnsIndexOutOfBoundsException() {
        Project project = new Project("SCRUM", "Deliver a cli for agile developers",
                "60", "30");
        assertThrows(IndexOutOfBoundsException.class, () -> project.getAllSprints().getSprint(2));
    }

    @Test
    void getProjectMember_emptyArrayList_returnsIndexOutOfBoundsException() {
        Project project = new Project("SCRUM", "Deliver a cli for agile developers",
                "60", "30");
        assertThrows(IndexOutOfBoundsException.class, () -> project.getAllSprints().getSprint(2));
    }



}