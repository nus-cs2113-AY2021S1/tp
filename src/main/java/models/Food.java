package models;

//@@author zsk612
public class Food {
    protected String name;
    protected double calories;

    public Food(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public String toString() {
        return this.name + " with calories: " + this.calories;
    }

    public double getCalories() {
        return calories;
    }

    public String getName() {
        return this.name;
    }
}
