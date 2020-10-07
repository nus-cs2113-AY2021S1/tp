package seedu.duke.words;

public class Adjective extends Words {
    public Adjective(String description, String definition) {
        super(description, definition);
    }

    public String getType() {
        return "adjective";
    }
}
