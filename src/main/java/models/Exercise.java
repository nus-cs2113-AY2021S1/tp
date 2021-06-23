package models;

//@@author yujinyang1998
/**
 * A class that contains Exercise information.
 */
public class Exercise {

    private String moveName;
    private int repetitions;
    private double weight;

    /**
     * Constructs Exercise object.
     *
     * @param moveName Name of exercise.
     * @param repetitions Number of repetitions.
     * @param weight Weight of exercise.
     */
    public Exercise(String moveName, int repetitions, double weight) {

        this.moveName = moveName;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    /**
     * To get a string representation of Exercise.
     *
     * @return String of exercise information.
     */
    @Override
    public String toString() {

        return moveName
                + ", Repetitions:" + repetitions
                + ", Weight=" + weight;
    }

    /**
     * Gets description of exercise.
     *
     * @return Description of exercise.
     */
    public String getDescription() {
        return moveName;
    }

    /**
     * Gets number of repetition of this exercise.
     *
     * @return Number of repetition.
     */
    public String getRepetitions() {
        return Integer.toString(repetitions);
    }

    /**
     * Gets weight of this exercise.
     *
     * @return Weight of this exercise.
     */
    public String getWeight() {
        return Double.toString(weight);
    }
}
