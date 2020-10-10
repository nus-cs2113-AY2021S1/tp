package seedu.duke.hr;

import java.util.ArrayList;

public class MemberList {
    public static ArrayList<Member> members = new ArrayList<>();

    public static void addToList(Member m) {
        members.add(m);
    }

    public static String listMembers() {
        String output;
        if (Member.numOfMembers==0) {
            output = "Dude, the member list is empty! o_O";
        } else {
            output = "Here is the list of members in your CCA: \n";
            for (int i = 0; i < Member.numOfMembers; i++) {
                int index = i+1;
                output = output.concat(index + ".");
                output = output.concat(members.get(i).toString() + "\n");
            }
        }
        return output;
    }

    public static String deleteFromList(int index) {
        String output;
        try {
            output = "Noted. I'll remove this member: \n";
            output = output.concat(members.get(index).toString() + "\n");
            members.remove(index);
            Member.numOfMembers--;
            output = output.concat("Now you have " + Member.numOfMembers + " member");
            output = output.concat(((Member.numOfMembers > 1)?"s":"") + " in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            output = "OOPS!!! The member does not exist.\n";
        }
        return output;
    }
}
