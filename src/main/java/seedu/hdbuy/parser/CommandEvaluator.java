package seedu.hdbuy.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.hdbuy.common.CommandKey;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidParameterException;

public class CommandEvaluator {

    public static CommandKey extractInfo(String fullCommand) throws InvalidParameterException, InvalidIndexException {
        String[] lineParts;
        lineParts = fullCommand.split(" ");
        String keyCommand = lineParts[0];
        HdBuyLogger.info(Arrays.toString(lineParts));
        switch (keyCommand) {
        case CommandType.FILTER:
            if (lineParts.length < 3) {
                throw new InvalidParameterException(keyCommand);
            }
            String filterCriteria = lineParts[1];
            String filterValue = String.join(" ", Arrays.asList(lineParts).subList(2, lineParts.length));
            HdBuyLogger.info(String.format("%s : %s - %s", keyCommand, filterCriteria, filterValue));
            if (isValidFilterParameter(filterCriteria, filterValue)) {
                return new CommandKey(filterCriteria, filterValue, keyCommand);
            }
            throw new InvalidParameterException(keyCommand);
        case CommandType.SORT:
            if (lineParts.length < 2) {
                throw new InvalidParameterException(keyCommand);
            }
            List<String> rawSortValue = new ArrayList<>(Arrays.asList(lineParts).subList(1, lineParts.length));
            rawSortValue.removeAll(Collections.singleton(""));
            HdBuyLogger.info(String.format("%s : %s", keyCommand, rawSortValue.toString()));
            String sortValue = rawSortValue.get(0);
            if (sortValue.equals(CommandType.SORT_ASC) || sortValue.equals(CommandType.SORT_DESC)) {
                return new CommandKey(keyCommand, sortValue);
            }
            throw new InvalidParameterException(keyCommand);
        case CommandType.REMOVE:
        case CommandType.SAVE:
            if (lineParts.length < 2) {
                throw new InvalidParameterException(keyCommand);
            }
            List<String> editRawValue = new ArrayList<>(Arrays.asList(lineParts).subList(1, lineParts.length));
            editRawValue.removeAll(Collections.singleton(""));
            HdBuyLogger.info(String.format("%s : %s", keyCommand, editRawValue.toString()));
            String editValue = editRawValue.get(0);
            if (isValidIndex(editValue)) {
                return new CommandKey(keyCommand, editValue);
            }
            throw new InvalidIndexException(editValue);
        default:
            // any other commands
            if (lineParts.length != 1) {
                throw new InvalidParameterException(keyCommand);
            }
        }
        return new CommandKey(keyCommand);
    }

    private static boolean isValidIndex(String input) {
        try {
            int index = Integer.parseInt(input);
            return index <= 100 && index >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidFilterParameter(String criteria, String input) {
        final String LEASE_REMAINING = "lease_remaining";
        final String TYPE = "type";
        String[] types = new String[]{"executive", "5 room", "4 room", "3 room", "2 room", "1 room"};
        switch (criteria) {
        case LEASE_REMAINING:
            try {
                int index = Integer.parseInt(input);
                return index <= 99 && index >= 0;
            } catch (NumberFormatException e) {
                return false;
            }
        case TYPE:
            return Arrays.asList(types).contains(input);
        default:
            return false;
        }
    }
}
