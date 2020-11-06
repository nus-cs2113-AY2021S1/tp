package parser;

import exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtil {

    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";

    private static final String MESSAGE_QUESTION_PREFIX = "There needs to be a \"q:\" prefix before the question.\n";
    private static final String MESSAGE_ANSWER_PREFIX = "There needs to be a \"a:\" prefix before the answer.\n";

    //@@author Jane-Ng
    public static String parseQuestion(String arg, String messageUsage) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException(MESSAGE_QUESTION_PREFIX + messageUsage);
        }

        return arg.substring(2).trim();
    }

    public static String parseAnswer(String arg, String messageUsage) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException(MESSAGE_ANSWER_PREFIX + messageUsage);
        }

        return arg.substring(2).trim();
    }

    //@@author gua-guargia
    public static boolean checkAlphanumericOnly(String commandArgs) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(commandArgs);
        return matcher.matches();
    }
}
