package parser;

import exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";

    private static final String MESSAGE_QUESTION_PREFIX = "There needs to be a \"q:\" prefix before the question.\n";
    private static final String MESSAGE_ANSWER_PREFIX = "There needs to be a \"a:\" prefix before the answer.\n";

    //@@author Jane-Ng
    /**
     * Parses {@code arg} into a question by removing the question prefix.
     *
     * @param arg argument to be parsed
     * @param messageUsage message usage of the command
     * @return the question with leading and trailing whitespaces trimmed
     * @throws InvalidInputException if the given {@code arg} is invalid
     */
    public static String parseQuestion(String arg, String messageUsage) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException(MESSAGE_QUESTION_PREFIX + messageUsage);
        }

        return arg.substring(2).trim();
    }

    /**
     * Parses {@code arg} into an answer by removing the answer prefix.
     *
     * @param arg argument to be parsed
     * @param messageUsage message usage of the command
     * @return the answer with leading and trailing whitespaces trimmed
     * @throws InvalidInputException if the given {@code arg} is invalid
     */
    public static String parseAnswer(String arg, String messageUsage) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException(MESSAGE_ANSWER_PREFIX + messageUsage);
        }

        return arg.substring(2).trim();
    }

    //@@author gua-guargia
    /**
     * Checks if {@code commandArgs} contains alphanumeric characters and spaces only.
     *
     * @param commandArgs command argument entered by user
     * @return true if {@code commandArgs} contains alphanumeric characters and spaces only
     */
    public static boolean checkAlphanumericOnly(String commandArgs) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(commandArgs);
        return matcher.matches();
    }
}
