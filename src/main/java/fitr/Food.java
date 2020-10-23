package fitr;

public class Food {
    protected String nameOfFood; // The name of the Food
    protected Calorie caloriesInFood;// Amount of calories consumed
    protected int amountOfFood = 1; // Amount of food in grams, if not it will be taken as arbritary unit e.g, 1 banana

    public Food(String nameOfFood,Calorie calories,int amount) {
        this.nameOfFood = nameOfFood;
        this.amountOfFood = amount;
        this.caloriesInFood = new Calorie(calories.get() * amount);
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

    public int getAmountOfFood() {
        return amountOfFood;
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
