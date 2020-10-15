package workout;

public class AerobicTrainingRecord extends WorkOutRecord {

    int duration;

    AerobicTrainingRecord(BodyPart bp, String name, int duration) {
        super(bp, name);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString() + "";
    }
}
