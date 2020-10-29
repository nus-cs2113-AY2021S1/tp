package usercommunication;

/**
 * Represents the personal information about the user.
 */
public class UserInfo {
    protected String name = "";
    protected UserType type = UserType.UNKNOWN;

    public String getName() {
        return name;
    }

    public UserInfo(String name, String type) {
        this.name = name;
        if (type.equalsIgnoreCase("PROFESSOR")) {
            this.type = UserType.PROFESSOR;
        } else if (type.equalsIgnoreCase("STUDENT")) {
            this.type = UserType.STUDENT;
        }
    }

    public String fileString() {
        return name + "//" + type;
    }
}
