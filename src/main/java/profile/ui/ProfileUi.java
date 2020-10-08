package profile.ui;

import profile.Profile;

import static profile.Constants.AGE_LOWER_BOUND;
import static profile.Constants.AGE_UPPER_BOUND;
import static profile.Constants.HEIGHT_LOWER_BOUND;
import static profile.Constants.HEIGHT_UPPER_BOUND;
import static profile.Constants.MESSAGE_FIRST_TIME;
import static profile.Constants.MESSAGE_FORMAT;
import static profile.Constants.MESSAGE_INVALID_AGE;
import static profile.Constants.MESSAGE_INVALID_HEIGHT;
import static profile.Constants.MESSAGE_INVALID_WEIGHT;
import static profile.Constants.MESSAGE_WELCOME;
import static profile.Constants.QUESTION_AGE;
import static profile.Constants.QUESTION_EXPECTED_WEIGHT;
import static profile.Constants.QUESTION_HEIGHT;
import static profile.Constants.QUESTION_NAME;
import static profile.Constants.QUESTION_WEIGHT;
import static profile.Constants.SCANNER;
import static profile.Constants.WEIGHT_LOWER_BOUND;
import static profile.Constants.WEIGHT_UPPER_BOUND;

/**
 * A class that deals with interactions with user.
 */
public class ProfileUi {

    /**
     * Shows a message to the user.
     *
     * @param result Message to be displayed.
     */
    public void showToUser(String result) {
        System.out.println(String.format(MESSAGE_FORMAT, result));
    }

    /**
     * Greets user.
     */
    public void greetUser() {
        showToUser(MESSAGE_WELCOME);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Full line entered by the user.
     */
    public String getCommand() {
        System.out.print(">>>>> ");

        String inputLine = SCANNER.nextLine();

        // Silently consume all blank lines
        while (inputLine.trim().isEmpty()) {
            System.out.print(">>>>> ");
            inputLine = SCANNER.nextLine();
        }

        return inputLine;
    }

    public Profile getFirstTimeProfile() {
        showToUser(MESSAGE_FIRST_TIME);
        return new Profile(getName(), getAge(), getHeight(), getWeight(false), getWeight(true));
    }

    /**
     * Gets user's name for first time config.
     *
     * @return User's name.
     */
    private String getName() {
        showToUser(QUESTION_NAME);
        return getCommand();
    }

    /**
     * Gets user's age for first time config.
     *
     * @return User's age.
     */
    private int getAge() {
        int age = 0;

        while (true) {
            try {
                showToUser(QUESTION_AGE);
                age = Integer.parseInt(getCommand());

                if (!checkValidAge(age)) {
                    showToUser(MESSAGE_INVALID_AGE);
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_AGE);
            }
        }
    }

    /**
     * Verifies if user's input age is in the valid range
     * (between {@link AGE_LOWER_BOUND} and {@link AGE_UPPER_BOUND} inclusive).
     *
     * @param age User's input age.
     * @return Whether input age is valid.
     */
    private boolean checkValidAge(int age) {
        return (age >= AGE_LOWER_BOUND && age <= AGE_UPPER_BOUND);
    }

    /**
     * Gets user's height for first time config.
     *
     * @return User's height.
     */
    private int getHeight() {
        int height = 0;

        while (true) {
            try {
                showToUser(QUESTION_HEIGHT);
                height = Integer.parseInt(getCommand());

                if (!checkValidHeight(height)) {
                    showToUser(MESSAGE_INVALID_HEIGHT);
                } else {
                    return height;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_HEIGHT);
            }
        }
    }

    /**
     * Verifies if user's input height is in the valid range
     * (between {@link HEIGHT_LOWER_BOUND} and {@link HEIGHT_UPPER_BOUND} inclusive).
     *
     * @param height User's input height.
     * @return Whether input height is valid.
     */
    private boolean checkValidHeight(int height) {
        return (height >= HEIGHT_LOWER_BOUND && height <= HEIGHT_UPPER_BOUND);
    }

    /**
     * Gets user's weight for first time config.
     *
     * @param isExpected If the program is getting expected or current weight of user.
     * @return User's expected or current weight.
     */
    private double getWeight(boolean isExpected) {
        String question;
        if (isExpected) {
            question = QUESTION_EXPECTED_WEIGHT;
        } else {
            question = QUESTION_WEIGHT;
        }

        double weight = 0;

        while (true) {
            try {
                showToUser(question);
                weight = Double.parseDouble(getCommand());

                if (!checkValidWeight(weight)) {
                    showToUser(MESSAGE_INVALID_WEIGHT);
                } else {
                    return weight;
                }
            } catch (NumberFormatException e) {
                showToUser(MESSAGE_INVALID_WEIGHT);
            }
        }
    }

    /**
     * Verifies if user's input weight is in the valid range
     * (between {@link WEIGHT_LOWER_BOUND} and {@link WEIGHT_UPPER_BOUND} inclusive).
     *
     * @param weight User's input weight.
     * @return Whether input weight is valid.
     */
    private boolean checkValidWeight(double weight) {
        return (weight >= WEIGHT_LOWER_BOUND && weight <= WEIGHT_UPPER_BOUND);
    }
}
