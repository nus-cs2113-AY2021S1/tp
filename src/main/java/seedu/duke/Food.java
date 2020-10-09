package seedu.duke;

public class Food {
    protected String nameOfFood; // The name of the Food
    protected Calorie caloriesInFood;// Amount of calories consumed
    protected int amountOfFood = 1; // Amount of food in grams, if not it will be taken as arbritary unit e.g, 1 banana
    // Caloric Rate of the food, if amount is not given we will take it as a whole unit.
    protected int caloricRate = caloriesInFood.get() / amountOfFood;

    public Food(String nameOfExercise, Calorie calories, int amount) {
        this.nameOfFood = nameOfFood;
        this.caloriesInFood = calories;
        this.amountOfFood = amount;
    }

    public Food(String name,Calorie caloriesBurnt) {
        this.nameOfFood = name;
        this.caloriesInFood = caloriesBurnt;
    }

    public int getCalories() {
        return caloriesInFood.get();
    }

    public String getFoodName() {
        return nameOfFood;
    }

    public int getCaloricRate() {
        return caloricRate;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }
}