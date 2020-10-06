package seedu.duke.food;

public class Food {
    private final String name;
    private final int calorie;
    private final int carbohydrate;
    private final int protein;

    public Food(String name, int calorie, int carbohydrate, int protein){
        this.name = name;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
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
}
