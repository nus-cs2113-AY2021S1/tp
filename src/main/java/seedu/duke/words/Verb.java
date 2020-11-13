package seedu.duke.words;

public class Verb extends Words {
    public Verb(String description, String definition) {
        super(description, definition);
    }

    public String getType() {
        return "verb";
    }

    @Override
    public String toString() {
        return "v" + " > " + this.description + " > " + this.definition;
    }
}
