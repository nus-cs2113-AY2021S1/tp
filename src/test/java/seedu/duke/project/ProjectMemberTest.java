package seedu.duke.project;

import org.junit.jupiter.api.Test;
import seedu.duke.sprint.Member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectMemberTest {
    Project project = new Project("SCRUM", "Deliver a cli for agile developers",
            "60", "30");

    @Test
    void add_addMember_noError() {
        project.getProjectMember().addMember(new Member("test user"));
        assertEquals(1, project.getProjectMember().size());
    }

    @Test
    void checkPerson_personExists_noError() {
        project.getProjectMember().addMember(new Member("test user"));
        assertTrue(project.getProjectMember().containMember(new Member("test user")));
    }

    @Test
    void checkPerson_personDoesNotExist_noError() {
        Member m = new Member("test user");
        Member m1 = new Member("Not added");
        project.getProjectMember().addMember(m);
        assertFalse(project.getProjectMember().containMember(m1));
    }
}