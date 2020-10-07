package seedu.duke.person;

/**
 * Represents a Person.
 * A <code>Person</code> has an age, height, certain activity level, original and desired weight.
 */
public class Person {

    /* The height of the person in cm */
    private int height;
    /* The original weight of the person in kg */
    private int originalWeight;
    /* The target weight of the person in kg */
    private int targetWeight;
    private int age;
    private ActivityLevel activityLevel;

    /**
     * Constructs a <code>Person</code> with the given age, height, original weight, target weight and
     * activity level.
     *
     * @param age The age of the person.
     * @param height The height of the person.
     * @param originalWeight The original weight of the person.
     * @param targetWeight The target/desired weight that the person wants to achieve.
     * @param activityLevel The activity level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    public Person(int age, int height, int originalWeight, int targetWeight, ActivityLevel activityLevel) {
        this.age = age;
        this.height = height;
        this.originalWeight = originalWeight;
        this.targetWeight = targetWeight;
        this.activityLevel = activityLevel;
    }

    /**
     * Returns the age of the person.
     *
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person to the new age that is given.
     *
     * @param newAge The new/revised age of the person.
     */
    public void setAge(int newAge) {
        age = newAge;
    }

    /**
     * Returns the height of the person.
     *
     * @return The height of the person.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the person to the new height given.
     *
     * @param newHeight The new/revised height of the person.
     */
    public void setHeight(int newHeight) {
        height = newHeight;
    }

    /**
     * Returns the original weight of the person.
     *
     * @return The original weight of the person.
     */
    public int getOriginalWeight() {
        return originalWeight;
    }

    /**
     * Sets the original weight of the person to the new original weight given.
     *
     * @param newOriginalWeight The new/revised original weight of the person.
     */
    public void setOriginalWeight(int newOriginalWeight) {
        originalWeight = newOriginalWeight;
    }

    /**
     * Returns the target weight the person the person wants to achieve.
     *
     * @return The target weight the person wants to achieve.
     */
    public int getTargetWeight() {
        return targetWeight;
    }

    /**
     * Sets the target weight of the person to the new target weight given.
     *
     * @param newTargetWeight The new/revised target weight of the person.
     */
    public void setTargetWeight(int newTargetWeight) {
        targetWeight = newTargetWeight;
    }

    /**
     * Returns the activity level of the person.
     *
     * @return The activity level of the person.
     */
    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    /**
     * Sets the activity level of the person to the new activity level given.
     *
     * @param newActivityLevel The new/revised activity level of the person.
     */
    public void getActivityLevel(ActivityLevel newActivityLevel) {
        activityLevel = newActivityLevel;
    }
}
