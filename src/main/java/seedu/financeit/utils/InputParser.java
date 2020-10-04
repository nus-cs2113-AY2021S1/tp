package seedu.financeit.utils;

import java.util.HashMap;
import java.util.regex.Matcher;

public class InputParser {
    protected Matcher matcher;

    public InputParser() {
    }

    public String getSeparator(String input) {
        return String.valueOf(input.charAt(matcher.start() + 1));
    }

    /**
     * Example input: deadline do homework /by tomorrow /note skip page 70.
     * commandString: "deadline"
     * CommandPacket created:
     * {
     *  commandType: ADD_DEADLINE
     *  commandContent: "do homework"
     *  params: HashMap< String, String >
     *  {
     *   "by": "tomorrow"
     *   "note": "skip page 70"
     *  }
     * }
     */
    public CommandPacket parseInput(String input) {
        String commandContent = "";
        String commandString = "";
        String restOfCommand;
        CommandPacket packet;
        HashMap<String, String> params = new HashMap<>();
        String[] buffer = null;
        String separator = "";
        boolean paramsExist = false;
        boolean commandContentExist = false;

        //Split into [command, rest of input]

        //Check for existence of command title
        //commandContentExist = buffer.length > 1 && !(buffer[1].startsWith("/") || buffer[1].startsWith("-"));
        try {
            this.matcher = RegexMatcher.regexMatcher(input, Constants.paramRegex);
            separator = this.getSeparator(input);
            System.out.println(separator);
            commandContentExist = true;

        } catch (java.lang.IllegalStateException exception) {
            System.out.println("lol");
            commandString = input;
        }
        if (commandContentExist) {
            System.out.println(matcher.start());
            buffer = input.split(separator, 2);
            //command , /a param
            System.out.println(buffer[0]);
            buffer[1] = separator + buffer[1];
            commandString = buffer[0];
            String paramSubstring = buffer[1];
            params = new ParamsParser(paramSubstring).parseParams();
        }

        return new CommandPacket(commandString, params);
    }

    /**
     * Parse strings to produce date or time depending on mode specified in arguments.
     * @param input Input by user to set date or time
     * @param mode Mode set by user, can be "date" or "time" depends on input to be parsed
     * @return Formatted date or time for parsing with the DateTime class.
     */
    public static String parseDateTime(String input, final String mode) {
        final String partition = mode.equals("time") ? ":" :
                mode.equals("date") ? "-" : "?";
        String[] output = new String[3];
        if (input.matches("[0-9]{4}") && mode.equals("time")) {
            // If input time format is XXXX, replace with XX:XX:00.
            input = input.substring(0,2) + "," + input.substring(2) + ",00";
        } else if (input.matches("[0-9]{6}") && mode.equals("date")) {
            // If input date format is XXXXXX, replace with XX,XX,XX
            input = input.substring(0,2) + "," + input.substring(2, 4) + "," + input.substring(4);
        } else if (input.matches("[0-9]{8}") && mode.equals("date")) {
            // If input date format is XXXXXX, replace with XX,XX,XX
            input = input.substring(0,2) + "," + input.substring(2, 4) + "," + input.substring(6);
        }
        // Replaces on no-digit numbers with the same string "c"
        // such that they can be manipulated easily regardless of string.
        input = input.replaceAll("[\\D]+", "c");
        String[] tokens = input.split("c");
        for (int i = 0; i < output.length; i++) {
            if (i > tokens.length - 1) {
                // If subsequent pair of digits is undefined by simplified input, set to 0.
                output[i] = "01";
            } else if (tokens[i].length() < 2) {
                // If token is 1 or 0 digits long, fill the remaining space with 0 such that a 2 digit number is formed.
                output[i] = "0" + tokens[i];
            } else if (i == 0 && tokens[i].matches("[0-9]{2}") && mode.equals("date")) {
                // If year given is YY
                output[i] = "20" + tokens[i].substring(0,2);
            } else {
                output[i] = tokens[i];
            }
        }
        return String.join(partition, output);
    }
}