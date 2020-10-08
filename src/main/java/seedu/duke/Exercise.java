package seedu.duke;

public class Exercise {
    protected String nameOfExercise; // The name of the Exercise
    protected Calorie caloriesBurnt;// Amount of calories burnt from exercise
    protected int durationOfExercise = 1; // Duration of the exercises
    protected int caloricBurnRate; // Caloric Rate of the food, if amount isnt given we will take it as a whole unit.

    public Exercise(String nameOfExercise,Calorie caloriesBurnt, int durationOfExercise) {
        this.nameOfExercise = nameOfExercise;
        this.caloriesBurnt = caloriesBurnt;
        this.durationOfExercise = durationOfExercise;
        this.caloricBurnRate = caloriesBurnt.get()/durationOfExercise;
    }

    public Exercise(String name,Calorie caloriesBurnt) {
        this.nameOfExercise = name;
        this.caloriesBurnt = caloriesBurnt;
        this.caloricBurnRate = caloriesBurnt.get()/durationOfExercise;
    }

    public int getCalories() {
        return caloriesBurnt.get();
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

    public int getCaloricBurnRate() {
        return caloricBurnRate;
    }

    public int getDuration() {
        return durationOfExercise;
    }
}