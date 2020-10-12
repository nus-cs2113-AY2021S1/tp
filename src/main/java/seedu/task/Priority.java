package seedu.task;

public enum Priority {
    LOW(0, "LOW"),
    MEDIUM(1, "MEDIUM"),
    HIGH(2, "HIGH");

    private final int priority;
    private final String string;

    Priority(int priority, String string) {
        this.priority = priority;
        this.string = string;

    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return string;
    }
}
