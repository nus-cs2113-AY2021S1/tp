package fitr;

public class Exercise {
    protected String nameOfExercise; // The name of the Exercise
    protected Calorie caloriesBurnt;// Amount of calories burnt from exercise
    protected String date; //Date when the Exercise is added to the list

    public Exercise(String name, Calorie caloriesBurnt, String date) {
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
        return date;
    }

    public void setNameOfExercise(String nameOfExercise) {
        this.nameOfExercise = nameOfExercise;
    }

    public void setCaloriesBurnt(Calorie caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

}
