package seedu.financeit.utils;

import seedu.financeit.common.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static Matcher regexMatcher(String inputString, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);
        return matcher;
    }

    public static Matcher paramMatcher(String inputString) {
        String regex = String.format("(\\s)([%s]{1})(\\w+)(\\s)",
                String.join("", Constants.DEFAULT_PARAMS_PREFIX));
        Matcher matcher = regexMatcher(inputString, regex);
        matcher.find();
        return matcher;
    }

    public static Matcher alphabetMatcher(String inputString) {
        String regex = "[a-zA-Z]+";
        Matcher matcher = regexMatcher(inputString, regex);
        return matcher;
    }
}
