package fitr;

import java.util.ArrayList;

public class StandardExercise {
    protected ArrayList<Integer> duration;
    protected ArrayList<Integer> sets;
    protected double caloricBurnRate;
    protected String name;

    public StandardExercise(String name, double caloricBurnRate,ArrayList duration,ArrayList sets) {
        this.name = name;
        this.caloricBurnRate = caloricBurnRate;
        this.duration = duration;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public double getCaloricBurnRate() {
        return caloricBurnRate;
    }

    public ArrayList<Integer> getDuration() {
        return duration;
    }

    public ArrayList<Integer> getSets() {
        return sets;
    }

    public String toString(int fitnessLevel) {
        return "Temp";
    }

}
