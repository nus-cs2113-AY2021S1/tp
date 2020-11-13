package usercommunication;

/**
 * Represents the personal information about the user.
 */
public class UserInfo {
    protected String name = "";
    protected UserType type = UserType.UNKNOWN;
    protected boolean isAutoClear = false;

    public boolean isAutoClear() {
        return isAutoClear;
    }

    public void setAutoClear(boolean isAutoClear) {
        this.isAutoClear = isAutoClear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String userType) {
        if (userType.equalsIgnoreCase("PROFESSOR")) {
            this.type = UserType.PROFESSOR;
        } else if (userType.equalsIgnoreCase("STUDENT")) {
            this.type = UserType.STUDENT;
        }
    }

    public String getName() {
        return name;
    }

    public UserInfo() {
    }

    public UserInfo(String name, String type, boolean isAutoClear) {
        this.name = name;
        if (type.equalsIgnoreCase("PROFESSOR")) {
            this.type = UserType.PROFESSOR;
        } else if (type.equalsIgnoreCase("STUDENT")) {
            this.type = UserType.STUDENT;
        }
        this.isAutoClear = isAutoClear;
    }

    public String fileString() {
        return name + "//" + type + "//" + (isAutoClear ? 1 : 0);
    }
}
