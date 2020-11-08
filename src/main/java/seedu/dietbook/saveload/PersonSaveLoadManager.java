package seedu.dietbook.saveload;

import java.io.FileNotFoundException;

/**
 * This class is responsible for saving and loading personal information.
 * It has setters and getters for the following fields :
 * name, gender, age, height, original weight, current weight, target weight, activity level
 * it has a method load which loads from a specified saved file
 * it has a method save which saves the current information to a specified file name
 */
public class PersonSaveLoadManager {
    private static final int NUM_OF_ENTRIES = 8;
    private static final int TABLE_HEIGHT = 1;
    private static final int PERSON_DATA_ROW = 1;
    private static final String PERSON_FOLDER_NAME = "PERSONA_IS_NO_SUCH_PERSOOSOOSNSNSNS";

    private static final int NAME_ENTRY_INDEX = 1;
    private static final int GENDER_ENTRY_INDEX = 2;
    private static final int AGE_ENTRY_INDEX = 3;
    private static final int HEIGHT_ENTRY_INDEX = 4;
    private static final int ORIGINAL_WEIGHT_ENTRY_INDEX = 5;
    private static final int CURRENT_WEIGHT_ENTRY_INDEX = 6;
    private static final int TARGET_WEIGHT_ENTRY_INDEX = 7;
    private static final int ACTIVITY_LEVEL_ENTRY_INDEX = 8;

    private static final String DEFAULT_NAME = "Missing Name";
    private static final String DEFAULT_GENDER = "Others";
    private static final int DEFAULT_AGE = 0;
    private static final int DEFAULT_HEIGHT = 0;
    private static final int DEFAULT_ORIGINAL_WEIGHT = 0;
    private static final int DEFAULT_CURRENT_WEIGHT = 0;
    private static final int DEFAULT_TARGET_WEIGHT = 0;
    private static final int DEFAULT_ACTIVITY_LEVEL = 0;

    private String name;
    private String gender;
    private int age;
    private int height;
    private int originalWeight;
    private int currentWeight;
    private int targetWeight;
    private int activityLevel;

    private Saver saver;
    private Loader fileLoader;

    public PersonSaveLoadManager() {
        this.saver = new Saver(NUM_OF_ENTRIES, TABLE_HEIGHT);
        this.fileLoader = Loader.loadEmpty();

        this.name = DEFAULT_NAME;
        this.gender = DEFAULT_GENDER;
        this.age = DEFAULT_AGE;
        this.height = DEFAULT_HEIGHT;
        this.originalWeight = DEFAULT_ORIGINAL_WEIGHT;
        this.currentWeight = DEFAULT_CURRENT_WEIGHT;
        this.targetWeight = DEFAULT_TARGET_WEIGHT;
        this.activityLevel = DEFAULT_ACTIVITY_LEVEL;
    }

    /**
     * loads a saved file and fill up all the fields with the data from the loaded file.
     * set the field to default is the loaded file does not contain the field
     * @param fileName name of the saved file to be loaded to
     * @throws FileNotFoundException there is no save file with the name
     * @throws IllegalAccessException the get method is called when the loader is empty, will never happen with the
     *     current implementation.
     */
    public void load(String fileName) throws FileNotFoundException, IllegalAccessException {
        this.reset();
        this.fileLoader = Loader.load(PERSON_FOLDER_NAME, fileName);
        this.name = this.fileLoader.get(NAME_ENTRY_INDEX, PERSON_DATA_ROW).orElse(DEFAULT_NAME);
        this.gender = this.fileLoader.get(GENDER_ENTRY_INDEX, PERSON_DATA_ROW).orElse(DEFAULT_GENDER);
        this.age = Integer.parseInt(this.fileLoader.get(AGE_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_AGE)));
        this.height = Integer.parseInt(this.fileLoader.get(HEIGHT_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_HEIGHT)));
        this.originalWeight = Integer.parseInt(this.fileLoader.get(ORIGINAL_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_ORIGINAL_WEIGHT)));
        this.currentWeight = Integer.parseInt(this.fileLoader.get(CURRENT_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_CURRENT_WEIGHT)));
        this.targetWeight = Integer.parseInt(this.fileLoader.get(TARGET_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_TARGET_WEIGHT)));
        this.activityLevel = Integer.parseInt(this.fileLoader.get(ACTIVITY_LEVEL_ENTRY_INDEX, PERSON_DATA_ROW).orElse(
                Integer.toString(DEFAULT_ACTIVITY_LEVEL)));

    }

    /**
     * Method saves the current data stored in the fields to the specified file name.
     * Will over-write files with the same name.
     * @param fileName name of the file to save to
     */
    public void save(String fileName) {
        this.saver.reset();
        this.saver.add(this.name, NAME_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(this.gender, GENDER_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.age), AGE_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.height), HEIGHT_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.originalWeight), ORIGINAL_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.currentWeight), CURRENT_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.targetWeight), TARGET_WEIGHT_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.add(Integer.toString(this.activityLevel), ACTIVITY_LEVEL_ENTRY_INDEX, PERSON_DATA_ROW);
        this.saver.save(PERSON_FOLDER_NAME, fileName);
    }

    public void reset() {
        this.fileLoader = Loader.loadEmpty();
        this.name = DEFAULT_NAME;
        this.gender = DEFAULT_GENDER;
        this.age = DEFAULT_AGE;
        this.height = DEFAULT_HEIGHT;
        this.originalWeight = DEFAULT_ORIGINAL_WEIGHT;
        this.currentWeight = DEFAULT_CURRENT_WEIGHT;
        this.targetWeight = DEFAULT_TARGET_WEIGHT;
        this.activityLevel = DEFAULT_ACTIVITY_LEVEL;
    }

    // ----- Setters and Getters ------
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getOriginalWeight() {
        return originalWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getTargetWeight() {
        return targetWeight;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setOriginalWeight(int originalWeight) {
        this.originalWeight = originalWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void setTargetWeight(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }
}
