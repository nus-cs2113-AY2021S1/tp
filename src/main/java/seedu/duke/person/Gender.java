package seedu.duke.person;

/**
 * Represents the gender of a person.
 * A <code>Gender</code> has a description.
 */
public enum Gender {
    FEMALE("female"),
    MALE("male");

    private final String description;

    /**
     * Constructs a <code>Gender</code> given the description.
     *
     * @param description The description of the gender.
     */
    Gender(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the gender.
     *
     * @return The description of the gender.
     */
    public String getDescription() {
        return description;
    }

}
