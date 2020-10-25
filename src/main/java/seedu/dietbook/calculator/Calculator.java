package seedu.dietbook.calculator;

import seedu.dietbook.food.Food;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;

import java.util.ArrayList;

/**
 * Represents a calculator of food items in foodList.
 */
public class Calculator {
    private int totalCalorie = 0;
    private int totalCarbohydrate = 0;
    private int totalProtein = 0;
    private int totalFat = 0;

    /**
     * Construct a calculator taking in a foodList. Add up calories,
     * carbs, protein, and fats in each food item.
     *
     * @param foodList foodList containing food items to calculate.
     */
    public Calculator(ArrayList<Food> foodList) {
        assert foodList != null : "the foodList should not be null.";
        for (int i = 0; i < foodList.size(); i++) {
            totalCalorie += foodList.get(i).getCalorie();
            totalCarbohydrate += foodList.get(i).getCarbohydrate();
            totalProtein += foodList.get(i).getProtein();
            totalFat += foodList.get(i).getFats();
        }
    }

    /**
     * Returns an int type variable containing the value of total calorie.
     *
     * @return the value of total calorie of food items in foodList.
     */
    public int calculateCalorie() {
        return totalCalorie;
    }

    /**
     * Returns an int type variable containing the value of total carbs.
     *
     * @return the value of total carbs of food items in foodList.
     */
    public int calculateCarb() {
        return totalCarbohydrate;
    }

    /**
     * Returns an int type variable containing the value of total protein.
     *
     * @return the value of total protein of food items in foodList.
     */
    public int calculateProtein() {
        return totalProtein;
    }

    /**
     * Returns an int type variable containing the value of total fats.
     *
     * @return the value of total fats of food items in foodList.
     */
    public int calculateFat() {
        return totalFat;
    }

    /**
     * Returns an int type variable containing the value of recommended daily calorie intake.
     * It is calculated based on the gender, activity level, age, height, original weight,
     * and targeted weight.
     *
     * @param person person whose recommended daily calorie intake are to return.
     * @return the value of recommended daily calorie intake.
     */
    public int calculateRecomendation(Person person) {
        double requirement = 0;
        int recomendation;
        double activityScore;
        switch (person.getActivityLevel()) {
        case NONE:
            activityScore = 1;
            break;
        case LOW:
            if (person.getGender() == Gender.MALE) {
                activityScore = 1.11;
            } else {
                activityScore = 1.12;
            }
            break;
        case MEDIUM:
            if (person.getGender() == Gender.MALE) {
                activityScore = 1.26;
            } else {
                activityScore = 1.27;
            }
            break;
        case HIGH:
        default:
            if (person.getGender() == Gender.MALE) {
                activityScore = 1.48;
            } else {
                activityScore = 1.45;
            }
            break;
        }

        switch (person.getGender()) {
        case MALE:
            requirement = 662 - 9.53 * person.getAge() + 15.91 * activityScore * person.getOriginalWeight()
                    + 539.6 * person.getHeight() / 100;
            break;
        case FEMALE:
            requirement = 354 - 6.91 * person.getAge() + 9.36 * activityScore * person.getOriginalWeight()
                    + 726 * person.getHeight() / 100;
            break;
        default:
        }

        if (person.getOriginalWeight() > person.getTargetWeight()) {
            recomendation = (int) requirement - 300;
        } else {
            recomendation = (int) requirement + 100;
        }
        return recomendation;
    }
}
