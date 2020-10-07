package seedu.duke.task;

public enum Priority {
    HIGH {
        public String toString() {
            return "high priority";
        }
    },

    MEDIUM {
        public String toString() {
            return "medium priority";
        }
    },

    LOW {
        public String toString() {
            return "low priority";
        }
    }
}
