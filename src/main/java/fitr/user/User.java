package fitr.user;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.Calorie;
import fitr.ui.Ui;

import static fitr.common.Messages.USER_SETUP_GREET;
import static fitr.common.Messages.INPUT_NAME;
import static fitr.common.Messages.INPUT_AGE;
import static fitr.common.Messages.INPUT_HEIGHT;
import static fitr.common.Messages.INPUT_WEIGHT;
import static fitr.common.Messages.INPUT_GENDER;
import static fitr.common.Messages.SETUP_COMPLETE;
import static fitr.common.Messages.ERROR_INVALID_AGE_INPUT;
import static fitr.common.Messages.ERROR_INVALID_GENDER_INPUT;
import static fitr.common.Messages.ERROR_INVALID_HEIGHT_INPUT;
import static fitr.common.Messages.ERROR_INVALID_WEIGHT_INPUT;
import static fitr.common.Messages.NAME_OUTPUT_HEADER;
import static fitr.common.Messages.AGE_OUTPUT_HEADER;
import static fitr.common.Messages.GENDER_OUTPUT_HEADER;
import static fitr.common.Messages.HEIGHT_OUTPUT_HEADER;
import static fitr.common.Messages.WEIGHT_OUTPUT_HEADER;
import static fitr.common.Messages.LINE_BREAK;
import static fitr.common.Messages.MALE_SYMBOL;
import static fitr.common.Messages.FEMALE_SYMBOL;
import static fitr.common.Messages.MALE_STRING;
import static fitr.common.Messages.FEMALE_STRING;

/**
 * User class keeps track of user's personal information.
 */
public class User {
    private String name;
    private int age;
    private double height;
    private double weight;
    private String gender;

    /**
     * Setup configures user profile for first time use.
     */
    public void setup() {
        Ui.printCustomMessage(USER_SETUP_GREET);
        Ui.printCustomMessage(INPUT_NAME);
        setName();
        Ui.printCustomMessage(INPUT_AGE);
        setupAge();
        Ui.printCustomMessage(INPUT_HEIGHT);
        setupHeight();
        Ui.printCustomMessage(INPUT_WEIGHT);
        setupWeight();
        Ui.printCustomMessage(INPUT_GENDER);
        setupGender();
        Ui.printCustomMessage(SETUP_COMPLETE);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public void setName() {
        name = Ui.read();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setupAge() {
        int ageInput = 0;
        while (ageInput <= 0) {
            try {
                ageInput = Integer.parseInt(Ui.read());
                if (ageInput <= 0) {
                    Ui.printCustomMessage(ERROR_INVALID_AGE_INPUT + INPUT_AGE);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomMessage(ERROR_INVALID_AGE_INPUT + INPUT_AGE);
                ageInput = 0;
            }
        }
        setAge(ageInput);
    }

    public void setupHeight() {
        double heightInput = 0.00;
        // Height (in m)
        while (heightInput <= 0.00) {
            try {
                heightInput = Double.parseDouble(Ui.read());
                if (heightInput <= 0.00) {
                    Ui.printCustomMessage(ERROR_INVALID_HEIGHT_INPUT + INPUT_HEIGHT);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomMessage(ERROR_INVALID_HEIGHT_INPUT + INPUT_HEIGHT);
                heightInput = 0.00;
            }
        }
        setHeight(heightInput);
    }

    public void setupWeight() {
        // Weight (in kg)
        double weightInput = 0.00;
        while (weightInput <= 0.00) {
            try {
                weightInput = Double.parseDouble(Ui.read());
                if (weightInput <= 0.00) {
                    Ui.printCustomMessage(ERROR_INVALID_WEIGHT_INPUT + INPUT_WEIGHT);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomMessage(ERROR_INVALID_WEIGHT_INPUT + INPUT_WEIGHT);
                weightInput = 0.00;
            }
        }
        setWeight(weightInput);
    }

    public void setupGender() {
        String genderInput = Ui.read();
        while (!genderInput.equalsIgnoreCase("m") && !genderInput.equalsIgnoreCase("f")) {
            Ui.printCustomMessage(ERROR_INVALID_GENDER_INPUT + INPUT_GENDER);
            genderInput = Ui.read();
        }
        if (genderInput.equalsIgnoreCase(MALE_SYMBOL)) {
            setGender(MALE_STRING);
        } else if (genderInput.equalsIgnoreCase(FEMALE_SYMBOL)) {
            setGender(FEMALE_STRING);
        }
    }

    @Override
    public String toString() {
        return NAME_OUTPUT_HEADER + getName() + LINE_BREAK + AGE_OUTPUT_HEADER + getAge() + LINE_BREAK
                + GENDER_OUTPUT_HEADER + getGender() + LINE_BREAK + HEIGHT_OUTPUT_HEADER + getHeight()
                + LINE_BREAK + WEIGHT_OUTPUT_HEADER + getWeight();
    }

    public Calorie calculateCalorieBurnt(ExerciseList exerciseList) {
        int index = 0;
        int totalBurnt = 0;
        while (index < exerciseList.getSize()) {
            totalBurnt += exerciseList.getExercise(index).getCalories();
            index++;
        }
        return new Calorie(totalBurnt);
    }

    public Calorie calculateCalorieConsumed(FoodList foodList) {
        int index = 0;
        int totalConsumed = 0;
        while (index < foodList.getSize()) {
            totalConsumed += foodList.getFood(index).getCalories();
            index++;
        }
        return new Calorie(totalConsumed);
    }

    public Calorie calculateCalorie(FoodList foodList, ExerciseList exerciseList) {
        int totalCalories;
        Calorie totalConsumed = calculateCalorieConsumed(foodList);
        Calorie totalBurnt = calculateCalorieBurnt(exerciseList);
        totalCalories = totalConsumed.get() - totalBurnt.get();
        return new Calorie(totalCalories);
    }

    public void loadUserData(String name, int age, double height, double weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public double getBmi() {
        return weight / ((height) * (height));
    }

}
