package seedu.dietbook.list;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Utility class to do regex matching and substitution in a format similar to Python's fstrings.
 */
public class StringFormatter {
    private static final Pattern MATCH_TEMPLATE = 
            Pattern.compile("\\$\\{([a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*)\\}");
    
    /**
     * Formats the string based on (variable name, variable) pairs provided in map.
     * Uses the regex pattern ${variable_name} to detect points in the string to substitute.
     * @param string string containing regex pattern to be formatted
     * @param map map of (String variable name, variable values) for substitution.
     * @return formatted string
     */
    public static <E extends Object> String formatStringWithMap(String string, Map<String, E> map) 
            throws NoReplacementFoundException {
        Matcher matcher = MATCH_TEMPLATE.matcher(string);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            E value = map.get(matcher.group(1));
            if (value == null) {
                throw new NoReplacementFoundException("For key of: " + matcher.group(1));
            }
            matcher.appendReplacement(buffer, value.toString());

        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }
}
