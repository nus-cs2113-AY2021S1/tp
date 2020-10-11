package seedu.task;

import static seedu.font.Colors.ANSI_GREEN;
import static seedu.font.Colors.ANSI_RED;
import static seedu.font.Colors.ANSI_RESET;
import static seedu.font.Colors.ANSI_YELLOW;


public enum Priority {
    LOW(0, "LOW", ANSI_GREEN),
    MEDIUM(1, "MEDIUM", ANSI_YELLOW),
    HIGH(2, "HIGH", ANSI_RED);

    private final int priority;
    private final String string;
    private final String color;

    Priority(int priority, String string, String color) {
        this.priority = priority;
        this.string = string;
        this.color = color;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return color + string + ANSI_RESET;
    }
}
