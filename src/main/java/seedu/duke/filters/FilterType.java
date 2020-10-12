package seedu.duke.filters;

import seedu.duke.exceptions.FilterCommandException;

public enum FilterType {
    WORD_TYPE,
    STARTING_STRING,
    CONTAINING_STRING;

    public static FilterType getTypeOfFilter(String command) throws FilterCommandException {
        if (!command.toLowerCase().contains("by\\")) {
            throw new FilterCommandException();
        }

        int indexOfTypeTag = command.indexOf("by\\");
        String cutCommand = command.substring(indexOfTypeTag);
        cutCommand = cutCommand.replace("by\\", "");
        FilterType filterType;
        if (cutCommand.toLowerCase().trim().startsWith("type")) {
            filterType = FilterType.WORD_TYPE;
        } else if (cutCommand.toLowerCase().trim().startsWith("start")) {
            filterType = FilterType.STARTING_STRING;
        } else if (cutCommand.toLowerCase().trim().startsWith("include")) {
            filterType = FilterType.CONTAINING_STRING;
        } else {
            throw new FilterCommandException();
        }

        return filterType;
    }
}
