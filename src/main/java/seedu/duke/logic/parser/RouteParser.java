package seedu.duke.logic.parser;

import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

public class RouteParser extends Parser {

    public static final String DELIMITER = "/to";

    public RouteParser(String message) {
        super(message);
    }

    public String[] getLocations() throws CustomException {
        try {
            if(super.getUserInput().trim().length()==0) {
                throw new CustomException(ExceptionType.NO_LOCATIONS);
            } else if(super.getUserInput().contains(DELIMITER)) {
                String[] components = super.splitCommands(2, DELIMITER);
                return new String[]{components[0], components[1]};
            } else {
                throw new CustomException(ExceptionType.NO_ROUTE_DELIMITER);
            }
        } catch (StringIndexOutOfBoundsException error) {
            throw new CustomException(ExceptionType.NO_LOCATIONS);
        }
    }
}
