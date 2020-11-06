package seedu.financeit.parser;

import seedu.financeit.common.exceptions.ParseFailParamException;
import seedu.financeit.utils.ParamChecker;
import seedu.financeit.utils.RegexMatcher;

import java.util.HashMap;
import java.util.regex.Matcher;

//@@author Artemis-Hunt
public class ParamsParser {
    protected Matcher matcher;
    private static ParamsParser paramsParser;

    private ParamsParser() {
    }

    public static ParamsParser getInstance() {
        if (paramsParser == null) {
            paramsParser = new ParamsParser();
        }
        return paramsParser;
    }

    public String getSeparator(String input) {
        //Matcher matches <space><separator><paramType><space>, so (matched index + 1) gives the separator
        int separatorIndex = matcher.start() + 1;
        System.out.println(input.charAt(separatorIndex));
        return String.valueOf(input.charAt(separatorIndex));
    }

    public String getNextParamType(String input) {
        System.out.println("input1: " + input);
        //Matcher gives index of space before the param, so (matched index + 1) gives the separator
        int separatorIndex = -1;
        try {
            separatorIndex = RegexMatcher.paramMatcher(input).start();
            System.out.println("proces: " + input + ", " + separatorIndex);
        } catch (IllegalStateException e) {
            return input;
        }
        int nextSpaceIndex = input.indexOf(" ", separatorIndex + 1);
        System.out.println("output: " + input.substring(separatorIndex, nextSpaceIndex));
        return input.substring(separatorIndex, nextSpaceIndex);
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
    public HashMap<String, String> parseParams(String paramSubstring) {
        HashMap<String, String> paramMap = new HashMap<>();
        String[] buffer;
        String paramArgument = "";
        boolean paramArgumentExist;
        System.out.println("input: " + paramSubstring);
        do {
            paramSubstring += " ";
            paramArgument = "";
            System.out.println("paramsubs: " + paramSubstring);
            //Separate into [paramType, rest of string]
            buffer = paramSubstring.split(" ", 2);

            String paramType = buffer[0].trim();
            paramSubstring = buffer[1].trim();
            /*
            * Process param argument and check whether more params exist
            */

            boolean isRestOfStringEmpty = paramSubstring.length() == 0;
            if (isRestOfStringEmpty) {
                //No param argument, no further params
                putParamIntoParamMap(paramType, paramSubstring, paramMap);
                break;
            }

            //Matcher requires a leading and trailing blank space to successfully match a param
            paramSubstring = " " + paramSubstring + " ";

            matcher = RegexMatcher.paramMatcher(paramSubstring);

            try {
                //Attempts to look for the start of the next param. If found, everything before start of next
                //param is the paramArgument belonging to the current param.
                paramArgumentExist = matcher.start() >= 0;
            } catch (java.lang.IllegalStateException e) {
                //If no more params are present after current param
                //Only thing in paramSubstring is the paramArgument
                paramArgument = paramSubstring.trim();
                putParamIntoParamMap(paramType, paramArgument, paramMap);
                break;
            }

            if (paramArgumentExist) {
                //Split into [paramArgument, rest of string]
                //Separator character = '/' or '-'
                //String separator = getSeparator(paramSubstring);
                String separator = getNextParamType(paramSubstring);
                buffer = paramSubstring.split(separator, 2);
                buffer[1] = separator + buffer[1];
                System.out.println("buffer0: " + buffer[0]);
                System.out.println("buffer1: " + buffer[1]);

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