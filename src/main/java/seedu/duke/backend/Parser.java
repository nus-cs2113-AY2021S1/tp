package seedu.duke.backend;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public Parser() {

    }

    /**
     * Given a string input, returns a sanitized and pre-processed UserInput object.
     * @param input The string to be processed
     * @return UserInput object containing the command, category and all arguments
     */
    public UserInput parse(String input) {
        String userInput;

        userInput = sanitize(input);
        // Regex looks silly, you  have to escape the / so you type double \ to escape the \ used to escape the /
        String[] output = userInput.split("\\/");
        UserInput ui;
        Map<String, String> map = new HashMap<>();
        String category = checkCategory(output);
        if (!category.equals("")) {
            // Trim is still required as indexOf ' ' may hit a blank space before the category
            // This removes the category from the command if it exists
            String tmp = output[0].trim();
            output[0] = tmp.substring(tmp.indexOf(' ') + 1);
        }
        if (output.length == 1) {
            // There are no arguments supplied
            // Check if the input has any other arguments
            String tmp = output[0].trim();
            String cmd;
            if (tmp.indexOf(' ') != -1) {
                String arg = tmp.substring(tmp.indexOf(' ') + 1);
                cmd = tmp.substring(0, tmp.indexOf(' '));
                map.put("", arg);
            } else {
                cmd = tmp;
            }
            ui = new UserInput(cmd, map);
        } else {
            String base = output[0].trim();
            if (base.indexOf(' ') != -1) {
                String arg = base.substring(base.indexOf(' ') + 1);
                base = base.substring(0, base.indexOf(' '));
                map.put("", arg);
            }
            // Convert the / arguments into a map
            for (int i = 1; i < output.length; i++) {
                String tmp = output[i].trim();
                String key;
                String val;
                if (tmp.indexOf(' ') != -1) {
                    val = tmp.substring(tmp.indexOf(' ') + 1);
                    key = tmp.substring(0, tmp.indexOf(' '));
                } else {
                    val = "";
                    key = tmp;
                }

                map.put(key.trim().toLowerCase(), val.trim());
            }
            ui = new UserInput(base, map);
        }
        ui.setCategory(category);
        return ui;

    }

    /**
     * Given any split output, finds if the output contains a string that indicates the category.
     * Shorthand categories are supported.
     * @param output The split string output
     * @return The category of the command
     */
    public String checkCategory(String[] output) {
        String tmp = output[0].trim().toLowerCase();
        if (tmp.startsWith("hr ") || tmp.startsWith("h ")) {
            return "hr";
        } else if (tmp.startsWith("finance ") || tmp.startsWith("f ")) {
            return "finance";
        } else if (tmp.startsWith("event ") || tmp.startsWith("e ")) {
            return "event";
        }
        return "";
    }

    /**
     * TODO implement string sanitization.
     * Cleans up the string to ensure that anything else is safe for the rest of the program to handle.
     * @param s String to be sanitized
     * @return Sanitized string S
     */
    public String sanitize(String s) {
        return s;
    }
}