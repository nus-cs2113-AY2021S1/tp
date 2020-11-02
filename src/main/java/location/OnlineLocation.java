package location;

import event.Assignment;

/**
 * Virtual location is also location!
 * This class represents the online meetings (e.g. zoom sessions) for people to attend, or the link to submit
 * assignment.
 * This format should be /o url /p pwd, or /o url, since not all sessions need a password, and the submission link
 * does not require a password.
 */
public class OnlineLocation {
    private final String link;
    private String password;

    /**
     * the constructor of the object if the meeting does not need pwd.
     *
     * @param link the url of the online meeting
     */
    public OnlineLocation(String link) {
        assert link != null;
        this.link = link;
    }

    /**
     * the constructor of the object if the meeting needs pwd.
     *
     * @param link the url of the online meeting
     * @param pwd  the password of the online meeting
     */
    public OnlineLocation(String link, String pwd) {
        assert link != null;
        assert pwd != null;
        this.link = link;
        this.password = pwd;
    }

    /**
     * Returns the details of the online location.
     *
     * @return the link of the location. If there is password, return the password also.
     */
    public String toString() {
        return "link: " + link + (password != null ? " password: " + password : "");
    }

    /**
     * Returns the details of the location to be printed in a file.
     *
     * @return the link of the meeting. If there is password, return the password also.
     */
    public String fileString() {
        return "online//" + link + (password != null ? "//" + password : "");
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object instanceof OnlineLocation) {
            isEqual = (this.link.equals(((OnlineLocation) object).link));
        }

        return isEqual;
    }

    public String getLink() {
        return link;
    }
}
