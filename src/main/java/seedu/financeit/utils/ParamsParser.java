package seedu.financeit.utils;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ParamsParser {
    protected String paramSubstring;
    protected Matcher matcher;
    public ParamsParser(String paramSubstring) {
        this.paramSubstring = paramSubstring;
    }

    /**
     * Example input: deadline do homework /by tomorrow /note skip page 70
     * --First iteration--
     * paramSubstring: "by tomorrow /note skip page 70"
     * paramType: "by"
     * paramArgument: "tomorrow"
     * --Next iteration--
     * paramSubstring: "note skip page 70"
     * paramType: "note"
     * paramArgument: "skip page 70"
     * etc.
     */
    public HashMap<String, String> parseParams() {
        HashMap<String, String> params = new HashMap<>();
        String[] buffer;
        String paramArgument = "";

        do {
            paramSubstring += " ";
            paramArgument = "";
            //Separate into [paramType, rest of string]
            buffer = paramSubstring.split(" ", 2);
            String paramType = buffer[0];
            System.out.println("paramt " + paramType);
            boolean paramArgumentExist = buffer.length > 1;
            paramSubstring = buffer[1] + " ";
            matcher = RegexMatcher.regexMatcher(paramSubstring, Constants.paramRegex);
            //Separate into [paramArgument, rest of string]
            System.out.println("paramsub " + paramSubstring);

            try {
                if (matcher.start() != 0) {
                    String separator = String.valueOf(paramSubstring.charAt(matcher.start()));
                    buffer = paramSubstring.split(separator, 2);
                    buffer[1] = separator + buffer[1];
                    paramArgument = buffer[0];
                    paramSubstring = buffer[1];
                }
                params.put(paramType, paramArgument);
                System.out.println(params);
            } catch (java.lang.IllegalStateException exception) {
                //This point is reached when there are no more params to parse.

                //If there is a param type registered earlier
                if (paramType.length() > 0) {
                    paramArgument = paramSubstring.trim();
                    params.put(paramType, paramArgument);
                }
                break;
            }
        } while(true);
        return params;
    }
}