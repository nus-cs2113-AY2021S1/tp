package seedu.duke.userprofile;

import java.text.DecimalFormat;

//@@author jlifah
/**
 * Initialises user profile.
 */
public class InitialiseUserProfile {
    protected String name;
    protected String gender;
    protected String weight;
    protected String height;
    protected String age;
    protected String activityLevel;
    protected String weightGoal;
    protected double calories;
    private static final Integer DIFFERENCE_CALORIES = 500;

    public InitialiseUserProfile(String name, String gender, String weight, String height,
                                 String age, String activityLevel, String weightGoal) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.activityLevel = activityLevel;
        this.weightGoal = weightGoal;
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets gender.
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets weight.
     *
     * @return weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Gets height.
     *
     * @return height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Gets age.
     *
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * Gets activity level.
     *
     * @return activityLevel
     */
    public String getActivityLevel() {
        return activityLevel;
    }

    /**
     * Gets weight goal.
     *
     * @return weight goal
     */
    public String getWeightGoal() {
        return weightGoal;
    }

    /**
     * Gets calories.
     *
     * @return calories
     */
    public double getCalories() {
        return calories;
    }

    private static final DecimalFormat df2 = new DecimalFormat("#.00");

    /**
     * Calculates edited user bmi, recommended calories and actual calories needed.
     *
     */
    public void calculateEditedUserDetails() {
        double activityMultiple = calculateActivityMultiple(this.activityLevel);
        double bmi = calculateBmi(this.weight, this.height);
        assert bmi > 0 : "bmi should be more than 0";

        calories = calculateCalories(this.gender, this.weight, this.height, this.age) * activityMultiple;
        assert calories > 0 : "calories should be greater than 0";
    }

    /**
     * Calculates user bmi, recommended calories and actual calories needed.
     *
     */
    public String calculateNewUserDetails() {
        double activityMultiple = calculateActivityMultiple(this.activityLevel);

        String details = "";
        double bmi = calculateBmi(this.weight, this.height);
        assert bmi > 0 : "bmi should be more than 0";

        details += "\nYour BMI is " + df2.format(bmi) + "\n";

        calories = calculateCalories(this.gender, this.weight, this.height, this.age) * activityMultiple;
        assert calories > 0 : "calories should be greater than 0";

        details += "Your recommend daily calories intake is " + df2.format(calories) + " calories." + "\n";

        if (this.getWeightGoal().equals("lose")) {
            calories -= DIFFERENCE_CALORIES;
            details += "To " + this.getWeightGoal() + " weight, you should consume "
                    + df2.format(calories) + " calories instead.\n";
        } else if (this.getWeightGoal().equals("gain")) {
            calories += DIFFERENCE_CALORIES;
            details += "To " + this.getWeightGoal() + " weight, you should consume "
                    + df2.format(calories) + " calories instead.\n";
        }
        return details;
    }

    /**
     * Calculates activity multiple from activity level.
     *
     * @param activityLevel how active is the user from 1 to 5
     * @return activity multiple
     */
    public double calculateActivityMultiple(String activityLevel) {
        switch (Integer.parseInt(activityLevel)) {
        case 1:
            return 1.2;
        case 2:
            return 1.375;
        case 3:
            return 1.55;
        case 4:
            return 1.725;
        case 5:
            return 1.9;
        default:
        }
        return 1;
    }

    /**
     * Calculates bmi of user.
     *
     * @param weight of user
     * @param height of user
     * @return bmi
     */
    public double calculateBmi(String weight, String height) {
        return (Double.parseDouble(weight)
                / (Double.parseDouble(height) * Double.parseDouble(height)))
                * 10000;
    }

    /**
     * Calculates recommended calories.
     *
     * @param gender of user
     * @param weight of user
     * @param height of user
     * @param age of user
     * @return activity multiple
     */
    public double calculateCalories(String gender, String weight, String height, String age) {
        double v = (10 * Double.parseDouble(weight))
                + (6.25 * Double.parseDouble(height));

        if (gender.equals("female")) {
            return ((v) - (5 * Double.parseDouble(age)) - 161);
        } else {
            return (v - (5 * Double.parseDouble(age)) + 5);
        }
    }

    @Override
    public String toString() {
        return getName() + "," + getGender() + "," + getWeight() + ","
                + getHeight() + "," + getAge() + "," + getActivityLevel() + "," + getWeightGoal();
    }
}
//@@author jlifah

