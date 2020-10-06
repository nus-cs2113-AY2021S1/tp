package seedu.duke;

public class UserProfile extends Human {
    protected String birthdate;
    protected String gender;

    public UserProfile(String name, String birthdate, String gender) {
        super(name);
        this.birthdate = birthdate;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Name= " + getName() + ", birthdate= " + birthdate + ", gender= " + gender;
    }
}
