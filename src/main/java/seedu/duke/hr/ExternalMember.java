package seedu.duke.hr;

/**
 * unused in v1.0
 */
public class ExternalMember extends Member {
    public static int numOfExternalMembers = 0;
    protected String organization;

    public ExternalMember(String name, int phone, String email){
        super(name, phone, email);
        numOfMembers += 1;
    }

    public void setOrganization (String orgName) {
        organization = orgName;
    }

    public String getOrganization() {
        return this.organization;
    }
}
