package seedu.financeit.common;

public class Common {
    public static final String[] DEFAULT_PARAM_TYPE_PREFIX = {"/", "-"};

    public static final int MAX_PARTITION_LINE_LEN = 60;
    public static final int MAX_ARRAY_LEN = 20;

    public static final String[] MONTHS_LESS_THAN_31_DAYS = {"Feb", "Apr", "Jun", "Sept", "Nov"};
    public static final String[] MONTHS_LESS_THAN_30_DAYS = {"Feb"};

    /**
     * Types of entry, limited to Income and Expense.
     */
    public enum EntryType {

        EXP("Expense"),
        INC("Income");
        public String literal;
        EntryType(String string) {
            literal = string;
        }

        @Override
        public String toString() {
            return literal;
        }
    }

    /**
     * Types of print messages on the console.
     */
    public enum PrintType {
        ERROR_MESSAGE("ERROR"),
        INSTRUCTION("TODO"),
        GOAL_STATUS("GOAL STATUS"),
        DIRECTORY("DIR"),
        SYS_MSG("SYSTEM MESSAGE"),
        REMINDER("REMINDER");
        public String literal;
        PrintType(String string) {
            literal = string;
        }

        @Override
        public String toString() {
            return literal;
        }
    }

    public static String convertVarArgsToString(String... varArgs) {
        String paramString = "";
        int count = 0;
        for (String param : varArgs) {
            paramString += param;
            if (count < varArgs.length - 1) {
                paramString += ", ";
            }
            count++;
        }
        return paramString;
    }
}
