package storage;

import exception.InvalidFileFormatException;

public class StorageParser {

    //@@author Zhu-Ze-Yu
    /**
     * Parses the question in the file.
     *
     * @param arg question with prefix [Q] in the file
     * @return String question without prefix [Q]
     * @throws InvalidFileFormatException if the file format is not invalid
     */
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

    /**
     * Parses the answer in the file.
     *
     * @param arg answer with prefix [A] in the file
     * @return String answer without prefix [A]
     * @throws InvalidFileFormatException if the file format is not invalid
     */
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

    /**
     * Parses the previous interval in the file.
     *
     * @param arg previous interval with prefix [P] in the file
     * @return String previous interval without prefix [P]
     * @throws InvalidFileFormatException if the file format is not invalid
     */
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
