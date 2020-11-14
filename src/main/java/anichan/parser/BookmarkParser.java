package anichan.parser;

import anichan.commands.BookmarkCommand;
import anichan.exception.AniException;

import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles parsing for bookmark command.
 */
public class BookmarkParser extends CommandParser {
    //Bookmark action param
    public static final String ADD_PARAM = "a";
    public static final String DELETE_PARAM = "d";
    public static final String EPISODE_PARAM = "e";
    public static final String LIST_PARAM = "l";
    public static final String INFO_PARAM = "i";
    public static final String ADD_NOTE_PARAM = "n";
    public static final String REMOVE_NOTE_PARAM = "r";

    //Constant parameter
    private static final String EMPTY_PARAM = "";
    private static final int MIN_LENGTH = 1;
    private static final int ZERO_PARAM = 0;
    public static final int MAX_PARAM_LENGTH = 2;

    //Message error header
    private static final String PARAMETER_ERROR_HEADER = "Parameter :";
    private static final String DESCRIPTION_ERROR_HEADER = "Description :";
    private static final String BOOKMARK_LOAD_ERROR_HEADER = "Could not load bookmark command :";

    //Message bookmark action type
    private static final String BOOKMARK_EPISODE = "edit episode";
    private static final String BOOKMARK_DELETE = "delete";
    private static final String BOOKMARK_ADD = "add";
    private static final String BOOKMARK_REMOVE_NOTE = "remove note";
    private static final String BOOKMARK_INDEX = "index";

    //Message error trailer
    private static final String BOOKMARK_INDEX_INFO_ERROR = " Bookmark index for info requires integer.";
    private static final String EXTRA_FIRST_PARAM_ERROR = " Add/Delete/List should not have extra first param.";
    private static final String BOOKMARK_LIST_EXTRA_FIELD_ERROR = " Bookmark list should not have extra field.";
    private static final String EMPTY_PARAM_ERROR = " The parameter is empty";
    private static final String NON_INTEGER_ERROR = " param requires integer.";
    private static final String NO_SPACE_BETWEEN_PARAM_ERROR = "Please leave a spacing between each parameter!";

    //Bookmark required field
    private int bookmarkIndex;
    private int animeIndex;
    private int noteIndex;
    private int bookmarkEpisode;
    private String bookmarkAction;
    private String bookmarkNote;

    private static final Logger LOGGER = AniLogger.getAniLogger(BookmarkParser.class.getName());

    /**
     * Parses the string parameters and creates an executable bookmarkCommand according to the parameters.
     * All command require 2 parameter except for bookmark info e.g bookmark bookmark_id.
     *
     * @param description is the parameters portion of the user input
     * @return and executable BookmarkCommand object
     * @throws AniException if an error is encountered while parsing
     */
    public BookmarkCommand parse(String description) throws AniException {
        assert description != null : DESCRIPTION_CANNOT_BE_NULL;
        String[] paramGiven = getSplitDescription(description);
        if (paramGiven.length > MIN_LENGTH) {
            parameterParser(paramGiven[1]);
            setFirstParameter(paramGiven[0]);
        } else {
            setSingleParameter(description);
        }

        return new BookmarkCommand(bookmarkAction, bookmarkIndex, animeIndex, bookmarkEpisode, noteIndex, bookmarkNote);
    }

    /**
     * Bookmark commands only allow one dash parameter e.g "-a" or "-d".
     * The method based on the dach parameter will determine the checks to do
     * and the values to set for Bookmark Command required field.
     *
     * @param paramGiven is the string containing the processed parameters with field
     * @throws AniException if invalid parameters are parsed in
     */
    private void parameterParser(String paramGiven) throws AniException {
        String[] paramParts = paramGiven.split(WHITESPACE, FIELD_SPLIT_LIMIT);
        bookmarkAction = paramParts[0].trim();
        paramEmptyCheck(paramGiven, paramParts);
        switch (bookmarkAction) {
        case EPISODE_PARAM:
            paramFieldCheck(paramParts);
            paramExtraFieldCheck(paramParts);
            checkIsInteger(paramGiven, paramParts[1], BOOKMARK_EPISODE);
            bookmarkEpisode = parseStringToInteger(paramParts[1].trim());
            break;
        case ADD_PARAM:
            paramFieldCheck(paramParts);
            paramExtraFieldCheck(paramParts);
            checkIsInteger(paramGiven, paramParts[1], BOOKMARK_ADD);
            animeIndex = parseStringToInteger(paramParts[1].trim());
            break;
        case DELETE_PARAM:
            paramFieldCheck(paramParts);
            paramExtraFieldCheck(paramParts);
            checkIsInteger(paramGiven, paramParts[1], BOOKMARK_DELETE);
            bookmarkIndex = parseStringToInteger(paramParts[1].trim());
            break;
        case LIST_PARAM:
            listFieldCheck(paramParts);
            break;
        case ADD_NOTE_PARAM:
            paramFieldCheck(paramParts);
            bookmarkNote = paramParts[1].trim();
            break;
        case REMOVE_NOTE_PARAM:
            paramFieldCheck(paramParts);
            paramExtraFieldCheck(paramParts);
            checkIsInteger(paramGiven, paramParts[1], BOOKMARK_REMOVE_NOTE);
            noteIndex = parseStringToInteger(paramParts[1].trim());
            break;
        default:
            String invalidParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED;
            throw new AniException(invalidParameter);
        }
    }

    /**
     * Check that part of the parameter (the field) is integer.
     * Else, throw a custom error message based on the command type and the param given.
     *
     * @param paramGiven   the input with processed parameter and its field
     * @param paramPart    the field to check
     * @param bookmarkType the type of bookmark command for error message
     * @throws AniException when an error occurred while executing the command
     */
    private void checkIsInteger(String paramGiven, String paramPart, String bookmarkType) throws AniException {
        if (!isInteger(paramPart.trim())) {
            String invalidParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED
                    + System.lineSeparator() + " Bookmark " + bookmarkType + NON_INTEGER_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidParameter);
            throw new AniException(invalidParameter);
        }
    }

    /**
     * Check that the field of parameters are not empty.
     *
     * @param paramGiven the parameter with its field
     * @param paramParts array of parameter parts
     * @throws AniException if the parameter provided is empty
     */
    private void paramEmptyCheck(String paramGiven, String[] paramParts) throws AniException {
        if (paramParts.length == ZERO_PARAM) {
            String invalidParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED
                    + System.lineSeparator() + EMPTY_PARAM_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidParameter);
            throw new AniException(invalidParameter);
        }
    }

    /**
     * Check that bookmark list command does not contain a field.
     *
     * @param paramParts the processed parameter
     * @throws AniException if the list field provided is not empty
     */
    private void listFieldCheck(String[] paramParts) throws AniException {
        if (paramParts.length > MIN_LENGTH) {
            String invalidExtraField = PARAMETER_ERROR_HEADER + paramParts[1] + NOT_RECOGNISED
                    + System.lineSeparator() + BOOKMARK_LIST_EXTRA_FIELD_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidExtraField);
            throw new AniException(invalidExtraField);
        }
    }

    /**
     * Set the first parameter field, for commands that require two different variable.
     * It includes the bookmark edit episode, bookmark note and bookmark remove note command.
     *
     * @param paramGiven the first field not accompanied by parameter
     * @throws AniException if the first field is required but empty, or the first field is not required but not empty
     */
    private void setFirstParameter(String paramGiven) throws AniException {
        //Action edit(e), note(n), remove note(r) requires first parameter as bookmarkIndex
        if (bookmarkAction.equals(EPISODE_PARAM)
                || bookmarkAction.equals(ADD_NOTE_PARAM)
                || bookmarkAction.equals(REMOVE_NOTE_PARAM)) {
            checkIsInteger(paramGiven, paramGiven, BOOKMARK_INDEX);
            checkForParamStacking(paramGiven);
            bookmarkIndex = parseStringToInteger(paramGiven.trim());
        } else {
            boolean isEmpty = paramGiven.trim().equals(EMPTY_PARAM);
            if (!isEmpty) {
                String invalidFirstParameter = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED
                        + System.lineSeparator() + EXTRA_FIRST_PARAM_ERROR;
                LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidFirstParameter);
                throw new AniException(invalidFirstParameter);
            }
        }
    }

    /**
     * This indicate that it is a bookmark info command.
     *
     * @param paramGiven the processed parameter
     * @throws AniException if the only field is not integer or empty
     */
    private void setSingleParameter(String paramGiven) throws AniException {
        if (!isInteger(paramGiven.trim())) {
            String invalidBookmarkIndex = PARAMETER_ERROR_HEADER + paramGiven + NOT_RECOGNISED
                    + System.lineSeparator() + BOOKMARK_INDEX_INFO_ERROR;
            LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidBookmarkIndex);
            throw new AniException(invalidBookmarkIndex);
        }
        bookmarkAction = INFO_PARAM;
        bookmarkIndex = parseStringToInteger(paramGiven.trim());
    }

    /**
     * Split the input provided by - to recognise the number of parameter.
     * The string array will be returned for further processing.
     *
     * @param description is the parameters portion of the user input
     * @return the individual parameter with its field
     * @throws AniException if there is more than 2 parameter provided
     */
    private String[] getSplitDescription(String description) throws AniException {
        String[] paramGiven = description.split(DASH);
        if (paramGiven.length > MAX_PARAM_LENGTH) {
            String invalidDescription = DESCRIPTION_ERROR_HEADER + description + TOO_MUCH_FIELDS;
            LOGGER.log(Level.WARNING, BOOKMARK_LOAD_ERROR_HEADER + invalidDescription);
            throw new AniException(invalidDescription);
        }
        return paramGiven;
    }

    /**
     * Checks and prevents parameter stacking e.g. "1-e 5" or "1-n test".
     *
     * @param firstParam first parameter before -
     * @throws AniException if there is no space between the parameter
     */
    private void checkForParamStacking(String firstParam) throws AniException {
        //Forgive the last param
        if (!firstParam.endsWith(WHITESPACE)) {
            throw new AniException(NO_SPACE_BETWEEN_PARAM_ERROR);
        }
    }
}
