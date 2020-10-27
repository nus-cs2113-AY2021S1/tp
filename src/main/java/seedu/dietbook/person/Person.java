package seedu.dietbook.person;

/**
 * Represents a Person.
 * A <code>Person</code> has a name, gender, age, height, certain activity level, original and desired weight.
 */
public class Person {

    /* The height of the person in cm */
    private int height;
    /* The original weight of the person when he or she first started using DietBook in kg */
    private int originalWeight;
    /* The current weight of the person in kg */
    private int currentWeight;
    /* The target weight of the person in kg */
    private int targetWeight;
    private int age;
    private ActivityLevel activityLevel;
    private Gender gender;
    private String name;

    /**
     * Constructs a <code>Person</code> with the given name, gender, age, height, activity level, original,
     * current and target weight.
     *
     * @param name The name of the person.
     * @param gender The gender of the person.
     * @param age The age of the person.
     * @param height The height of the person.
     * @param originalWeight The original weight of the person when he or she first started using DietBook.
     * @param currentWeight The current weight of the person.
     * @param targetWeight The target/desired weight that the person wants to achieve.
     * @param activityLevel The activity level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    public Person(String name, Gender gender, int age, int height, int originalWeight,
                  int currentWeight, int targetWeight, ActivityLevel activityLevel) {
        performAssertionsForPerson(name, gender, age, height, originalWeight, currentWeight,
                targetWeight, activityLevel);

        this.name = name.trim();
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.originalWeight = originalWeight;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.activityLevel = activityLevel;
    }

    /**
     * Sets all the attributes of a person to the new attributes given.
     *
     * @param newName The new/revised name of the person.
     * @param newGender The new/revised gender of the person.
     * @param newAge The new/revised age of the person.
     * @param newHeight The new/revised height of the person.
     * @param newOriginalWeight The new/revised original weight of the person when he or she first started
     *     using DietBook.
     * @param newCurrentWeight The new/revised current weight of the person.
     * @param newTargetWeight The new/revised target weight that the person wants to achieve.
     * @param newActivityLevel The new/revised activity level of the person or in other words, the amount
     *     of exercise the person engages in.
     */
    public void setAll(String newName, Gender newGender, int newAge, int newHeight, int newOriginalWeight,
                             int newCurrentWeight, int newTargetWeight, ActivityLevel newActivityLevel) {
        setName(newName);
        setGender(newGender);
        setAge(newAge);
        setHeight(newHeight);
        setOriginalWeight(newOriginalWeight);
        setCurrentWeight(newCurrentWeight);
        setTargetWeight(newTargetWeight);
        setActivityLevel(newActivityLevel);
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person to the new name given.
     *
     * @param newName The new/revised name of the person.
     */
    public void setName(String newName) {
        performAssertionsForNameInput(newName);
        name = newName.trim();
    }

    /**
     * Returns the gender of the person.
     *
     * @return The gender of the person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person to the new gender given.
     *
     * @param newGender The new/revised gender of the person.
     */
    public void setGender(Gender newGender) {
        performAssertionsForGenderInput(newGender);
        gender = newGender;
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
        performAssertionsForAgeInput(newAge);
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
        performAssertionsForHeight(newHeight);
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
        performAssertionsForWeight(newOriginalWeight,"Original weight");
        originalWeight = newOriginalWeight;
    }

    /**
     * Returns the current weight of the person when he or she first started using DietBook.
     *
     * @return The current weight of the person when he or she first started using DietBook.
     */
    public int getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Sets the current weight of the person to the new current weight given.
     *
     * @param newCurrentWeight The new/revised current weight of the person.
     */
    public void setCurrentWeight(int newCurrentWeight) {
        performAssertionsForWeight(newCurrentWeight, "Current weight");
        currentWeight = newCurrentWeight;
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
        performAssertionsForWeight(newTargetWeight, "Target weight");
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
    public void setActivityLevel(ActivityLevel newActivityLevel) {
        performAssertionsForActivityLevel(newActivityLevel);
        activityLevel = newActivityLevel;
    }

    /**
     * Returns a string representation of all information related to the user.
     * Information includes name, gender, age, height, original weight, target weight and activity level.
     *
     * @return A string representation of all information related to the user.
     */
    @Override
    public String toString() {
        String userInformation = "  Name: " + name + System.lineSeparator()
                + "  Gender: " + gender.getDescription() + System.lineSeparator()
                + "  Age: " + age + System.lineSeparator()
                + "  Height: " + height + "cm" + System.lineSeparator()
                + "  Original weight: " + originalWeight + "kg" + System.lineSeparator()
                + "  Current weight: " + currentWeight + "kg" + System.lineSeparator()
                + "  Target weight: " + targetWeight + "kg" + System.lineSeparator()
                + "  Activity level: " + activityLevel.getDescription();
        return userInformation;
    }

    /**
     * Performs assertions on all possible person inputs parameters.
     *
     * @param name The name of the person.
     * @param gender The gender of the person.
     * @param age The age of the person.
     * @param height The height of the person.
     * @param originalWeight The original weight of the person when he or she first started using DietBook.
     * @param currentWeight The current weight of the person.
     * @param targetWeight The target/desired weight that the person wants to achieve.
     * @param activityLevel The activity level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    private void performAssertionsForPerson(String name, Gender gender, int age, int height,
                                            int originalWeight, int currentWeight, int targetWeight,
                                            ActivityLevel activityLevel) {
        performAssertionsForNameInput(name);
        performAssertionsForGenderInput(gender);
        performAssertionsForAgeInput(age);
        performAssertionsForHeight(height);
        performAssertionsForWeight(originalWeight, "Original weight");
        performAssertionsForWeight(currentWeight, "Current weight");
        performAssertionsForWeight(targetWeight, "Target weight");
        performAssertionsForActivityLevel(activityLevel);
    }

    /**
     * Performs assertion on the activity level input.
     *
     * @param activityLevel The activity level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    private void performAssertionsForActivityLevel(ActivityLevel activityLevel) {
        assert activityLevel != null : "Activity level of person should not be null";
    }

    /**
     * Performs assertions the weight related inputs.
     *
     * @param weight Either the original, current or target weight of the person.
     * @param weightType A string describing whether the weight given the original, current or target weight.
     */
    private void performAssertionsForWeight(int weight, String weightType) {
        assert weight > 0 : weightType + " of person should be greater than 0";
        assert weight < 500 : weightType + " of person should less than 500";
    }

    /**
     * Performs assertions on the height input.
     *
     * @param height The height of the person.
     */
    private void performAssertionsForHeight(int height) {
        assert height > 0 : "Height of person should be greater than 0";
        assert height < 300 : "Height of person should be less than 300";
    }

    /**
     * Performs assertion on the gender input.
     *
     * @param gender The gender of the person.
     */
    private void performAssertionsForGenderInput(Gender gender) {
        assert gender != null : "Gender of person should not be null";
    }

    /**
     * Performs assertions on the name input.
     *
     * @param name The name of the person.
     */
    private void performAssertionsForNameInput(String name) {
        assert name != null : "The name of person should not be null";
        assert name.trim().length() > 0 : "The name of person should not be an empty string";
    }

    /**
     * Performs assertions on the age input.
     *
     * @param age The age of the person.
     */
    private void performAssertionsForAgeInput(int age) {
        assert age > 0 : "The age of person should be greater than 0";
        assert age < 150 : "The age of person should be lesser than 150";
    }
}
