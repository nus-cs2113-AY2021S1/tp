package fitr.list;

import fitr.exercise.StandardExercise;

import java.util.ArrayList;

public class StandardExerciseList {
    private final ArrayList<StandardExercise> standardExerciseList;

    public StandardExerciseList() {
        this(new ArrayList<>());
    }

    public StandardExerciseList(ArrayList<StandardExercise> exerciseList) {
        this.standardExerciseList = exerciseList;
    }

    public void addExercise(StandardExercise exercise) {
        standardExerciseList.add(exercise);
    }

    public StandardExercise getExercise(int index) {
        return standardExerciseList.get(index);
    }

    public int getSize() {
        return standardExerciseList.size();
    }
}
