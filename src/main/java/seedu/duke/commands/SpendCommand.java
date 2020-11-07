package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.ListType;
import seedu.duke.model.Model;
import seedu.duke.model.item.Expense;
import seedu.duke.model.itemlist.ExpenseList;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SpendCommand extends Command {

    public static final String COMMAND_WORD = "spend";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an expense item to the expense list.\n"
            + "     Parameters: DESCRIPTION <compulsory/optional argument>\n"
            + "     List of <compulsory arguments>:\n"
            + "       - v/<value> sets the value of the expense item.\n"
            + "     List of <compulsory arguments>:\n"
            + "       - currency/<currency> sets the currency of the expense item.\n"
            + "       - date/<yyyy-MM-dd> sets the date of the expense item.\n"
            + "     Example: " + COMMAND_WORD + " example_expense_item v/5.0 currency/SGD date/2020-11-07";
    public static final HashSet<String> ALLOWED_ARGUMENTS = new HashSet<>(Arrays.asList("v", "currency", "date"));

    private String description;
    private HashMap<String, String> argumentsMap;

    public SpendCommand(String description, HashMap<String, String> argumentsMap) {
        this.description = description;
        this.argumentsMap = argumentsMap;
    }

    @Override
    public void execute(Model model) throws DukeException {
        ExpenseList expenses = (ExpenseList) model.getList(ListType.EXPENSE_LIST);
        if (!argumentsMap.containsKey("v")) {
            throw new DukeException(Messages.EXCEPTION_SPEND_ARGUMENTS);
        }
        Double value = 0.0;
        try {
            value = Double.valueOf(argumentsMap.get("v"));
        } catch(NumberFormatException e) {
            throw new DukeException(Messages.EXCEPTION_INVALID_VALUE);
        }
        Expense newExpense = new Expense(description, value);
        if (argumentsMap.containsKey("currency")) {
            newExpense.setCurrency(argumentsMap.get("currency"));
        }
        if (argumentsMap.containsKey("date")) {
            LocalDate date;
            try {
                date = LocalDate.parse(argumentsMap.get("date"));
                newExpense.setDate(date);
            } catch (DateTimeException e) {
                throw new DukeException(Messages.EXCEPTION_WRONG_DATE_FORMAT);
            }
        }
        expenses.addExpense(newExpense);
    }
}
