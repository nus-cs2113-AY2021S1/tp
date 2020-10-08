package seedu.financeit.common;

import java.util.HashMap;

public class Constants {
    public static final String[] DEFAULT_EXP_CAT = {"TRANSPORT", "FOOD", "TRAVEL", "SHOPPING", "BILLS"};
    public static final String[] DEFAULT_INC_CAT = {"ALLOWANCE", "WAGES", "BONUS"};
    public static final String[] DEFAULT_PARAMS_PREFIX = {"/", "-"};
    public static final HashMap<String, String> categoryMap = new HashMap() {
        {
            put("tpt", "TRANSPORT");
            put("fd", "FOOD");
            put("tvl", "TRAVEL");
            put("shp", "SHOPPING");
            put("bll", "BILLS");
            put("slr", "SALARY");
            put("alw", "ALLOWANCE");
        }
    };

    public static final String PLACEHOLDER_DATE = "2020-01-01";
    public static final String PLACEHOLDER_TIME = "00:00";
    public static final int MAX_PARTITION_LINE_LEN = 60;
    public static final int MAX_ARRAY_LEN = 20;

    public enum EntryType {

        EXP("Expense"),
        INC("Income");
        public String literal;
        EntryType(String string) {
            literal = string;
        }
        @Override
        public String toString(){
            return literal;
        }
    }

    public enum PrintType {
        ERROR_MESSAGE("ERROR"),
        INSTRUCTION("TODO"),
        GOAL_STATUS("GOAL STATUS"),
        DIRECTORY("DIR"),
        SYS_MSG("SYSTEM MESSAGE");
        public String literal;
        PrintType(String string) {
            literal = string;
        }
        @Override
        public String toString(){
            return literal;
        }
    }

    // Regex detects the following: <whitespace>/<word><whitespace>
    public static final String paramRegex = String.format("(\\s)([%s]{1})(\\w+)(\\s)",
            String.join("", DEFAULT_PARAMS_PREFIX));
}
