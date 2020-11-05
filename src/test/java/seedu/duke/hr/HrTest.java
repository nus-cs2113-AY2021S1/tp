package seedu.duke.hr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.hr.Member;
import seedu.duke.hr.MemberList;

public class HrTest {

    Member member1 = new Member("James Gosling", 11111111,
            "111111@gmail.com", "member");
    Member member2 = new Member("Harry Potter", 1234567890,
            "harry_potter@gmail.com", "president");
    @Test
    public void addListDelChangeMember_executesNormally() {
        Member.numOfMembers = 0;
        MemberList.members.clear();
        //test addToList: entry in title case
        String expected1 = "Got it. I've added this member:\n"
                + "name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member |attendance rate: 0%\n"
                + "Now you have 1 member in the list.\n";

        String actual1 = MemberList.addToList(new Member("James Gosling", 11111111,
                "111111@gmail.com", "member"));
        assertEquals(expected1, actual1);

        //test listMember
        String expected2 = "Here is the list of members in your CCA:\n"
                + "1.name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member "
                + "|attendance rate: 0%\n";
        String actual2 = MemberList.listMembers();
        assertEquals(expected2, actual2);

        //test deleteFromList
        String expected3 = "Noted. I'll remove this member:\n"
                + "name: James Gosling |phone: 11111111 |email: 111111@gmail.com |role: member |attendance rate: 0%\n"
                + "Now you have 0 member in the list.\n";
        String actual3 = MemberList.deleteFromList(0);
        assertEquals(expected3, actual3);

        //add more members to test further: entry in lower case
        String expected4 = "Got it. I've added this member:\n"
                + "name: Harry Potter |phone: 1234567890 |email: harry_potter@gmail.com |role: president "
                + "|attendance rate: 0%\n"
                + "Now you have 1 member in the list.\n";

        String actual4 = MemberList.addToList(new Member("Harry Potter", 1234567890,
                "harry_potter@gmail.com", "president"));
        assertEquals(expected4, actual4);

        //test changeMemberInfo, only change phone number
        String expected5 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 88888888 |email: harry_potter@gmail.com |role: president "
                + "|attendance rate: 0%";
        Member m = MemberList.findMemberByName("harry potter");
        String actual5 = MemberList.changeMemberInfo(m, "88888888", null, null);
        assertEquals(expected5, actual5);

        //test changeMemberInfo, only change email
        String expected6 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 88888888 |email: magic@gmail.com |role: president "
                + "|attendance rate: 0%";
        String actual6 = MemberList.changeMemberInfo(m, null, "magic@gmail.com", null);
        assertEquals(expected6, actual6);

        //test changeMemberInfo, only change role
        String expected7 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 88888888 |email: magic@gmail.com |role: member "
                + "|attendance rate: 0%";
        String actual7 = MemberList.changeMemberInfo(m, null, null, "member");
        assertEquals(expected7, actual7);

        //test changeMemberInfo, change phone number and role simultaneously
        String expected8 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 1234567890 |email: magic@gmail.com |role: president "
                + "|attendance rate: 0%";
        String actual8 = MemberList.changeMemberInfo(m, "1234567890", null, "president");
        assertEquals(expected8, actual8);

        //test changeMemberInfo, change phone number and email simultaneously
        String expected9 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 88888888 |email: harry_potter@gmail.com |role: president "
                + "|attendance rate: 0%";
        String actual9 = MemberList.changeMemberInfo(m, "88888888", "harry_potter@gmail.com", null);
        assertEquals(expected9, actual9);

        //test changeMemberInfo, change email and role simultaneously
        String expected10 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 88888888 |email: snape@gmail.com |role: professor "
                + "|attendance rate: 0%";
        String actual10 = MemberList.changeMemberInfo(m, null, "snape@gmail.com", "professor");
        assertEquals(expected10, actual10);

        //test changeMemberInfo, change phone number, email and role simultaneously
        String expected11 = "I have changed the information of this member:\n"
                + "name: Harry Potter |phone: 1234567890 |email: harry_potter@gmail.com |role: president "
                + "|attendance rate: 0%";
        String actual11 = MemberList.changeMemberInfo(m, "1234567890", "harry_potter@gmail.com", "president");
        assertEquals(expected11, actual11);

        MemberList.deleteFromList(0);
    }

    @Test
    public void changeMemberInfo_PhoneNumberWrongFormat() {
        MemberList.addToList(member2);
        String expected1 = "OOPS!!! The format of the phone number given is incorrect.\n"
                + "The phone number should be a whole number less than 19 digits.\n";
        Member m = MemberList.findMemberByName("harry potter");
        String actual1 = MemberList.changeMemberInfo(m, "9999999999999999999", null, null);
        assertEquals(expected1, actual1);

        String expected2 = "OOPS!!! The format of the phone number given is incorrect.\n"
                + "The phone number should be a whole number less than 19 digits.\n";
        String actual2 = MemberList.changeMemberInfo(m, "string", null, null);
        assertEquals(expected2, actual2);

        String expected3 = "OOPS!!! The phone number should be a positive number.";
        String actual3 = MemberList.changeMemberInfo(m, "-111111", null, null);
        assertEquals(expected3, actual3);
        MemberList.deleteFromList(0);
    }

    @Test
    public void removeMember_MemberNotExists() {
        String expected = "OOPS!!! The member does not exist.\n";
        String actual = MemberList.deleteFromList(0);
        assertEquals(expected, actual);
    }

    @Test
    public void listMember_MemberListEmpty() {
        Member.numOfMembers = 0;
        String expected = "OOPS!!! The member list is empty!\n";
        String actual = MemberList.listMembers();
        assertEquals(expected, actual);
    }

    @Test
    public void checkMemberExistence_executesNormally() {
        MemberList.addToList(member2);
        boolean hasExist1 = MemberList.checkMemberExistence(MemberList.members, "Harry Potter");
        String actual1 = String.valueOf(hasExist1);
        String expected1 = "true";
        assertEquals(expected1, actual1);

        boolean hasExist2 = MemberList.checkMemberExistence(MemberList.members, "Draco Malfoy");
        String actual2 = String.valueOf(hasExist2);
        String expected2 = "false";
        assertEquals(expected2, actual2);
        MemberList.deleteFromList(0);
    }

    @Test
    public void standardizeMemberName_executesNormally() {
        String actual1 = MemberList.standardizeMemberName("HArrY POTTER");
        String expected1 = "Harry Potter";
        assertEquals(expected1, actual1);

        String actual2 = MemberList.standardizeMemberName("draco malfoy");
        String expected2 = "Draco Malfoy";
        assertEquals(expected2, actual2);

        String actual3 = MemberList.standardizeMemberName("Hermione Granger");
        String expected3 = "Hermione Granger";
        assertEquals(expected3, actual3);
    }

    @Test
    public void isNumber_executesNormally() {
        //positive number
        boolean actual1 = MemberList.isNumber("9999999999");
        boolean expected1 = true;
        assertEquals(expected1, actual1);

        //not a number
        boolean actual2 = MemberList.isNumber("HArrY POTTER");
        boolean expected2 = false;
        assertEquals(expected2, actual2);

        //negative number
        boolean actual3 = MemberList.isNumber("-10000000000000000");
        boolean expected3 = true;
        assertEquals(expected3, actual3);

        //exceed length of long
        boolean actual4 = MemberList.isNumber("9999999999999999999");
        boolean expected4 = false;
        assertEquals(expected4, actual4);
    }

    @Test
    public void findMemberIndex_executesNormally() {
        MemberList.deleteFromList(0);
        MemberList.addToList(member2);
        int actual1 = MemberList.findMemberIndex(MemberList.members, "Harry Potter");
        int expected1 = 0;
        assertEquals(expected1, actual1);

        int actual2 = MemberList.findMemberIndex(MemberList.members, "Draco Malfoy");
        int expected2 = -1;
        assertEquals(expected2, actual2);
        MemberList.deleteFromList(0);
    }
}
