package seedu.trippie;

import seedu.trippie.command.AddExpenseCommand;
import seedu.trippie.command.AddPlaceCommand;
import seedu.trippie.command.BudgetCommand;
import seedu.trippie.command.CalculateCurrencyCommand;
import seedu.trippie.command.Command;
import seedu.trippie.command.DeleteExpenseCommand;
import seedu.trippie.command.DeletePlaceCommand;
import seedu.trippie.command.DeleteTripCommand;
import seedu.trippie.command.EditTripCommand;
import seedu.trippie.command.ExitCommand;
import seedu.trippie.command.HelpCommand;
import seedu.trippie.command.ListExpenseCommand;
import seedu.trippie.command.ListPlacesCommand;
import seedu.trippie.command.LoadTripCommand;
import seedu.trippie.command.NewTripCommand;
import seedu.trippie.command.SearchCommand;
import seedu.trippie.exception.TrippieIllegalCommandException;
import seedu.trippie.exception.TrippieInvalidArgumentException;

public class Parser {

    private static final String ERROR_MESSAGE = "Invalid Command! Type \"help\" to view the commands available!";

    /**
     * Parses user input before calling specified command.
     *
     * @param userInput Command input by the user.
     * @return different methods depending on command input by user.
     */
    public static Command parse(String userInput) {
        try {
            if (userInput.trim().equals("exit")) {
                return new ExitCommand();
            } else if (userInput.startsWith("buy")) {
                return new AddExpenseCommand(userInput);
            } else if (userInput.startsWith("delete /e")) {
                return new DeleteExpenseCommand(userInput);
            } else if (userInput.trim().equals("list /e")) {
                return new ListExpenseCommand();
            } else if (userInput.startsWith("budget")) {
                return new BudgetCommand(userInput);
            } else if (userInput.startsWith("convert /to")) {
                return new CalculateCurrencyCommand(userInput);
            } else if (userInput.startsWith("add ")) {
                return new AddPlaceCommand(userInput);
            } else if (userInput.startsWith("delete /p")) {
                return new DeletePlaceCommand(userInput);
            } else if (userInput.trim().equals("help")) {
                return new HelpCommand();
            } else if (userInput.startsWith("list /p")) {
                return new ListPlacesCommand(userInput);
            } else if (userInput.startsWith("search")) {
                return new SearchCommand(userInput);
            } else if (userInput.trim().equals("new trip")) {
                return new NewTripCommand();
            } else if (userInput.trim().equals("load trip")) {
                return new LoadTripCommand();
            } else if (userInput.trim().equals("edit trip")) {
                return new EditTripCommand();
            } else if (userInput.trim().equals("delete trip")) {
                return new DeleteTripCommand();
            } else {
                throw new TrippieIllegalCommandException(ERROR_MESSAGE);
            }
        } catch (TrippieIllegalCommandException | TrippieInvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
