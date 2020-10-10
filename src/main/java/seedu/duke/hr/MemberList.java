package seedu.duke.hr;

import java.util.ArrayList;

public class MemberList {
    public static ArrayList<Member> members = new ArrayList<>();

    public static void addToList(Member m) {
        members.add(m);
    }
}
