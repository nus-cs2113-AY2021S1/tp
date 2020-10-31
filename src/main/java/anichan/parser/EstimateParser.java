package anichan.parser;

import anichan.commands.EstimateCommand;
import anichan.exception.AniException;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author OngDeZhi
/**
 * Handles parsing for estimate command.
 */
public class EstimateParser extends CommandParser {
    private static final String WORDS_PER_HOUR_OPTION = "wph";
    private static final String VALID_SCRIPT_FILE_FORMAT = ".txt";
    private static final String SLASH = "/";

    private static final String NO_SCRIPT_FILE_SPECIFIED = "No script file specified!";
    private static final String TOO_MANY_SCRIPT_FILE = "AniChan can only process one script file at a time!";
    private static final String SPECIFIED_PATH_TO_SCRIPT_FILE = "Only specify the script file name!";
    private static final String INVALID_SCRIPT_FILE_FORMAT = "Only \".txt\" script files are accepted!";
    private static final String INVALID_OPTION = "Only \"-wph\" is accepted!";
    private static final String NO_WORDS_PER_HOUR_SPECIFIED = "Words per hour information is missing!";
    private static final String MULTIPLE_WORDS_PER_HOUR_SPECIFIED = "Only one words per hour value is needed!";
    private static final String WORDS_PER_HOUR_IS_ZERO = "Words per hour cannot be zero!";

    private static final int NO_WORDS_PER_HOUR_PROVIDED = -1;
    private static final Logger LOGGER = AniLogger.getAniLogger(EstimateParser.class.getName());

    /**
     * Parses the specified command description.
     *
     * @param description the specified command description
     * @return initialised {@code EstimateCommand} object
     * @throws AniException when an error occurred while parsing the command description
     */
    public EstimateCommand parse(String description) throws AniException {
        assert description != null : DESCRIPTION_CANNOT_BE_NULL;
        String[] paramGiven = description.split(SPLIT_DASH, 2);
        if (paramGiven[0].isBlank()) {
            throw new AniException(NO_SCRIPT_FILE_SPECIFIED);
        }

        String fileName = paramGiven[0].trim();
        if (!isValidFileName(fileName)) {
            throw new AniException(INVALID_SCRIPT_FILE_FORMAT);
        }

        int wordsPerHour = NO_WORDS_PER_HOUR_PROVIDED;
        if (paramGiven.length == 2) {
            wordsPerHour = parameterParser(paramGiven);
        }

        LOGGER.log(Level.INFO, "Returning a EstimateCommand object with file: "
                                    + fileName + ", and wph: " + wordsPerHour + ".");
        return new EstimateCommand(fileName, wordsPerHour);
    }

    /**
     * Parses the parameter provided in the command description.
     *
     * @param paramGiven an String Array containing the parameters and the value
     * @return the parsed words per hour specified in the parameter
     * @throws AniException when an error occurred while parsing the parameters
     */
    private int parameterParser(String[] paramGiven) throws AniException {
        String[] parsedParts = paramGiven[1].split(SPLIT_WHITESPACE);
        String option = parsedParts[0].trim();
        if (!option.equals(WORDS_PER_HOUR_OPTION)) {
            throw new AniException(INVALID_OPTION);
        }

        if (parsedParts.length == 1) {
            throw new AniException(NO_WORDS_PER_HOUR_SPECIFIED);
        } else if (parsedParts.length > 2) {
            throw new AniException(MULTIPLE_WORDS_PER_HOUR_SPECIFIED);
        }

        String wordsPerHourString = parsedParts[1].trim();
        int wordsPerHour = parseStringToInteger(wordsPerHourString);
        if (wordsPerHour == 0) {
            throw new AniException(WORDS_PER_HOUR_IS_ZERO);
        }

        return wordsPerHour;
    }

    /**
     * Check to ensure the user specified a valid script file.
     *
     * @param fileName script file name
     * @return {@code true} if the file name is valid; false otherwise
     * @throws AniException when the file name is invalid
     */
    private boolean isValidFileName(String fileName) throws AniException {
        if (fileName.contains(SLASH)) {
            throw new AniException(SPECIFIED_PATH_TO_SCRIPT_FILE);
        }

        String[] fileNameSplit = fileName.split(SPLIT_WHITESPACE);
        int numberOfTextFiles = 0;
        for (String fileNameParts : fileNameSplit) {
            if (fileNameParts.contains(VALID_SCRIPT_FILE_FORMAT)) {
                numberOfTextFiles++;
            }
        }

        if (numberOfTextFiles > 1) {
            throw new AniException(TOO_MANY_SCRIPT_FILE);
        }

        return fileName.trim().endsWith(VALID_SCRIPT_FILE_FORMAT);
    }
}
