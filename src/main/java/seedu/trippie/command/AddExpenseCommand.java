package seedu.trippie.command;

import seedu.trippie.ExpenseComparator;
import seedu.trippie.Ui;
import seedu.trippie.data.Expense;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.util.List;

public class AddExpenseCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "Incorrect format for [buy] command! Please try the following:\n"
            + "Format: buy /n ITEM_NAME /d DAY_NUMBER /c FINAL_COST\n"
            + "Example: buy /n R&B Brown Sugar  /d 2 /c 3.00";
    private static final String PARAMETER_ERROR_MESSAGE = "Please check that your FINAL_COST and DAY_NUMBER parameters"
            + " are in \nnumerical form.";
    private static final String NEGATIVE_COST_MESSAGE = "Trippie doesn't know how to deal with negative cost.";
    private static final String NEGATIVE_DAY_MESSAGE = "Trippie doesn't know how to deal with negative days.";

    private final String expenseName;
    private final Float expenseCost;
    private final int expenseDayBought;

    /**
     * Takes in the add input command from the user.
     * Identifies and set the item name, item cost and day bought into expenseName, expenseCost and expenseDayBought
     * respectively.
     *
     * @param userInput Command input by the user
     * @throws TrippieInvalidArgumentException if input has incorrect format, cost and days has incorrect parameters.
     */
    public AddExpenseCommand(String userInput) throws TrippieInvalidArgumentException {
        try {
            this.expenseName = extractExpenseName(userInput);
            this.expenseCost = extractExpenseCost(userInput);
            this.expenseDayBought = extractDayBought(userInput);
            char[] characters = userInput.toCharArray();
            if (characters[3] != ' ') {
                throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            throw new TrippieInvalidArgumentException(PARAMETER_ERROR_MESSAGE);
        }
    }

    /**
     * Extracts the item name from the command inputted by the user.
     *
     * @param userInput Command input by the user.
     * @return Input with only the item name.
     */
    public String extractExpenseName(String userInput) {
        String inputWithoutCostAndDay = userInput.split(" /d ")[0];
        return inputWithoutCostAndDay.split(" /n ")[1];
    }

    /**
     * Extracts the item cost from the command inputted by the user.
     *
     * @param userInput Command input by the user.
     * @return Input with only the item cost.
     * @throws TrippieInvalidArgumentException if negative cost is inputted in the command.
     */
    public Float extractExpenseCost(String userInput) throws TrippieInvalidArgumentException {
        String onlyCost = userInput.split(" /c ")[1];
        if (onlyCost.contains("$")) {
            onlyCost = onlyCost.replace("$", "");
        }
        if (Float.parseFloat(onlyCost) < 0) {
            throw new TrippieInvalidArgumentException(NEGATIVE_COST_MESSAGE);
        }
        return Float.parseFloat(onlyCost);
    }

    /**
     * Extracts the day item was bought from the command inputted by the user.
     *
     * @param userInput Command input by the user.
     * @return Input with only the day bought.
     * @throws TrippieInvalidArgumentException if negative day is inputted in the command.
     */
    public int extractDayBought(String userInput) throws TrippieInvalidArgumentException {
        String inputWithoutCost = userInput.split(" /c ")[0];
        String onlyDay = inputWithoutCost.split(" /d ")[1];
        if (Integer.parseInt(onlyDay) <= 0) {
            throw new TrippieInvalidArgumentException(NEGATIVE_DAY_MESSAGE);
        }
        return Integer.parseInt(onlyDay);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out successful add item message and add item from the expenseList.
     *
     * @param ui User Interface of the program.
     * @param trippieData The current trip expenseList that would updated.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        List<Expense> expenses = trippieData.getCurrentTrip().getExpenseListObject().getExpenseList();
        String currencyAbbreviation = trippieData.getCurrentTrip().getExpenseListObject().getCurrencyAbbreviation();
        Expense expenseEntry = new Expense(expenseName, expenseCost, expenseDayBought);
        expenses.add(expenseEntry);
        if (expenses.size() > 1) {
            sortExpenseList(expenses);
        }
        System.out.println("Got it! I've added the following item: " + expenseEntry.toString()
                + " " + currencyAbbreviation);
        System.out.printf("Now you have %d %s in the list.%n", expenses.size(), expenses.size() > 1 ? "items" : "item");
        trippieData.getCurrentTrip().getExpenseListObject().setExpenseList(expenses);
    }

    /**
     * Sorts the expenseList everytime new expense is added.
     *
     * @param expenseList list of expenses to be sorted.
     */
    public void sortExpenseList(List<Expense> expenseList) {
        expenseList.sort(new ExpenseComparator());
    }
}
