package seedu.duke.food;


public class Food {
    private final String name;
    private final int calorie;
    private final int carbohydrate;
    private final int protein;
    private final int fats;

    /**
     * Constructor of the Food class
     * Store information regarding a food item: name, number of calories in kcal, amount of carbohydrate in grams,
     * amount of protein in grams, amount of fats in grams.
     * @param name name of food e.g. chicken rice
     * @param calorie number of calories e.g. 480 kcal
     * @param carbohydrate amount of carbohydrates e.g. 40 grams
     * @param protein amount of protein e.g. 20 grams
     * @param fats amount of fats e.g. 20 grams
     */
    public Food(String name, int calorie, int carbohydrate, int protein, int fats) {
        this.name = name;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fats = fats;
    }

    public int getFats() {
        return fats;
    }

    public String getName() {
        return name;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public int getProtein() {
        return protein;
    }

    @Override
    public String toString() {
        return name + " | calorie : " + calorie + " | protein : " + protein + " | carbohydrate : " + carbohydrate
                + " | fats : " + fats;
    }
}
