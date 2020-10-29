package seedu.duke.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;

public class HrTest {

    @Test
    public void addListDelMember_executesNormally() {
        String expected1 = "Got it. I've added this member:\n"
                + "name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member\n"
                + "Now you have 1 member in the list.\n";

        String actual1 = MemberList.addToList(new Member("James Gosling", 11111111, "111111@gmail.com", "member"));
        assertEquals(expected1, actual1);

        String expected2 = "Here is the list of members in your CCA:\n"
                + "1.name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member\n";
        String actual2 = MemberList.listMembers();
        assertEquals(expected2, actual2);

        String expected3 = "Noted. I'll remove this member:\n"
                + "name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member\n"
                + "Now you have 0 member in the list.\n";
        String actual3 = MemberList.deleteFromList(0);
        assertEquals(expected3, actual3);
    }

    @Test
    public void removeMember_MemberNotExists() {
        String expected = "OOPS!!! The member does not exist.\n";
        String actual = MemberList.deleteFromList(0);
        assertEquals(expected, actual);
    }

    @Test
    public void listMember_MemberListEmpty() {
        String expected = "OOPS!!! The member list is empty!\n";
        String actual = MemberList.listMembers();
        assertEquals(expected, actual);
    }
}
