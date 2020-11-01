package storage;

import exception.InvalidFileFormatException;

public class StorageParser {

    //@@author Zhu-Ze-Yu
    public static String parseQuestionInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.QUESTION_PREFIX))) {
            throw new InvalidFileFormatException("Questions in the file should begin with [Q].");
        }

        String question = arg.substring(3).trim();
        if (question.isEmpty()) {
            throw new InvalidFileFormatException("There should be a question after [Q] in the file.");
        }

        return question;
    }

    public static String parseAnswerInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.ANSWER_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [A].");
        }

        String answer = arg.substring(3).trim();
        if (answer.isEmpty()) {
            throw new InvalidFileFormatException("There should be a answer after [A] in the file.");
        }

        return answer;
    }

    public static String parsePreIntervalInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.PREVIOUS_INTERVAL_PREFIX))) {
            throw new InvalidFileFormatException("Previous intervals in the file should begin with [P].");
        }

        String preInterval = arg.substring(3).trim();
        if (preInterval.isEmpty()) {
            throw new InvalidFileFormatException("There should be a interval after [P] in the file.");
        }

        return preInterval;
    }

    public static String parseTaskNameInFile(String arg) throws InvalidFileFormatException {
        String name = arg.trim();
        if (name.isEmpty()) {
            throw new InvalidFileFormatException("There should be a name of the completed task.");
        }

        return name;
    }

    public static String parsePercentInFile(String arg) throws InvalidFileFormatException {
        String percent = arg.trim().substring(0,3);
        if (percent.isEmpty()) {
            throw new InvalidFileFormatException(
                    "There should be a number to indicate how many tasks have completed.");
        }
        return percent;
    }

    public static String parseRatingInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.RATING_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [R].");
        }

        String rating = arg.substring(3).trim();
        if (rating.isEmpty()) {
            throw new InvalidFileFormatException("There should be a rating after [R] in the file.");
        }

        return rating;
    }

    //@@author
}
