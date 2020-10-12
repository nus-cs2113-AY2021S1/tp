package seedu.eduke8.parser;

import seedu.eduke8.command.Command;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;

public interface Parser {
    Command parseCommand(DisplayableList displayableList, String userInput) throws Eduke8Exception;
}
