package seedu.dietbook.person;

/**
 * Represents the physical activity level of a person or the amount of exercise a person engages in.
 * An <code>FitnessLevel</code> has a description.
 */
public enum FitnessLevel {
    NONE("You hardly engage in any exercise or have a job that requires little to no physical "
            + "activity."),
    LOW("You engage in some form of light exercise or have a job that requires some "
            + "physical activity."),
    MEDIUM("You engage in moderate amount of exercise or have a job that requires moderate "
            + "physical activity."),
    HIGH("You engage in vigorous exercise or have a physically demanding job."),
    EXTREME("You engage in extremely vigorous exercise or have an extremely physically demanding"
            + " job.");

    private final String description;

    /**
     * Constructs an <code>FitnessLevel</code> given the description.
     *
     * @param description The description of the fitness level.
     */
    FitnessLevel(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the fitness level.
     *
     * @return The description of the fitness level.
     */
    public String getDescription() {
        return description;
    }
}
