package seedu.duke.words;

public abstract class Words {
    protected String description;
    protected String definition;

    public Words(String description, String definition) {
        this.description = description;
        this.definition = definition;
    }

    /**
     * Function to get the type of this word.
     * i.e. Noun, Verb, or Adjective
     * @return the type of this word
     */
    public abstract String getType();

    public String getDefinition() {
        return this.definition;
    }

    public String getDescription() {
        return this.description;
    }
}
