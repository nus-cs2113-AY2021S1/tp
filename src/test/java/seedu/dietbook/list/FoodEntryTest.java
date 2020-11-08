package seedu.dietbook.list;

import seedu.dietbook.food.Food;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for FoodEntry and dependencies.
 */
public class FoodEntryTest {
    
    @Test
    @DisplayName("Empty Calorie Test")
    void getFood_foodWithEmptyCalorie_isNotInstanceOfOptionalFood() {
        FoodEntry entry = new FoodEntry(1, "Sashimi", OptionalFood.EMPTY_VALUE, 10, 10, 10);

        // getFood should not return an optional food.
        Food foodFromEntry = entry.getFood();
        assertTrue(!(foodFromEntry instanceof OptionalFood));

        assertEquals(10 * 4 * 2 + 10 * 9, foodFromEntry.getCalorie());

        // further test that Manager does create OptionalFood:
        Food foodFromManager = FoodManager.createFood("Sashimi", OptionalFood.EMPTY_VALUE, 10, 10, 10);
        assertTrue(foodFromManager instanceof OptionalFood);

    }

    @Test
    @DisplayName("1 Empty Nutritional Values Test")
    void getNutritionalValue_foodWithEmptyNutrionalValue_remainingDividedOutValue() {
        FoodEntry entryWithNoFat = new FoodEntry(2, "Sashimi", 170, 10, 10, OptionalFood.EMPTY_VALUE);
        assertEquals(10, entryWithNoFat.getFood().getFat());

        FoodEntry entryWithNoCarb = new FoodEntry(2, "Sashimi", 170, OptionalFood.EMPTY_VALUE, 10, 10);
        assertEquals(10, entryWithNoCarb.getFood().getCarbohydrate());
    }

    @Test
    @DisplayName("3 Empty Nutritional Values Test")
    void getNutritionalValues_foodWithEmptyNutrionalValues_dividedOutNutritionalValues() {
        FoodEntry entry = new FoodEntry(2, "Balanced", 300,
                OptionalFood.EMPTY_VALUE, OptionalFood.EMPTY_VALUE, OptionalFood.EMPTY_VALUE);
        assertEquals(300 / 3 / 9, entry.getFood().getFat());
        assertEquals(300 / 3 / 4, entry.getFood().getProtein());
    }

    @Test
    @DisplayName("2 Empty Nutritional Values Test")
    void getNutrionalValue_foodWithOnlyOneNutritionalValue_dividedOutNutritionalValues() {
        FoodEntry entryWithOnlyFat = new FoodEntry(2, "FatOnly", 300,
                OptionalFood.EMPTY_VALUE, OptionalFood.EMPTY_VALUE, 10);
        assertEquals((300 - 90) / 2 / 4, entryWithOnlyFat.getFood().getCarbohydrate());
        assertEquals((300 - 90) / 2 / 4, entryWithOnlyFat.getFood().getProtein());

        FoodEntry entryWithOnlyProtein = new FoodEntry(1, "ProteinOnly", 300,
                OptionalFood.EMPTY_VALUE, 25, OptionalFood.EMPTY_VALUE);
        assertEquals(100 / 9, entryWithOnlyProtein.getFood().getFat());
        assertEquals(100 / 4, entryWithOnlyProtein.getFood().getCarbohydrate());

    }
}
