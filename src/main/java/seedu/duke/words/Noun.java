package seedu.duke.words;

public class Noun extends Words {
    public Noun(String description, String definition) {
        super(description, definition);
    }

    public String getType() {
        return "noun";
    }

    @Override
    public String toString() {
        return "n" + " > " + this.description + " > " + this.definition;
    }
}
