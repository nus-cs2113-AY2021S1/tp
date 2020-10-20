package seedu.duke.parser;

import seedu.duke.command.BrowseCommand;
import seedu.duke.exception.AniException;
import static seedu.duke.logger.AniLogger.getAniLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowseParser extends CommandParser {
    protected static final String SORT_PARAM = "s";
    protected static final String FILTER_PARAM = "f";
    protected static final String ORDER_PARAM = "o";
    protected static final String PAGE_PARAM = "p";
    protected static final String ASCENDING_FIELD = "asc";
    protected static final String DESCENDING_FIELD = "dsc";
    protected static final String NAME_FIELD = "name";
    protected static final String RATING_FIELD = "rating";
    protected static final int ID_SORT = 0;
    protected static final String OUT_OF_BOUND_PAGE_ERROR = "Invalid Page size!";
    protected static final String PARAMETER_ERROR_HEADER = "Parameter : -";
    protected static final String REQUIRE_ADDITIONAL_FIELD = " requires an additional field";
    protected static final String INVALID_OPTION = " is not a valid option";
    protected static final String NOT_RECOGNISED = " is not recognised!";
    protected static final String NON_INTEGER_PROVIDED = "Please specify an Int value for page number!";
    protected static final String BROWSE_SETTINGS_CHANGED_INFO = "Default values modified";
    private static final Logger LOGGER = getAniLogger(BrowseParser.class.getName());

    private BrowseCommand browseCommand;

    public BrowseParser() {
        browseCommand = new BrowseCommand();
        // LOGGER.setLevel(Level.WARNING);
    }

    public BrowseCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);
        if (paramGiven.length > 1) {
            parameterParser(paramGiven);
            LOGGER.log(Level.INFO, BROWSE_SETTINGS_CHANGED_INFO);
        }
        return browseCommand;
    }

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
            case FILTER_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                browseCommand.setFilter(paramParts[1]);
                break;
            case ORDER_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                checkOrderType(paramParts[1]);
                break;
            case PAGE_PARAM:
                paramFieldCheck(paramParts);
                paramExtraFieldCheck(paramParts);
                if (!isInt(paramParts[1].trim())) {
                    throw new AniException(NON_INTEGER_PROVIDED);
                }
                browseCommand.setPage(Integer.parseInt(paramParts[1].trim()));
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
    }

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
