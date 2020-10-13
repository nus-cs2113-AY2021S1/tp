package fitr.list;

import fitr.Exercise;

import java.util.ArrayList;

public class ExerciseList {
    private final ArrayList<Exercise> exerciseList;

    public ExerciseList() {
        this(new ArrayList<>());
    }

    public ExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public Exercise getExercise(int index) {
        return exerciseList.get(index);
    }

    public Exercise deleteExercise(int index) {
        return exerciseList.remove(index);
    }

    public int getSize() {
        return exerciseList.size();
    }

    public void clearList() {
        exerciseList.clear();
    }
}
