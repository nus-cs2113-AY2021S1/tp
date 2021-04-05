package seedu.hdbuy.parser;

import seedu.hdbuy.command.ClearCommand;
import seedu.hdbuy.command.CloseCommand;
import seedu.hdbuy.command.Command;
import seedu.hdbuy.command.DefaultCommand;
import seedu.hdbuy.command.FilterCommand;
import seedu.hdbuy.command.FindCommand;
import seedu.hdbuy.command.HelpCommand;
import seedu.hdbuy.command.ListCommand;
import seedu.hdbuy.command.RemoveCommand;
import seedu.hdbuy.command.SaveCommand;
import seedu.hdbuy.command.ShortlistCommand;
import seedu.hdbuy.command.SortCommand;
import seedu.hdbuy.common.CommandKey;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.exception.EmptyInputException;
import seedu.hdbuy.common.exception.InvalidIndexException;
import seedu.hdbuy.common.exception.InvalidParameterException;
import seedu.hdbuy.ui.TextUi;

public class Parser {

    public static Command parse(String fullLine) throws EmptyInputException {
        if (fullLine.trim().isEmpty()) {
            throw new EmptyInputException();
        }
        try {
            CommandKey keyCommand = CommandEvaluator.extractInfo(fullLine);
            switch (keyCommand.getCommand()) {
            case CommandType.HELP:
                return new HelpCommand();
            case CommandType.FILTER:
                String criteria = keyCommand.getCriteria();
                String value = keyCommand.getValue();
                return new FilterCommand(criteria, value);
            case CommandType.FIND:
                return new FindCommand();
            case CommandType.EXIT:
                return new CloseCommand();
            case CommandType.CLEAR:
                return new ClearCommand();
            case CommandType.LIST:
                return new ListCommand();
            case CommandType.SHORTLIST:
                return new ShortlistCommand();
            case CommandType.SAVE:
                int addIndex = Integer.parseInt(keyCommand.getValue());
                return new SaveCommand(addIndex);
            case CommandType.REMOVE:
                int removeIndex = Integer.parseInt(keyCommand.getValue());
                return new RemoveCommand(removeIndex);
            case CommandType.SORT:
                String sortCriteria = keyCommand.getValue();
                return new SortCommand(sortCriteria);
            }
        } catch (InvalidParameterException e) {
            HdBuyLogger.error(e.getMessage());
            TextUi.showInvalidParameter(e.getKeyCommand(), e);
        }
        return new DefaultCommand();
    }
}
