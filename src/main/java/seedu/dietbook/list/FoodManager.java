package seedu.dietbook.list;

import seedu.dietbook.food.Food;

public class FoodManager {
    /**
     * Static constructor method to create the Food objects required by FoodEntry.
     * It decides if there are empty values provided and creates either an OptionalFood
     * or simply the default food object.
     * @param name name of food item
     * @param calorie int value for calorie content
     * @param carbohydrate int value for carbohydrate content
     * @param protein int value for protein content
     * @param fat int value for fat content
     * @return Food object: could be instance of Food or OptionalFood (child).
     */
    protected static Food createFood(String name, int calorie, 
            int carbohydrate, int protein, int fat) {
        if (calorie == OptionalFood.EMPTY_VALUE || carbohydrate == OptionalFood.EMPTY_VALUE
                || calorie == OptionalFood.EMPTY_VALUE
                || fat == OptionalFood.EMPTY_VALUE) {
            return new OptionalFood(name, calorie, carbohydrate, protein, fat);
        }
        return new Food(name, calorie, carbohydrate, protein, fat);
    }

    /**
     * Returns a new Food object with no missing values.
     * Instances of OptionalFood are converted into Food with recalculated values for use by other classes.
     * @param food instance of Food or OptionalFood
     * @return Food object with no missing values.
     */
    protected static Food retrieveFood(Food food) {
        if (! (food instanceof OptionalFood)) {
            return food;
        }
        // Find out which parameters are missing. 
        // Only 4 scenarios: Calorie missing. Other 3 Nutrients missing. 2 Missing. 1 Missing.
        
        int calorie = food.getCalorie();
        int carbohydrate = food.getCarbohydrate();
        int protein = food.getProtein();
        int fat = food.getFat();

        // Calorie missing:
        if (calorie == OptionalFood.EMPTY_VALUE) {
            assert (carbohydrate != OptionalFood.EMPTY_VALUE) : "Carbohydrate cannot be empty when calorie is empty";
            assert (protein != OptionalFood.EMPTY_VALUE) : "Protein cannot be empty when calorie is empty";
            assert (fat != OptionalFood.EMPTY_VALUE) : "Fat cannot be empty when calorie is empty";
            
            // calculate calories:
            calorie = NutritionCalculator.calculateCalorieFromNutrients(carbohydrate, protein, fat);
            return new Food(food.getName(), calorie, carbohydrate, protein, fat);
        }

        // Other values missing instead:
        // Recalculate missing parameters based on known parameters.
        NutrientData data = NutritionCalculator.calculateNutrientsFromCalorie(calorie, carbohydrate, protein, fat);
    
        return new Food(food.getName(), data.calorie, data.carbohydrate, data.protein, data.fat);

    }

}
