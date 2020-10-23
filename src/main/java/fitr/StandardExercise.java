package fitr;

import java.util.ArrayList;

public class StandardExercise {
    protected ArrayList<Double> duration;
    protected ArrayList<Integer> sets;
    protected double met;
    protected String name;

    public StandardExercise(String name, double met, ArrayList<Double> duration, ArrayList<Integer> sets) {
        this.name = name;
        this.met = met;
        this.duration = duration;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public double getMet() {
        return met;
    }

    public ArrayList<Double> getDuration() {
        return duration;
    }

    public ArrayList<Integer> getSets() {
        return sets;
    }

    public String toString(int fitnessLevel) {
        return "Temp";
    }

}
