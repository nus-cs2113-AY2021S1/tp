package cheatsheet;

public class CheatSheet {
    private String name;
    private String subject;
    private String details;
    private boolean isFavourite;

    public CheatSheet(String name, String subject, String details) {
        this.name = name;
        this.subject = subject;
        this.details = details;
        this.isFavourite = false;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public String getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getWrappedDetails() {
        String[] parsedDescription = this.getDetails().split("\n");
        StringBuilder wrappedDescription = new StringBuilder();
        for (String s : parsedDescription) {
            wrappedDescription.append(s);
            wrappedDescription.append("\n\t\t\t ");
        }
        return wrappedDescription.toString();
    }

    @Override
    public String toString() {
        return "CheatSheet{"
                + "Name='" + name + '\''
                + ", Subject ='" + subject + '\''
                + ", Details='" + details + '\''
                + '}';
    }
}

