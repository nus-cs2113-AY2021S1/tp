package seedu.dietbook.person;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a Person.
 * A <code>Person</code> has a name, gender, age, height, certain fitness level, original and desired weight.
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
    private FitnessLevel fitnessLevel;
    private Gender gender;
    private String name;
    private final Logger logger;

    /**
     * Constructs a <code>Person</code> with the given name, gender, age, height, fitness level, original,
     * current and target weight.
     *
     * @param name The name of the person.
     * @param gender The gender of the person.
     * @param age The age of the person.
     * @param height The height of the person.
     * @param originalWeight The original weight of the person when he or she first started using DietBook.
     * @param currentWeight The current weight of the person.
     * @param targetWeight The target/desired weight that the person wants to achieve.
     * @param fitnessLevel The fitness level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    public Person(String name, Gender gender, int age, int height, int originalWeight,
                  int currentWeight, int targetWeight, FitnessLevel fitnessLevel) {
        performAssertionsForPerson(name, gender, age, height, originalWeight, currentWeight,
                targetWeight, fitnessLevel);

        logger = Logger.getLogger(Person.class.getName());
        initialiseLogger();
        logger.log(Level.FINE, "Start constructing a person");
        logger.log(Level.FINE, "Name: " + name);
        logger.log(Level.FINE, "Gender: " + gender.getDescription());
        logger.log(Level.FINE, "Age: " + age);
        logger.log(Level.FINE, "Height: " + height);
        logger.log(Level.FINE, "Original weight: " + originalWeight);
        logger.log(Level.FINE, "Current weight: " + currentWeight);
        logger.log(Level.FINE, "Target weight: " + targetWeight);
        logger.log(Level.FINE, "Fitness Level: " + fitnessLevel.getDescription());

        this.name = name.trim();
        logger.log(Level.FINE, "Trimmed Name: " + this.name);
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.originalWeight = originalWeight;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.fitnessLevel = fitnessLevel;
        logger.log(Level.FINE, "Person constructed");
    }

    /**
     * Initialises the logger and sets the log level.
     */
    private void initialiseLogger() {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.WARNING);
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
     * @param newFitnessLevel The new/revised fitness level of the person or in other words, the amount
     *     of exercise the person engages in.
     */
    public void setAll(String newName, Gender newGender, int newAge, int newHeight, int newOriginalWeight,
                             int newCurrentWeight, int newTargetWeight, FitnessLevel newFitnessLevel) {
        setName(newName);
        setGender(newGender);
        setAge(newAge);
        setHeight(newHeight);
        setOriginalWeight(newOriginalWeight);
        setCurrentWeight(newCurrentWeight);
        setTargetWeight(newTargetWeight);
        setFitnessLevel(newFitnessLevel);
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
        logger.log(Level.FINE, "New name: " + newName);
        name = newName.trim();
        logger.log(Level.FINE, "Trimmed new name: " + this.name);
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
        logger.log(Level.FINE, "New gender: " + newGender.getDescription());
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
        logger.log(Level.FINE, "New age: " + newAge);
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
        logger.log(Level.FINE, "New height: " + newHeight);
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
        logger.log(Level.FINE, "New original weight: " + newOriginalWeight);
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
        logger.log(Level.FINE, "New current weight: " + newCurrentWeight);
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
        logger.log(Level.FINE, "New target weight: " + newTargetWeight);
        targetWeight = newTargetWeight;
    }

    /**
     * Returns the fitness level of the person.
     *
     * @return The fitness level of the person.
     */
    public FitnessLevel getFitnessLevel() {
        return fitnessLevel;
    }

    /**
     * Sets the fitness level of the person to the new fitness level given.
     *
     * @param newFitnessLevel The new/revised fitness level of the person.
     */
    public void setFitnessLevel(FitnessLevel newFitnessLevel) {
        performAssertionsForFitnessLevel(newFitnessLevel);
        logger.log(Level.FINE, "New fitness level: " + newFitnessLevel);
        fitnessLevel = newFitnessLevel;
    }

    /**
     * Returns a string representation of all information related to the user.
     * Information includes name, gender, age, height, original weight, target weight and fitness level.
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
                + "  Fitness level: " + fitnessLevel.getDescription();
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
     * @param fitnessLevel The fitness level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    private void performAssertionsForPerson(String name, Gender gender, int age, int height,
                                            int originalWeight, int currentWeight, int targetWeight,
                                            FitnessLevel fitnessLevel) {
        performAssertionsForNameInput(name);
        performAssertionsForGenderInput(gender);
        performAssertionsForAgeInput(age);
        performAssertionsForHeight(height);
        performAssertionsForWeight(originalWeight, "Original weight");
        performAssertionsForWeight(currentWeight, "Current weight");
        performAssertionsForWeight(targetWeight, "Target weight");
        performAssertionsForFitnessLevel(fitnessLevel);
    }

    /**
     * Performs assertion on the fitness level input.
     *
     * @param fitnessLevel The fitness level of the person or in other words, the amount of exercise the
     *     person engages in.
     */
    private void performAssertionsForFitnessLevel(FitnessLevel fitnessLevel) {
        assert fitnessLevel != null : "Fitness level of person should not be null";
    }

    /**
     * Performs assertions on the weight related inputs.
     *
     * @param weight Either the original, current or target weight of the person.
     * @param weightType A string describing whether the given weight is the original, current or target
     *     weight.
     */
    private void performAssertionsForWeight(int weight, String weightType) {
        int minWeight = 1;
        assert weight >= minWeight : weightType + " of person should be greater than 0";
        int maxWeight = 500;
        assert weight <= maxWeight : weightType + " of person should less than 500";
    }

    /**
     * Performs assertions on the height input.
     *
     * @param height The height of the person.
     */
    private void performAssertionsForHeight(int height) {
        int minHeight = 1;
        assert height >= minHeight : "Height of person should be greater than 0";
        int maxHeight = 300;
        assert height <= maxHeight : "Height of person should be less than 300";
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
        int minAge = 0;
        assert age >= minAge : "The age of person should be equals to or greater than 0";
        int maxAge = 150;
        assert age <= maxAge : "The age of person should be lesser than 150";
    }
}
