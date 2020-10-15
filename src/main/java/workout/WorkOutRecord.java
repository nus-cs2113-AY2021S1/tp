package workout;

public class WorkOutRecord {

    BodyPart bp;
    String moveName;

    WorkOutRecord(BodyPart bp, String moveName) {
        this.bp = bp;
        this.moveName = moveName;
    }

    @Override
    public String toString() {
        return bp.name() + ": " + moveName;
    }



}
