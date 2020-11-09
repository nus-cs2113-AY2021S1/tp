package seedu.dietbook.list;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Collection of methods that help with guesstimating/calculating missing nutrition values.
 */
public class NutritionCalculator {
    private static final int NUM_OF_NUTRIENTS = 3;

    /**
     * Calculates the calories by summing up the weighted contributions of the individual nutrients.
     */
    protected static int calculateCalorieFromNutrients(int carbohydrate, int protein, int fat) {
        return carbohydrate * 4 + protein * 4 + fat * 9;
    }

    /**
     * Divides up the calories among the nutrients that are empty.
     */
    protected static NutrientData calculateNutrientsFromCalorie(int calorie, int carbohydrate, int protein, int fat) {
        assert (calorie != OptionalFood.EMPTY_VALUE);

        Map<Nutrient, Integer> map = new HashMap<>();

        map.put(Nutrient.CARBOHYDRATE, carbohydrate);
        map.put(Nutrient.PROTEIN, protein);
        map.put(Nutrient.FAT, fat);
        
        ArrayList<Integer> calorieCounts = new ArrayList<>();

        map.forEach((x, y) -> {
            if (y == OptionalFood.EMPTY_VALUE) {
                return;
            } else if (x.equals(Nutrient.FAT)) {
                calorieCounts.add(y * 9);
            } else {
                calorieCounts.add(y * 4);
            }
        });

        int existingCalories = 0;
        int emptyCount = NUM_OF_NUTRIENTS - calorieCounts.size();

        for (Integer calorieCount : calorieCounts) {
            existingCalories += calorieCount;
        }

        int dividedCalorie = (calorie - existingCalories) / emptyCount;

        Map.copyOf(map).forEach((x, y) -> {
            if (y != OptionalFood.EMPTY_VALUE) {
                return;
            }
            if (x.equals(Nutrient.FAT)) {
                map.put(x, dividedCalorie / 9);
                return;
            }
            map.put(x, dividedCalorie / 4);
        });
        return new NutrientData(calorie, map.get(Nutrient.CARBOHYDRATE),
                 map.get(Nutrient.PROTEIN), map.get(Nutrient.FAT));
        
    }

    private enum Nutrient {
        CARBOHYDRATE,
        PROTEIN,
        FAT
    }
}
