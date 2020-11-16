package seedu.financeit.utils;

import seedu.financeit.common.Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static Matcher regexMatcher(String inputString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);
        return matcher;
    }

    public static Matcher paramMatcher(String inputString) {
        //Matches <space><PARAMS_PREFIX><string><space>
        String regex = String.format("\\s+[%s]{1}[a-zA-Z]+\\s+",
                String.join("", Common.DEFAULT_PARAM_TYPE_PREFIX));
        Matcher matcher = regexMatcher(inputString, regex);
        matcher.find();
        return matcher;
    }

    public static Matcher alphabetMatcher(String inputString) {
        String regex = "[a-zA-Z]+";
        Matcher matcher = regexMatcher(inputString, regex);
        return matcher;
    }

    public static Matcher numberMatcher(String inputString) {
        String regex = "[0-9]+";
        Matcher matcher = regexMatcher(inputString, regex);
        return matcher;
    }
}
