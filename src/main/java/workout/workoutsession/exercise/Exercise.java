package workout.workoutsession.exercise;

public class Exercise {

    private String moveName = null;
    private int repetitions = 0;
    private int weight = 0;

    public Exercise(String moveName, int repetitions, int weight) {

        moveName = this.moveName;
        repetitions = this.repetitions;
        weight = this.weight;
    }

    @Override
    public String toString() {

        return  moveName
                + ", Repetitions:" + repetitions
                + ", Weight=" + weight;
    }
}
