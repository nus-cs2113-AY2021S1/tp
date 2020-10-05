package seedu.duke;

public class Calorie {
    protected int amountOfCalories;

    public Calorie(int calories){
        this.amountOfCalories = calories;
    }
    public int get(){
        return amountOfCalories;
    }
}
