package seedu.duke.hr;

public class Member {
    public static int numOfMembers = 0;
    protected String memberName;
    protected long memberPhone;
    protected String memberEmail;
    protected String memberRole;

    public Member(String name, long phone, String email, String role) {
        this.memberName = name;
        this.memberPhone = phone;
        this.memberEmail = email;
        this.memberRole = role;
        numOfMembers += 1;
    }

    public long getMemberPhone() {

        return this.memberPhone;
    }

    public String getMemberEmail() {

        return this.memberEmail;
    }

    public String getMemberRole() {

        return this.memberRole;
    }

    public String getMemberName() {

        return this.memberName;
    }

    public void setMemberRole(String newRole) {

        this.memberRole = newRole;
    }

    public void setMemberPhone(long newPhone) {

        this.memberPhone = newPhone;
    }

    public void setMemberEmail(String newEmail) {

        this.memberEmail = newEmail;
    }

    /**
     * Prints task data in the desired form to be printed for user interaction.
     * */
    public String toString() {
        return "name: " + this.memberName + " |phone: " + this.getMemberPhone() + " |email: " + this.getMemberEmail()
                + " |role: " + this.getMemberRole();
    }

    public boolean containsNameKeyword(String keyword) {
        boolean containsKeyword = memberName.toLowerCase().contains(keyword.toLowerCase());
        return containsKeyword;
    }

}
