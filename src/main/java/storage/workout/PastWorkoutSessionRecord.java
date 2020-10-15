package storage.workout;

import java.time.LocalDateTime;

public class PastWorkoutSessionRecord implements Comparable<PastWorkoutSessionRecord> {

    private final String filePath;
    private final LocalDateTime creationTime;
    private LocalDateTime lastEditTime;

    public PastWorkoutSessionRecord(String filePath) {
        this.filePath = filePath;
        creationTime = LocalDateTime.now();
        lastEditTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Session created on " + creationTime.toLocalDate()
                + ", last editted on " + lastEditTime.toLocalDate();
    }

    @Override
    public int compareTo(PastWorkoutSessionRecord o) {
        if (creationTime.isBefore(o.creationTime)) {
            return -1;
        }
        return 1;
    }
}
