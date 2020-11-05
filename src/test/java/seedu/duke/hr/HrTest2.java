package seedu.duke.hr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HrTest2 {
    @Test
    public void memberSearch() {
        String non = MemberList.deleteFromList(1);
        non = MemberList.addToList(new Member("Suzuhara Ruru",24341141,
                "2434ruru@yagoo.com","member"));
        non = MemberList.addToList(new Member("Tadokoro Kouji",1145141919,"kouji@gmail.com",
                "President"));
        non = MemberList.addToList(new Member("Lize",1919810,"lize24@outlook.com",
                "vice-president"));
        String expected = "I have found 2 result for you:\n"
                + "2.name: Tadokoro Kouji |phone: 1145141919 |email: kouji@gmail.com |"
                + "role: President |attendance rate: 0%\n"
                + "3.name: Lize |phone: 1919810 |email: lize24@outlook.com "
                + "|role: vice-president |attendance rate: 0%\n";
        String actual = MemberList.search(true,false,false,false,false,"president",
                "","","","");
        assertEquals(expected,actual);
    }
}
