package seedu.duke.task;

public enum Priority {
    HIGH {
        public String toString() {
            return "High priority";
        }
    },

    MEDIUM {
        public String toString() {
            return "Medium priority";
        }
    },

    LOW {
        public String toString() {
            return "Low priority";
        }
    }
}
