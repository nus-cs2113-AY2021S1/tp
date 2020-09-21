package seedu.duke.question;

public class FillBlank extends Question {
    private String stringBefore;
    private String stringAfter;

    public FillBlank(String correctAnswer, String stringBefore, String stringAfter) {
        super(correctAnswer, "");
        setStringBefore(stringBefore);
        setStringAfter(stringAfter);
    }

    public void setStringBefore(String stringBefore) {
        this.stringBefore = stringBefore;
    }

    public void setStringAfter(String stringAfter) {
        this.stringAfter = stringAfter;
    }

    public String getStringBefore() {
        return stringBefore;
    }

    public String getStringAfter() {
        return stringAfter;
    }
}
