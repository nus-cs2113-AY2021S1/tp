package seedu.dietbook.calculator;

import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a calculator of food items in foodList.
 */
public class Calculator {
    private int totalCalorie = 0;
    private int totalCarb = 0;
    private int totalProtein = 0;
    private int totalFat = 0;
    CalculatorData data = null;

    public Calculator() {
    }
    
    /**
     * Construct a calculator taking in a foodList. Add up calories,
     * carbs, protein, and fats in each food item.
     *
     * @param data a CalculatorData class instance containing data
     *             of food items to calculate.
     */
    public Calculator(CalculatorData data) {
        assert data != null : "The foodList should not be null.";
        this.data = data;
    }

    public void update(CalculatorData data) {
        this.data = data;
    }

    /**
     * Returns an int type variable containing the value of total calorie.
     *
     * @return the value of total calorie of food items in foodList.
     */
    public int calculateCalorie() {
        totalCalorie = 0;
        List<Integer> calories = data.getTotalCalorie();
        for (int calorie : calories) {
            totalCalorie += calorie;
        }
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
        totalCalorie = 0;
        List<Integer> calories = data.getTotalCalorie(startTime);
        for (int calorie : calories) {
            totalCalorie += calorie;
        }
        return totalCalorie;
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
        totalCalorie = 0;
        List<Integer> calories = data.getTotalCalorie(startTime, endTime);
        for (int calorie : calories) {
            totalCalorie += calorie;
        }
        return totalCalorie;
    }

    /**
     * Returns an int type variable containing the value of total carbs.
     *
     * @return the value of total carbs of food items in foodList.
     */
    public int calculateCarb() {
        totalCarb = 0;
        List<Integer> carbs = data.getTotalCarb();
        for (int carb : carbs) {
            totalCarb += carb;
        }
        return totalCarb;
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
        totalCarb = 0;
        List<Integer> carbs = data.getTotalCarb(startTime);
        for (int carb : carbs) {
            totalCarb += carb;
        }
        return totalCarb;
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
        totalCarb = 0;
        List<Integer> carbs = data.getTotalCarb(startTime, endTime);
        for (int carb : carbs) {
            totalCarb += carb;
        }
        return totalCarb;
    }

    /**
     * Returns an int type variable containing the value of total protein.
     *
     * @return the value of total protein of food items in foodList.
     */
    public int calculateProtein() {
        totalProtein = 0;
        List<Integer> proteins = data.getTotalProtein();
        for (int protein : proteins) {
            totalProtein += protein;
        }
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
        totalProtein = 0;
        List<Integer> proteins = data.getTotalProtein(startTime);
        for (int protein : proteins) {
            totalProtein += protein;
        }
        return totalProtein;
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
        totalProtein = 0;
        List<Integer> proteins = data.getTotalProtein(startTime, endTime);
        for (int protein : proteins) {
            totalProtein += protein;
        }
        return totalProtein;
    }

    /**
     * Returns an int type variable containing the value of total fats.
     *
     * @return the value of total fats of food items in foodList.
     */
    public int calculateFat() {
        totalFat = 0;
        List<Integer> fats = data.getTotalFat();
        for (int fat : fats) {
            totalFat += fat;
        }
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
        totalFat = 0;
        List<Integer> fats = data.getTotalFat(startTime);
        for (int fat : fats) {
            totalFat += fat;
        }
        return totalFat;
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
        totalFat = 0;
        List<Integer> fats = data.getTotalFat(startTime, endTime);
        for (int fat : fats) {
            totalFat += fat;
        }
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
    public int calculateRecommendation(Person person) {
        double requirement = 0;
        int recommendation;
        double fitnessScore;
        fitnessScore = getFitnessScore(person);
        switch (person.getGender()) {
        case MALE:
            requirement = 662 - 9.53 * person.getAge() + 15.91 * fitnessScore * person.getCurrentWeight()
                    + 539.6 * person.getHeight() / 100;
            break;
        case FEMALE:
            requirement = 354 - 6.91 * person.getAge() + 9.36 * fitnessScore * person.getCurrentWeight()
                    + 726 * person.getHeight() / 100;
            break;
        case OTHERS:
            requirement = 508 - 8.22 * person.getAge() + 12.635 * fitnessScore * person.getCurrentWeight()
                    + 632.8 * person.getHeight() / 100;
            break;
        default:
            assert requirement != 0 : "The requirement should not be 0 if the gender is "
                    + "one of the three given cases.";
        }
        if (person.getCurrentWeight() > person.getTargetWeight()) {
            recommendation = (int) requirement - 300;
        } else {
            recommendation = (int) requirement + 100;
        }
        recommendation = checkCaps(recommendation);
        return recommendation;
    }

    private double getFitnessScore(Person person) {
        double fitnessScore = 0;
        switch (person.getFitnessLevel()) {
        case NONE:
            fitnessScore = 1;
            break;
        case LOW:
            if (person.getGender() == Gender.MALE) {
                fitnessScore = 1.11;
            } else if (person.getGender() == Gender.FEMALE) {
                fitnessScore = 1.12;
            } else {
                fitnessScore = 1.115;
            }
            break;
        case MEDIUM:
            if (person.getGender() == Gender.MALE) {
                fitnessScore = 1.26;
            } else if (person.getGender() == Gender.FEMALE) {
                fitnessScore = 1.27;
            } else {
                fitnessScore = 1.265;
            }
            break;
        case HIGH:
            if (person.getGender() == Gender.MALE) {
                fitnessScore = 1.37;
            } else if (person.getGender() == Gender.FEMALE) {
                fitnessScore = 1.36;
            } else {
                fitnessScore = 1.365;
            }
            break;
        case EXTREME:
            if (person.getGender() == Gender.MALE) {
                fitnessScore = 1.48;
            } else if (person.getGender() == Gender.FEMALE) {
                fitnessScore = 1.45;
            } else {
                fitnessScore = 1.465;
            }
            break;
        default:
            assert fitnessScore != 0 : "The activityScore should not be 0 if"
                    + "the activityLevel are one of five given cases.";
        }
        return fitnessScore;
    }

    private int checkCaps(int recommendation) {
        if (recommendation < 1000) {
            recommendation = 1000;
        } else if (recommendation > 20000) {
            recommendation = 20000;
        }
        return recommendation;
    }
}
