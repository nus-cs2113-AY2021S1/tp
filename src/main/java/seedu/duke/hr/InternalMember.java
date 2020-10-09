package seedu.duke.hr;

/**
 * unused in v1.0
 */
public class InternalMember extends Member {
    public static int numOfInternalMembers = 0;
    protected String[] memberRoles;


    public InternalMember(String name, int phone, String email) {
        super(name, phone, email);
        numOfMembers += 1;
    }

    public void setMemberRole (String role) {
        memberRoles[memberRoles.length] = role;
    }

    public String[] getMemberRole() {
        return this.memberRoles;
    }


}
