package fitr;

public class Food {
    protected String nameOfFood; // The name of the Food
    protected Calorie caloriesInFood;// Amount of calories consumed
    protected int amountOfFood = 1; // Amount of food in grams, if not it will be taken as arbitrary unit e.g, 1 banana
    protected String date; //Date when the Food is added to the list

    public Food(String nameOfFood, Calorie calories, int amount, String date) {
        this.nameOfFood = nameOfFood;
        this.amountOfFood = amount;
        this.caloriesInFood = new Calorie(calories.get());
        this.date = date;
    }

    public Food(String name, Calorie caloriesInFood, String date) {
        this.nameOfFood = name;
        this.caloriesInFood = caloriesInFood;
        this.date = date;
    }

    public int getCalories() {
        return caloriesInFood.get();
    }

    public String getFoodName() {
        return nameOfFood;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public String getDate() {
        return date;
    }

    public void setNameOfFood(String nameOfFood) {
        this.nameOfFood = nameOfFood;
    }

    public void setCaloriesInFood(Calorie caloriesInFood) {
        this.caloriesInFood = caloriesInFood;
    }

    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }
}
