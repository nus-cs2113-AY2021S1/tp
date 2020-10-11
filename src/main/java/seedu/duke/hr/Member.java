package seedu.duke.hr;

public class Member {
    public static int numOfMembers = 0;
    protected String memberName;
    protected int memberPhone;
    protected String memberEmail;
    protected String memberRole;

    public Member(String name, int phone, String email, String role) {
        this.memberName = name;
        this.memberPhone = phone;
        this.memberEmail = email;
        this.memberRole = role;
        numOfMembers += 1;
    }

    public int getMemberPhone() {

        return this.memberPhone;
    }

    public String getMemberEmail() {

        return this.memberEmail;
    }

    public String getMemberRole() {

        return this.memberRole;
    }

    /**
     * Prints task data in the desired form to be printed for user interaction.
     * */
    public String toString() {
        return "name: " + this.memberName + " |phone: " + this.getMemberPhone() + " |email: " + this.getMemberEmail() +
                " |role: " + this.getMemberRole();
    }

}
