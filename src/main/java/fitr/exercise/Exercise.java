package fitr.exercise;

import fitr.calorie.Calorie;
import fitr.common.DateManager;

import java.time.LocalDate;

public class Exercise {
    protected String nameOfExercise; // The name of the Exercise
    protected Calorie caloriesBurnt;// Amount of calories burnt from exercise
    protected LocalDate date; //Date when the Exercise is added to the list

    public Exercise(String name, Calorie caloriesBurnt, LocalDate date) {
        this.nameOfExercise = name;
        this.caloriesBurnt = caloriesBurnt;
        this.date = date;
    }

    public int getCalories() {
        return caloriesBurnt.get();
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

    public String getDate() {
        return date.format(DateManager.formatter);
    }

    public void setNameOfExercise(String nameOfExercise) {
        this.nameOfExercise = nameOfExercise;
    }

    public void setCaloriesBurnt(Calorie caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

}
