package seedu.notus.util.parser;

//@@author brandonywl

import seedu.notus.command.Command;
import seedu.notus.command.ListEventCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;

import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.notus.util.PrefixSyntax.TIMING_SPLIT_DELIMITER;

/**
 * Represents a parser object specifically to parse message for ListEventCommand.
 */
public class ParseListEventCommand extends Parser {

    public ParseListEventCommand(String userMessage) {
        super(userMessage);
    }

    @Override
    public Command parse() throws SystemException {
        if (userMessage == null) {
            return new ListEventCommand();
        } else {
            ArrayList<String[]> splitInfoDetails = splitInfoDetails(userMessage);
            String details = "";
            int year;
            int month;

            if (splitInfoDetails.size() == 0) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TIMING_PREFIX);
            }

            for (String[] infoDetails : splitInfoDetails) {
                String prefix = infoDetails[0].toLowerCase();
                if (PREFIX_TIMING.equalsIgnoreCase(prefix)) {
                    ExceptionType exception = ExceptionType.EXCEPTION_INVALID_LIST_TIMING_FORMAT;
                    details = checkBlank(infoDetails[1], exception);
                } else {
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
            try {
                String[] timings = details.split(TIMING_SPLIT_DELIMITER);
                if (timings.length == 1) {
                    year = Integer.parseInt(timings[0]);
                    if (year <= 1000 || year > 3000) {
                        throw new SystemException(ExceptionType.EXCEPTION_SEARCH_DATE_OUT_OF_RANGE);
                    } else {
                        return new ListEventCommand(year);
                    }
                } else {
                    year = Integer.parseInt(timings[0]);
                    month = Integer.parseInt(timings[1]);
                }
                if (year <= ListEventCommand.SMALLEST_YEAR || year >= ListEventCommand.LARGEST_YEAR
                        || month <= ListEventCommand.SMALLEST_MONTH || month >= ListEventCommand.LARGEST_MONTH) {
                    throw new SystemException(ExceptionType.EXCEPTION_SEARCH_DATE_OUT_OF_RANGE);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_LIST_TIMING_FORMAT);
            }
            return new ListEventCommand(year, month);
        }
    }
}
