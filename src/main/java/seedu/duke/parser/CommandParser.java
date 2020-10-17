package seedu.duke.parser;

import seedu.duke.exception.AniException;

public abstract class CommandParser {
    public static final String NOT_RECOGNISED = " is not recognised!";
    protected static final String PARAMETER_ERROR_HEADER = "Parameter : -";
    protected static final String REQUIRE_ADDITIONAL_FIELD = " requires an additional field";
    protected static final String TOO_MUCH_FIELDS = " has too much fields";
    protected static final String NO_PARAMETER_PROVIDED = "No Parameter provided";

    /**
     * Splits the parameters into individual parts for parsing.
     *
     * @param parameter unprocessed parameter strings
     * @return parameters in parts
     */
    public String[] parameterSplitter(String parameter) {
        return parameter.split("-");
    }

    /**
     * Checks if there is a parameter set.
     *
     * @param paramParts Array of parameters
     * @throws AniException if there is no parameter provided
     */

    public void paramIsSetCheck(String[] paramParts) throws AniException {
        if (paramParts.length < 2) {
            throw new AniException(NO_PARAMETER_PROVIDED);
        }
    }

    /**
     * Checks if parameter has a single additional field or not.
     *
     * @param paramParts the param to check
     * @throws AniException if the parameter is missing the additional field
     */
    public void paramFieldCheck(String[] paramParts) throws AniException {
        if (paramParts.length < 2) {
            String invalidParameter = PARAMETER_ERROR_HEADER + paramParts[0] + REQUIRE_ADDITIONAL_FIELD;
            throw new AniException(invalidParameter);
        }
    }

    /**
     * Checks if parameter has more than one field.
     *
     * @param paramParts the param to check
     * @throws AniException if the parameter has too many fields
     */
    public void paramExtraFieldCheck(String[] paramParts) throws AniException {
        if (paramParts.length > 2) {
            String invalidParameter = PARAMETER_ERROR_HEADER + paramParts[0] + TOO_MUCH_FIELDS;
            throw new AniException(invalidParameter);
        }
    }


    /**
     * Checks if the string is an integer.
     *
     * @param checkStr the string to check
     * @return true if it can be parsed into an integer
     */

    public boolean isInt(String checkStr) {
        return checkStr.matches("-?\\d+(\\.\\d+)?");
    }

}
