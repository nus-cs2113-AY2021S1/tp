package fitr;

import java.util.ArrayList;

public class StandardExercise {
    protected ArrayList<Integer> quantity;
    protected ArrayList<Integer> sets;
    protected double caloricBurnRate;
    protected String name;

    public StandardExercise(String name, double caloricBurnRate,ArrayList quantity,ArrayList sets) {
        this.name = name;
        this.caloricBurnRate = caloricBurnRate;
        this.quantity = quantity;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public double getCaloricBurnRate() {
        return caloricBurnRate;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public ArrayList<Integer> getSets() {
        return sets;
    }

    public String toString(int fitnessLevel) {
        return "Temp";
    }

}
