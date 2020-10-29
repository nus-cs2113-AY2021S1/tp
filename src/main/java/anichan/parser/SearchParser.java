package anichan.parser;

import anichan.commands.SearchCommand;
import anichan.exception.AniException;

/**
 * Handles parsing for search command.
 */
public class SearchParser extends CommandParser {
    private static final String SEARCH_TYPE_ALREADY_SET_ERROR = "Please specify only one type of search!";
    private SearchCommand searchCommand;

    public SearchParser() {
        searchCommand = new SearchCommand();
    }

    /**
     * Parses the string parameters and creates an executable searchCommand according to the parameters.
     *
     * @param description is the parameters portion of the user input
     * @return an executable BrowseCommand object
     * @throws AniException if an error is encountered while parsing
     */
    public SearchCommand parse(String description) throws AniException {
        String[] paramGiven = parameterSplitter(description);
        paramIsSetCheck(paramGiven);
        parameterParser(paramGiven);
        return searchCommand;
    }

    /**
     * Loops through each parameter and sets the option specified by each parameter.
     *
     * @param paramGiven is a String Array containing the processed parameters
     * @throws AniException if invalid parameters are parsed in
     */
    public void parameterParser(String[] paramGiven) throws AniException {
        for (String param : paramGiven) {
            String[] paramParts = param.split(SPLIT_WHITESPACE, FIELD_SPLIT_LIMIT);
            if (paramParts.length == 0) {
                break;
            }
            switch (paramParts[0].trim()) {
            case "": //skip the first empty param
                break;
            case NAME_PARAM:
                paramFieldCheck(paramParts);
                checkIfSearchTypeAlreadySet();
                searchCommand.setSearchTerm(paramParts[1]);
                break;
            case GENRE_PARAM:
                paramFieldCheck(paramParts);
                checkIfSearchTypeAlreadySet();
                searchCommand.setSearchGenre(paramParts[1]);
                break;
            default:
                String invalidParameter = PARAMETER_ERROR_HEADER + param + NOT_RECOGNISED;
                throw new AniException(invalidParameter);
            }
        }
    }

    /**
     * Checks if search type had been previously set since name and genre searches are mutually
     * exclusive actions for now.
     *
     * @throws AniException if search type had already been specified
     */
    private void checkIfSearchTypeAlreadySet() throws AniException {
        if (searchCommand.getSearchType() != -1) {
            throw new AniException(SEARCH_TYPE_ALREADY_SET_ERROR);
        }
    }
}
