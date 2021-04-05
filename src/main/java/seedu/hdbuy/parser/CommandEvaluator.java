package seedu.hdbuy.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.hdbuy.common.CommandKey;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.exception.InvalidInputException;
import seedu.hdbuy.common.exception.InvalidParameterException;

public class CommandEvaluator {
    private static final String LEASE_REMAINING = "lease_remaining";
    private static final String TYPE = "type";
    private static final String LOCATION = "location";
    private static final String[] types = new String[]{"executive", "5 room", "4 room", "3 room", "2 room", "1 room"};

    public static CommandKey extractInfo(String fullCommand) throws InvalidParameterException, InvalidInputException {
        List<String> lineParts = Arrays.asList(fullCommand.split("\\s"));
        // Finds first non-empty input, else throws exception
        String keyCommand = lineParts.stream().filter(text -> !text.isEmpty())
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException(fullCommand));
        int indexOfKeyCommand = lineParts.indexOf(keyCommand);
        List<String> commandBody = lineParts.subList(indexOfKeyCommand + 1, lineParts.size());
        switch (keyCommand) {
        case CommandType.FILTER:
            return evaluateFilter(lineParts, commandBody, keyCommand);
        case CommandType.SORT:
            return evaluateSort(lineParts, keyCommand);
        case CommandType.REMOVE:
        case CommandType.SAVE:
            return evaluateEditShortlist(commandBody, keyCommand);
        case CommandType.HELP:
        case CommandType.FIND:
        case CommandType.LIST:
        case CommandType.SHORTLIST:
        case CommandType.CLEAR:
        case CommandType.EXIT:
            return evaluateNonParameterCommands(commandBody, keyCommand);
        default:
            throw new InvalidInputException(fullCommand);
        }
    }

    private static CommandKey evaluateNonParameterCommands(List<String> commandBody, String keyCommand) throws
            InvalidParameterException {
        if (!commandBody.isEmpty()) {
            throw new InvalidParameterException(keyCommand);
        }
        return new CommandKey(keyCommand);
    }

    private static CommandKey evaluateFilter(List<String> lineParts, List<String> commandBody, String keyCommand) throws
            InvalidParameterException {
        String filterCriteria = commandBody.stream().filter(text -> !text.isEmpty())
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException(keyCommand));
        int indexOfFilterCriteria = lineParts.indexOf(filterCriteria);
        List<String> filterValueBody = lineParts.subList(indexOfFilterCriteria + 1, lineParts.size());
        String filterValue = filterValueBody.stream().filter(text -> !text.isEmpty())
                .collect(Collectors.joining(" "));
        HdBuyLogger.info(String.format("%s : %s - %s", keyCommand, filterCriteria, filterValue));
        if (!isValidFilterParameter(filterCriteria, filterValue)) {
            throw new InvalidParameterException(keyCommand);
        }
        return new CommandKey(filterCriteria, filterValue, keyCommand);
    }

    private static CommandKey evaluateSort(List<String> lineParts, String keyCommand) throws InvalidParameterException {
        String sortCommand = lineParts.stream().filter(text -> !text.isEmpty())
                .collect(Collectors.joining(" "));
        if (!sortCommand.equals(String.format("%s %s", CommandType.SORT, CommandType.SORT_ASC))
                && !sortCommand.equals(String.format("%s %s", CommandType.SORT, CommandType.SORT_DESC))) {
            throw new InvalidParameterException(keyCommand);
        }
        String sortValue = sortCommand.split("\\s")[1];
        HdBuyLogger.info(String.format("%s : %s", keyCommand, sortValue));
        return new CommandKey(keyCommand, sortValue);
    }

    private static CommandKey evaluateEditShortlist(List<String> commandBody, String keyCommand) throws
            InvalidParameterException {
        if (commandBody.isEmpty()) {
            throw new InvalidParameterException(keyCommand);
        }
        String targetIndex = commandBody.stream().filter(text -> !text.isEmpty())
                .collect(Collectors.joining(" "));
        try {
            Integer.parseInt(targetIndex);
            HdBuyLogger.info(String.format("%s : %s", keyCommand, targetIndex));
            return new CommandKey(keyCommand, targetIndex);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException(keyCommand);
        }
    }

    private static boolean isValidFilterParameter(String criteria, String input) {
        if (input.isEmpty()) {
            return false;
        }
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
        case LOCATION:
            return true;
        default:
            return false;
        }
    }
}
