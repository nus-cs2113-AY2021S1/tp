package seedu.financeit.parser;

import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.utils.ParamChecker;
import seedu.financeit.utils.RegexMatcher;

import java.util.HashMap;
import java.util.regex.Matcher;

//@@author Artemis-Hunt
public class ParamsParser {
    protected String paramSubstring;
    protected Matcher matcher;

    public ParamsParser(String paramSubstring) {
        this.paramSubstring = paramSubstring;
    }

    public String getSeparator(String input) {
        //Matcher gives index of space before the param, so (matched index + 1) gives the separator
        int separatorIndex = matcher.start() + 1;
        return " " + String.valueOf(input.charAt(separatorIndex));
    }

    String prependAppendSpaces(String str) {
        return " " + str + " ";
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
        HashMap<String, String> paramMap = new HashMap<>();
        String[] buffer;
        String paramArgument = "";
        boolean paramArgumentExist;

        do {
            paramSubstring += " ";
            paramArgument = "";

            //Separate into [paramType, rest of string]
            buffer = paramSubstring.split(" ", 2);
            String paramType = buffer[0];
            buffer[1] = buffer[1].trim();

            /*
            * Process param argument and check whether more params exist
            */

            boolean isRestOfStringEmpty = buffer[1].length() == 0;
            if (isRestOfStringEmpty) {
                //No param argument, no further params
                putParamIntoParamMap(paramType, buffer[1], paramMap);
                break;
            }

            //Matcher requires a leading and trailing blank space to successfully match a param
            paramSubstring = " " + buffer[1] + " ";
            matcher = RegexMatcher.paramMatcher(paramSubstring);

            try {
                //Throws IllegalStateException if no more params are present after current param
                paramArgumentExist = matcher.start() > 0;
            } catch (java.lang.IllegalStateException e) {
                //No further params
                paramArgument = paramSubstring.trim();
                putParamIntoParamMap(paramType, paramArgument, paramMap);
                break;
            }

            if (paramArgumentExist) {
                //Split into [paramArgument, rest of string]
                //Separator character = '/' or '-'
                String separator = getSeparator(paramSubstring);
                buffer = paramSubstring.split(separator, 2);
                buffer[1] = separator + buffer[1];
                paramArgument = buffer[0].trim();
                paramSubstring = buffer[1].trim();
            } else {
                //Nothing more to process for current param
                paramSubstring = paramSubstring.trim();
            }
            putParamIntoParamMap(paramType, paramArgument, paramMap);

        } while (true);
        return paramMap;
    }

    public void putParamIntoParamMap(String paramType, String param, HashMap paramMap) {
        try {
            ParamChecker.getInstance().checkAndReturnDuplicateParamTypes(paramType, paramMap);
            paramMap.put(paramType, param);
        } catch (ParseFailParamException exception) {
            // Fall through
        }
    }

}