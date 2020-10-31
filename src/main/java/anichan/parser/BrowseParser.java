package anichan.parser;

import anichan.exception.AniException;
import anichan.commands.BrowseCommand;
import anichan.logger.AniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles parsing for browse command.
 */
public class BrowseParser extends CommandParser {
    private static final String SORT_PARAM = "s";
    private static final String ORDER_PARAM = "o";
    private static final String PAGE_PARAM = "p";
    private static final String ASCENDING_FIELD = "asc";
    private static final String DESCENDING_FIELD = "dsc";
    private static final String NAME_FIELD = "name";
    private static final String RATING_FIELD = "rating";
    private static final String PARAMETER_ERROR_HEADER = "Parameter : -";
    private static final String INVALID_OPTION = " is not a valid option";
    private static final String NOT_RECOGNISED = " is not recognised!";
    private static final String NON_INTEGER_PROVIDED = "Please specify an Int value for page number!";
    private static final String BROWSE_SETTINGS_CHANGED_INFO = "Default values modified";
    private static final Logger LOGGER = AniLogger.getAniLogger(BrowseParser.class.getName());

    private BrowseCommand browseCommand;

    public BrowseParser() {
        browseCommand = new BrowseCommand();
    }

    /**
     * Parses the string parameters and creates an executable browseCommand according to the parameters.
     *
     * @param description is the parameters portion of the user input
     * @return an executable BrowseCommand object
     * @throws AniException if an error is encountered while parsing
     */
    public BrowseCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);
        if (paramGiven.length > 1) {
            parameterParser(paramGiven);
            LOGGER.log(Level.INFO, BROWSE_SETTINGS_CHANGED_INFO);
        }
        return browseCommand;
    }

    /**
     * Loops through each parameter and sets the option specified by each parameter.
     *
     * @param paramGiven is a String Array containing the processed parameters
     * @throws AniException if invalid parameters are parsed in
     */
    private void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(SPLIT_WHITESPACE);
            if (paramParts.length == 0) {
                break;
            }
            switch (paramParts[0].trim()) {
            case "": //skip empty param
                break;
            case SORT_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                checkSortType(paramParts);
                break;
            case ORDER_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                checkOrderType(paramParts[1]);
                break;
            case PAGE_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                if (!isInteger(paramParts[1].trim())) {
                    throw new AniException(NON_INTEGER_PROVIDED);
                }
                browseCommand.setPage(parseStringToInteger(paramParts[1].trim()));
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
    }

    /**
     * Performs input validation of the order parameter and its field.
     *
     * @param paramField the field of the order parameter.
     * @throws AniException if the field is invalid
     */
    private void checkOrderType(String paramField) throws AniException {
        switch (paramField.trim()) {
        case ASCENDING_FIELD:
            browseCommand.setOrder(0);
            break;
        case DESCENDING_FIELD:
            browseCommand.setOrder(1);
            break;
        default:
            String paramFieldError = paramField + INVALID_OPTION;
            throw new AniException(paramFieldError);
        }
    }

    /**
     * Performs input validation of the sortType parameter and its field.
     *
     * @param paramParts the field of the sortType parameter.
     * @throws AniException if the field is invalid
     */
    private void checkSortType(String[] paramParts) throws AniException {
        switch (paramParts[1].trim()) {
        case NAME_FIELD:
            browseCommand.setSortType(1);
            break;
        case RATING_FIELD:
            browseCommand.setSortType(2);
            break;
        default:
            String paramFieldError = paramParts[1] + INVALID_OPTION;
            throw new AniException(paramFieldError);
        }
    }
}
