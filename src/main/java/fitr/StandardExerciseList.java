package fitr;

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

    public StandardExercise deleteExercise(int index) {
        return standardExerciseList.remove(index);
    }

    public int getSize() {
        return standardExerciseList.size();
    }

    public void clearList() {
        standardExerciseList.clear();
    }
}
