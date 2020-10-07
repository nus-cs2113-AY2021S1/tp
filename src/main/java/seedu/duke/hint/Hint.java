package seedu.duke.hint;

public class Hint implements HintInterface {
    private String description;

    public Hint(String description) {
        this.description = description;
    }

    @Override
    public String getHintDescription() {
        return description;
    }
}
