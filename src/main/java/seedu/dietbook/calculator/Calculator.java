package seedu.dietbook.calculator;

import seedu.dietbook.food.Food;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;

import java.time.LocalDateTime;
import java.util.List;

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
    public Calculator(List<Food> foodList) {
        assert foodList != null : "The foodList should not be null.";

        for (int i = 0; i < foodList.size(); i++) {
            assert foodList.get(i).getName().trim().length() != 0 : "Food names should not be empty.";

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
     * Returns an int type variable containing the value of total calorie
     * of the foods with time after a specific time.
     *
     * @param startTime the start time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateCalorie(LocalDateTime startTime) {
        int calorie = 0;
        for (int i = 0; i < FoodList.getFoodsAfterDateTime(startTime).size(); i++) {
            calorie += FoodList.getFoodsAfterDateTime(startTime).get(i).getCalorie();
        }
        return calorie;
    }

    /**
     * Returns an int type variable containing the value of total calories
     * of the foods with time after a specific time and before a specific time.
     *
     * @param startTime the start time for food items to be included.
     * @param endTime the end time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateCalorie(LocalDateTime startTime, LocalDateTime endTime) {
        int calorie = 0;
        for (int i = 0; i < FoodList.getFoodsInDateTimeRange(startTime, endTime).size(); i++) {
            calorie += FoodList.getFoodsInDateTimeRange(startTime, endTime).get(i).getCalorie();
        }
        return calorie;
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
     * Returns an int type variable containing the value of total carbs
     * of the foods with time after a specific time.
     *
     * @param startTime the start time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateCarb(LocalDateTime startTime) {
        int carb = 0;
        for (int i = 0; i < FoodList.getFoodsAfterDateTime(startTime).size(); i++) {
            carb += FoodList.getFoodsAfterDateTime(startTime).get(i).getCarbohydrate();
        }
        return carb;
    }

    /**
     * Returns an int type variable containing the value of total carbs
     * of the foods with time after a specific time and before a specific time.
     *
     * @param startTime the start time for food items to be included.
     * @param endTime the end time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateCarb(LocalDateTime startTime, LocalDateTime endTime) {
        int carb = 0;
        for (int i = 0; i < FoodList.getFoodsInDateTimeRange(startTime, endTime).size(); i++) {
            carb += FoodList.getFoodsInDateTimeRange(startTime, endTime).get(i).getCarbohydrate();
        }
        return carb;
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
     * Returns an int type variable containing the value of total protein
     * of the foods with time after a specific time.
     *
     * @param startTime the start time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateProtein(LocalDateTime startTime) {
        int protein = 0;
        for (int i = 0; i < FoodList.getFoodsAfterDateTime(startTime).size(); i++) {
            protein += FoodList.getFoodsAfterDateTime(startTime).get(i).getProtein();
        }
        return protein;
    }

    /**
     * Returns an int type variable containing the value of total protein
     * of the foods with time after a specific time and before a specific time.
     *
     * @param startTime the start time for food items to be included.
     * @param endTime the end time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateProtein(LocalDateTime startTime, LocalDateTime endTime) {
        int protein = 0;
        for (int i = 0; i < FoodList.getFoodsInDateTimeRange(startTime, endTime).size(); i++) {
            protein += FoodList.getFoodsInDateTimeRange(startTime, endTime).get(i).getProtein();
        }
        return protein;
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
     * Returns an int type variable containing the value of total fats
     * of the foods with time after a specific time.
     *
     * @param startTime the start time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateFat(LocalDateTime startTime) {
        int fat = 0;
        for (int i = 0; i < FoodList.getFoodsAfterDateTime(startTime).size(); i++) {
            fat += FoodList.getFoodsAfterDateTime(startTime).get(i).getFats();
        }
        return fat;
    }

    /**
     * Returns an int type variable containing the value of total fats
     * of the foods with time after a specific time and before a specific time.
     *
     * @param startTime the start time for food items to be included.
     * @param endTime the end time for food items to be included.
     *
     * @return the value of total calorie of food items with time after
     *         startTime in foodList.
     */
    public int calculateFat(LocalDateTime startTime, LocalDateTime endTime) {
        int fat = 0;
        for (int i = 0; i < FoodList.getFoodsInDateTimeRange(startTime, endTime).size(); i++) {
            fat += FoodList.getFoodsInDateTimeRange(startTime, endTime).get(i).getFats();
        }
        return fat;
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
            } else if (person.getGender() == Gender.FEMALE) {
                activityScore = 1.12;
            } else {
                activityScore = 1.115;
            }
            break;
        case MEDIUM:
            if (person.getGender() == Gender.MALE) {
                activityScore = 1.26;
            } else if (person.getGender() == Gender.FEMALE) {
                activityScore = 1.27;
            } else {
                activityScore = 1.265;
            }
            break;
        case HIGH:
        default:
            if (person.getGender() == Gender.MALE) {
                activityScore = 1.48;
            } else if (person.getGender() == Gender.FEMALE) {
                activityScore = 1.45;
            } else {
                activityScore = 1.465;
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
            requirement = 508 - 8.22 * person.getAge() + 12.635 * activityScore * person.getOriginalWeight()
                    + 632.8 * person.getHeight() / 100;
        }

        if (person.getOriginalWeight() > person.getTargetWeight()) {
            recomendation = (int) requirement - 300;
        } else {
            recomendation = (int) requirement + 100;
        }
        return recomendation;
    }
}
