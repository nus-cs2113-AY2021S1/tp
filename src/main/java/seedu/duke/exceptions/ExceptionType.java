package seedu.duke.exceptions;

public enum ExceptionType {
    INVALID_COMMAND("I do not understand!!"),
    INVALID_BUS("Sorry,there is no such bus in our database!"),
    NO_DELIMITER_ROUTE("Oops! You are missing the delimiter /to.\nThe format for this command is as follows:"
            + "\n/route <starting loc> /to <destination>\nwhere starting location and destination must be the names of "
            + "bus stops in NUS."),
    MANY_DELIMITERS_ROUTE("Oops! You have too many delimiters!\nThe format for this command is as follows:"
            + "\n/route <starting loc> /to <destination>\nwhere starting location and destination must be the names of "
            + "bus stops in NUS."),
    NO_DELIMITER_DESCFAV("Oops! You are missing the delimiter /to.\nThe format for this command is as follows:"
            + "\n/descfav <index> /to <description>\nwhere index is a number between 1 and the maximum items in the "
            + "list\nand description is the new description you want for your favourite command."),
    MANY_DELIMITERS_DESCFAV("Oops! You have too many delimiters!\nThe format for this command is as follows:"
            + "\n/descfav <index> /to <description>\nwhere index is a number between 1 and the maximum items in the "
            + "list\nand description is the new description you want for your favourite command."),
    NO_LOCATIONS("Oh dear! I don't have all locations."),
    MISSING_BUS_CODE("Oh no! You seem to have missed typing in the bus code!"),
    SAME_LOCATIONS("You don't need a bus to get there...You are right there!"),
    INVALID_BUS_STOP("Oh no! I do not understand which bus stop you are looking for."),
    INVALID_START_LOC("The starting location you have provided is not the name of any bus stop in our\n"
            + "database :("),
    INVALID_DEST("The destination you have provided is not the name of any bus stop in our database :("),
    INVALID_LOCATIONS("The starting location and destination you have provided are not the names of any bus\n"
            + "stops in our database :("),
    INVALID_FACULTY("Oh no! Please enter a faculty."),
    INVALID_FOODPLACE("Oh no! I cannot detect any inputs."),
    INVALID_FAVOURITE("Oh no! I cannot seem to detect the command you want to store as your favourite."),
    UPDATE_FILE_FAIL("Sorry to inform you that the update of frequency list is unsuccessful."),
    FREQ_READ_FILE_FAIL("FreqList.txt Read with corrupted data! Re-initialising file!"),
    EMPTY_FAVLIST("Your favourites list is empty!"),
    NO_INPUT("Oh dear! You haven't typed in the index or changed description."),
    EMPTY_DESCRIPTION("Hmmm, I don't think you would want an empty description."),
    NOT_A_NUMBER("Yikes! That is not even a number."),
    EXTRA_PARAMETERS("Hmm, this function should not be called with parameters."),
    INVALID_INDEX("Sorry, that isn't the index of any command in the list."),
    SAME_DESCRIPTION("No change needed! You already have that description for your favourite command."),
    EMPTY_INDEX("Oh no! I cannot detect the input index.");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
