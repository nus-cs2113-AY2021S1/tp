package seedu.duke.hr;

public class Member {
    public static int numOfMembers = 0;
    protected String memberName;
    protected int memberPhone;
    protected String memberEmail;
    protected String[] memberRoles;

    public Member(String name, int phone, String email){
        this.memberName = name;
        this.memberPhone = phone;
        this.memberEmail = email;
        numOfMembers += 1;
    }

    public int getMemberPhone() {
        return this.memberPhone;
    }

    public String getMemberEmail() {
        return this.memberEmail;
    }

    public void setMemberRole (String role) {
        memberRoles[memberRoles.length] = role;
    }

    public String[] getMemberRole() {
        return this.memberRoles;
    }

    /**
     * Prints task data in the desired form to be printed for user interaction.
     * */
    public void printMember() {
        System.out.format("name: %s |phone: %d |email: %s%n", this.memberName, this.getMemberPhone(), this.getMemberEmail());
    }

}
