package fitr;

import java.util.ArrayList;

public class StandardExercise {
    protected ArrayList<Double> duration;
    protected ArrayList<Integer> sets;
    protected double MET;
    protected String name;

    public StandardExercise(String name, double MET, ArrayList<Double> duration, ArrayList<Integer> sets) {
        this.name = name;
        this.MET = MET;
        this.duration = duration;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public double getMET() {
        return MET;
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
