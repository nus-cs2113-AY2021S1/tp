package fitr;

public class Exercise {
    protected String nameOfExercise; // The name of the Exercise
    protected Calorie caloriesBurnt;// Amount of calories burnt from exercise

    public Exercise(String name,Calorie caloriesBurnt) {
        this.nameOfExercise = name;
        this.caloriesBurnt = caloriesBurnt;
    }

    public int getCalories() {
        return caloriesBurnt.get();
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

}
