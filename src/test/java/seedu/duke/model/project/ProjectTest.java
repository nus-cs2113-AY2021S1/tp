package seedu.duke.model.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTest {


    @Test
    void getProjectSprint_emptyArrayList_returnsIndexOutOfBoundsException() {
        Project project = new Project(1, "SCRUM", "Deliver a cli for agile developers",
                60, 30);
        assertThrows(IndexOutOfBoundsException.class, () -> project.getSprintList().getSprint(2));
    }

    @Test
    void getProjectMember_emptyArrayList_returnsIndexOutOfBoundsException() {
        Project project = new Project(2,"SCRUM", "Deliver a cli for agile developers",
                60, 30);
        assertThrows(IndexOutOfBoundsException.class, () -> project.getSprintList().getSprint(2));
    }

}