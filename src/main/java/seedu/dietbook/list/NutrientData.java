package seedu.dietbook.list;

/**
 * Data class to hold nutrient info.
 */
public class NutrientData {
    protected final int calorie;
    protected final int carbohydrate;
    protected final int protein;
    protected final int fat;

    protected NutrientData(int calorie, int carbohydrate, int protein, int fat) {
        assert (calorie >= 0) : "Should not have negative calorie value";
        assert (carbohydrate >= 0) : "Should not have negative carbohydrate value";
        assert (protein >= 0) : "Should not have negative protein value";
        assert (fat >= 0) : "Should not have negative fat value";
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
    }
}
