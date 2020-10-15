package workout;

public class StrengthTrainingRecod extends WorkOutRecord {

    double weight;
    double repetitions;

    StrengthTrainingRecod(BodyPart bp, String moveName, double weight, double reps) {
        super(bp, moveName);
        this.weight = weight;
        this.repetitions = reps;
    }

    @Override
    public String toString() {
        String content = super.toString();
        content += String.format("%.2fkg   %d repetitions", weight, repetitions);
        return content;
    }
}
