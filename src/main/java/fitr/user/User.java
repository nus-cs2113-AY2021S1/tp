package fitr.user;

import fitr.command.ViewCommand;
import fitr.common.Messages;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.calorie.Calorie;
import fitr.storage.UserStorage;
import fitr.ui.Ui;

import java.util.logging.Logger;

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
import static fitr.common.Messages.ERROR_INVALID_FITNESS_INPUT;
import static fitr.common.Messages.NAME_OUTPUT_HEADER;
import static fitr.common.Messages.AGE_OUTPUT_HEADER;
import static fitr.common.Messages.GENDER_OUTPUT_HEADER;
import static fitr.common.Messages.HEIGHT_OUTPUT_HEADER;
import static fitr.common.Messages.WEIGHT_OUTPUT_HEADER;
import static fitr.common.Messages.FITNESS_OUTPUT_HEADER;
import static fitr.common.Messages.LINE_BREAK;
import static fitr.common.Messages.MALE_SYMBOL;
import static fitr.common.Messages.FEMALE_SYMBOL;
import static fitr.common.Messages.MALE_STRING;
import static fitr.common.Messages.FEMALE_STRING;
import static fitr.common.Messages.FIT_STRING;
import static fitr.common.Messages.UNFIT_STRING;
import static fitr.common.Messages.NORMAL_STRING;
import static fitr.common.Messages.NULL_STRING;
import static fitr.common.Messages.INPUT_FITNESS_LEVEL;
import static fitr.common.Messages.FORMAT_EDIT_AGE;
import static fitr.common.Messages.FORMAT_EDIT_HEIGHT;
import static fitr.common.Messages.FORMAT_EDIT_WEIGHT;
import static fitr.common.Messages.FORMAT_EDIT_GENDER;
import static fitr.common.Messages.FORMAT_EDIT_FITNESS;
import static fitr.common.Messages.NAME_FORMAT;
import static fitr.common.Messages.FORMAT_EDIT_NAME;
import static fitr.common.Messages.RANGE_EDIT_HEIGHT;
import static fitr.common.Messages.RANGE_EDIT_WEIGHT;
import static fitr.common.Messages.RANGE_EDIT_AGE;

/**
 * User class keeps track of user's personal information.
 */
public class User {
    private String name;
    private int age;
    private double height;
    private double weight;
    private String gender;
    private int userFitnessLevel; // 0 for unfit; 1 for normal; 2 for Fit

    private static final Logger LOGGER = Logger.getLogger("User");

    public User() {
        setup();
    }

    public User(String name, int age, double height, double weight, String gender, int userFitnessLevel) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.userFitnessLevel = userFitnessLevel;
    }

    /**
     * Setup configures user profile for first time use.
     */
    public void setup() {
        Ui.printCustomMessage(USER_SETUP_GREET);
        Ui.printCustomMessage(INPUT_NAME);
        LOGGER.fine("Setting up profile name.");
        setName(Ui.read().trim(), false);
        Ui.printCustomMessage(INPUT_AGE);
        LOGGER.fine("Setting up profile age.");
        setupAge(null, false);
        Ui.printCustomMessage(INPUT_HEIGHT);
        LOGGER.fine("Setting up profile height.");
        setupHeight(null, false);
        Ui.printCustomMessage(INPUT_WEIGHT);
        LOGGER.fine("Setting up profile weight.");
        setupWeight(null, false);
        Ui.printCustomMessage(INPUT_GENDER);
        LOGGER.fine("Setting up profile gender.");
        setupGender(null, false);
        Ui.printCustomMessage(INPUT_FITNESS_LEVEL);
        LOGGER.fine("Setting up profile fitness level.");
        setupFitnessLevel(null, false);
        Ui.printCustomMessage(SETUP_COMPLETE);
        LOGGER.fine("User setup completed.");
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

    public void setName(String name, Boolean isEdit) {
        while (!name.matches("^[a-zA-z ]+$") || name.trim().length() == 0) {
            Ui.printCustomError(NAME_FORMAT);
            if (isEdit) {
                Ui.printCustomError("FORMAT: " + FORMAT_EDIT_NAME);
                return;
            }
            Ui.printCustomMessage(INPUT_NAME);
            name = Ui.read().trim();
        }
        this.name = name;
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

    public void setFitnessLevel(int userFitnessLevel) {
        this.userFitnessLevel = userFitnessLevel;
    }

    public void setupAge(String argument, Boolean isEdit) {
        int ageInput = 0;
        while (ageInput < 1 || ageInput > 130) {
            try {
                if (isEdit) {
                    ageInput = Integer.parseInt(argument.trim());
                } else {
                    ageInput = Integer.parseInt(Ui.read().trim());
                }
                if (ageInput < 1 || ageInput > 130) {
                    Ui.printCustomError(ERROR_INVALID_AGE_INPUT);
                    if (isEdit) {
                        Ui.printCustomError("FORMAT: " + FORMAT_EDIT_AGE + LINE_BREAK + RANGE_EDIT_AGE);
                        return;
                    }
                    Ui.printCustomMessage(INPUT_AGE);
                    Ui.printCustomError(RANGE_EDIT_AGE);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomError(ERROR_INVALID_AGE_INPUT);
                if (isEdit) {
                    Ui.printCustomError("FORMAT: " + FORMAT_EDIT_AGE + LINE_BREAK + RANGE_EDIT_AGE);
                    return;
                }
                Ui.printCustomError(RANGE_EDIT_AGE);
                Ui.printCustomMessage(INPUT_AGE);
                ageInput = 0;
            }
        }
        setAge(ageInput);
    }

    public void setupHeight(String argument, Boolean isEdit) {
        double heightInput = 0.00;
        // Height (in m)
        while (heightInput < 0.50 || heightInput > 4.00) {
            try {
                if (isEdit) {
                    heightInput = Double.parseDouble(String.format("%.2f", Double.parseDouble(argument.trim())));
                } else {
                    heightInput = Double.parseDouble(String.format("%.2f", Double.parseDouble(Ui.read().trim())));
                }
                if (heightInput < 0.50 || heightInput > 4.00) {
                    Ui.printCustomError(ERROR_INVALID_HEIGHT_INPUT);
                    if (isEdit) {
                        Ui.printCustomError("FORMAT: " + FORMAT_EDIT_HEIGHT + LINE_BREAK + RANGE_EDIT_HEIGHT);
                        return;
                    }
                    Ui.printCustomError(RANGE_EDIT_HEIGHT);
                    Ui.printCustomMessage(INPUT_HEIGHT);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomError(ERROR_INVALID_HEIGHT_INPUT);
                if (isEdit) {
                    Ui.printCustomError("FORMAT: " + FORMAT_EDIT_HEIGHT + LINE_BREAK + RANGE_EDIT_HEIGHT);
                    return;
                }
                Ui.printCustomError(RANGE_EDIT_HEIGHT);
                Ui.printCustomMessage(INPUT_HEIGHT);
                heightInput = 0.00;
                if (isEdit) {
                    return;
                }
            }
        }
        setHeight(heightInput);
    }

    public void setupWeight(String argument, Boolean isEdit) {
        // Weight (in kg)
        double weightInput = 0.00;
        while (weightInput < 2.00 || weightInput > 1000.00) {
            try {
                if (isEdit) {
                    weightInput = Double.parseDouble(String.format("%.2f", Double.parseDouble(argument.trim())));
                } else {
                    weightInput = Double.parseDouble(String.format("%.2f", Double.parseDouble(Ui.read().trim())));
                }
                if (weightInput < 2.00 || weightInput > 1000.00) {
                    Ui.printCustomError(ERROR_INVALID_WEIGHT_INPUT);
                    if (isEdit) {
                        Ui.printCustomError("FORMAT: " + FORMAT_EDIT_WEIGHT + LINE_BREAK + RANGE_EDIT_WEIGHT);
                        return;
                    }
                    Ui.printCustomError(RANGE_EDIT_WEIGHT);
                    Ui.printCustomMessage(INPUT_WEIGHT);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomError(ERROR_INVALID_WEIGHT_INPUT);
                if (isEdit) {
                    Ui.printCustomError("FORMAT: " + FORMAT_EDIT_WEIGHT + LINE_BREAK + RANGE_EDIT_WEIGHT);
                    return;
                }
                Ui.printCustomError(RANGE_EDIT_WEIGHT);
                Ui.printCustomMessage(INPUT_WEIGHT);
                weightInput = 0.00;
            }
        }
        setWeight(weightInput);
    }

    public void setupGender(String argument, Boolean isEdit) {
        String genderInput;
        if (isEdit) {
            genderInput = argument.trim();
        } else {
            genderInput = Ui.read().trim();
        }
        while (!genderInput.equalsIgnoreCase("m") && !genderInput.equalsIgnoreCase("f")) {
            Ui.printCustomError(ERROR_INVALID_GENDER_INPUT);
            if (isEdit) {
                Ui.printCustomError("FORMAT: " + FORMAT_EDIT_GENDER);
                return;
            }
            Ui.printCustomMessage(INPUT_GENDER);
            genderInput = Ui.read().trim();
        }
        if (genderInput.equalsIgnoreCase(MALE_SYMBOL)) {
            setGender(MALE_STRING);
        } else if (genderInput.equalsIgnoreCase(FEMALE_SYMBOL)) {
            setGender(FEMALE_STRING);
        }
    }

    public void setupFitnessLevel(String argument, Boolean isEdit) {
        int fitnessLevelInput = -1;
        String input = null;
        while (fitnessLevelInput != 0 && fitnessLevelInput != 1 && fitnessLevelInput != 2) {
            try {
                if (isEdit) {
                    // Only single digit input allowed
                    if (argument.trim().length() == 1) {
                        fitnessLevelInput = Integer.parseInt(argument.trim());
                    }
                } else {
                    input = Ui.read().trim();
                    // Only single digit input allowed
                    if (input.length() == 1) {
                        fitnessLevelInput = Integer.parseInt(input);
                    }
                }
                if (fitnessLevelInput != 0 && fitnessLevelInput != 1 && fitnessLevelInput != 2) {
                    Ui.printCustomError(ERROR_INVALID_FITNESS_INPUT);
                    if (isEdit) {
                        Ui.printCustomError("FORMAT: " + FORMAT_EDIT_FITNESS);
                        return;
                    }
                    Ui.printCustomMessage(INPUT_FITNESS_LEVEL);
                }
            } catch (NumberFormatException e) {
                Ui.printCustomError(ERROR_INVALID_FITNESS_INPUT);
                if (isEdit) {
                    Ui.printCustomError("FORMAT: " + FORMAT_EDIT_FITNESS);
                    return;
                }
                Ui.printCustomMessage(INPUT_FITNESS_LEVEL);
                fitnessLevelInput = -1;
            }
        }
        setFitnessLevel(fitnessLevelInput);
    }

    // Returns user profile
    @Override
    public String toString() {
        return NAME_OUTPUT_HEADER + getName() + LINE_BREAK + AGE_OUTPUT_HEADER + getAge() + LINE_BREAK
                + GENDER_OUTPUT_HEADER + getGender() + LINE_BREAK + HEIGHT_OUTPUT_HEADER
                + String.format("%.2f", getHeight()) + LINE_BREAK + WEIGHT_OUTPUT_HEADER
                + String.format("%.2f", getWeight()) + LINE_BREAK + FITNESS_OUTPUT_HEADER
                + getUserFitnessLevelString();
    }

    public Calorie calculateCalorieBurnt(ExerciseList exerciseList, String date) {
        int totalCalorieBurnt = 0;
        ExerciseList exerciseListByDate = ViewCommand.viewExerciseByDate(exerciseList, date, false);
        int index = 0;
        while (index < exerciseListByDate.getSize()) {
            totalCalorieBurnt += exerciseListByDate.getExercise(index).getCalories();
            index++;
        }
        return new Calorie(totalCalorieBurnt);
    }

    public Calorie calculateCalorieConsumed(FoodList foodList, String date) {
        int totalCalorieConsumed = 0;
        FoodList foodListByDate = ViewCommand.viewFoodByDate(foodList, date, false);
        int index = 0;
        while (index < foodListByDate.getSize()) {
            totalCalorieConsumed += foodListByDate.getFood(index).getCalories();
            index++;
        }
        return new Calorie(totalCalorieConsumed);
    }

    public Calorie calculateCalorie(FoodList foodList, ExerciseList exerciseList, String date) {
        int totalCalories;
        Calorie totalConsumed = calculateCalorieConsumed(foodList, date);
        Calorie totalBurnt = calculateCalorieBurnt(exerciseList, date);
        totalCalories = totalConsumed.get() - totalBurnt.get();
        return new Calorie(totalCalories);
    }

    public double getBmi() {
        return weight / ((height) * (height));
    }

    public int getFitnessLevel() {
        return userFitnessLevel;
    }

    public String getUserFitnessLevelString() {
        if (userFitnessLevel == 0) {
            return UNFIT_STRING;
        } else if (userFitnessLevel == 1) {
            return NORMAL_STRING;
        } else if (userFitnessLevel == 2) {
            return FIT_STRING;
        } else {
            return NULL_STRING;
        }
    }
}
