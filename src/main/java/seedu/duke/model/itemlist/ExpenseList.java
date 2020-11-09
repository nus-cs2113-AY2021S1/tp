

package seedu.duke.model.itemlist;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.item.Expense;
import seedu.duke.model.item.TotalExpenseType;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

// @@author GuoAi

/**
 * Represents a list of expense items.
 */
public class ExpenseList extends ItemList<Expense> {

    public ExpenseList() {
        items = new ArrayList<>();
    }

    public ExpenseList(ArrayList<Expense> expenses) {
        super(expenses);
    }

    /**
     * Adds an expense item to the expense list from the parameters.
     *
     * @param expense Expense item to be added to the expense list.
     */
    public void addExpense(Expense expense) {
        items.add(expense);
        Ui.dukePrint(Messages.MESSAGE_ADD_EXPENSE + expense.toString() + Messages.MESSAGE_STATUS_FIRST
                + items.size() + Messages.MESSAGE_EXPENSE_STATUS_LAST);
    }

    /**
     * Deletes all the expense items in {@code expensesDeleted} from the expense list and print out the expense items
     * deleted.
     *
     * @param expensesDeleted expense items to be deleted from the expense list
     */
    public void deleteExpenses(HashSet<Expense> expensesDeleted) {
        Ui.showLine();
        Ui.dukePrintMultiple(Messages.MESSAGE_DELETE_EXPENSE);
        for (Expense expense : expensesDeleted) {
            items.remove(expense);
            Ui.dukePrintMultiple(expense.toString());
        }
        Ui.showLine();
        Ui.showEmptyLine();
    }

    /**
     * Clears all the expense items in the list.
     */
    public void clearExpense() {
        items = new ArrayList<>();
    }

    /**
     * Lists all the expense items in the list and summary values for today, this week, this month, and this year.
     */
    @Override
    public void listTask() {
        if (items.size() == 0) {
            Ui.dukePrint(Messages.MESSAGE_EMPTY_EXPENSE_LIST);
            return;
        }
        items = sortExpenseList(items);
        Ui.showLine();
        Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST);
        int count = 0;
        for (Expense expense : items) {
            count++;
            Ui.dukePrintMultiple(count + ". " + expense.toString());
        }
        ArrayList<Expense> selectedExpenses = new ArrayList<>();
        try {
            Ui.showEmptyLine();
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_DAY);
            selectedExpenses = getExpenseItems(TotalExpenseType.DAY);
            HashMap<String, Double> dayMap = getTotalExpense(selectedExpenses);
            for (String currency : dayMap.keySet()) {
                Ui.dukePrintMultiple(String.format("%.2f %s", dayMap.get(currency), currency));
            }
            Ui.showEmptyLine();
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_WEEK);
            selectedExpenses = getExpenseItems(TotalExpenseType.WEEK);
            HashMap<String, Double> weekMap = getTotalExpense(selectedExpenses);
            for (String currency : weekMap.keySet()) {
                Ui.dukePrintMultiple(String.format("%.2f %s", weekMap.get(currency), currency));
            }
            Ui.showEmptyLine();
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_MONTH);
            selectedExpenses = getExpenseItems(TotalExpenseType.MONTH);
            HashMap<String, Double> monthMap = getTotalExpense(selectedExpenses);
            for (String currency : monthMap.keySet()) {
                Ui.dukePrintMultiple(String.format("%.2f %s", monthMap.get(currency), currency));
            }
            Ui.showEmptyLine();
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_YEAR);
            selectedExpenses = getExpenseItems(TotalExpenseType.YEAR);
            HashMap<String, Double> yearMap = getTotalExpense(selectedExpenses);
            for (String currency : yearMap.keySet()) {
                Ui.dukePrintMultiple(String.format("%.2f %s", yearMap.get(currency), currency));
            }
            Ui.showLine();
            Ui.showEmptyLine();
        } catch (DukeException e) {
            Ui.dukePrintMultiple(e.toString());
        }
    }

    /**
     * Lists all the expense items with the given currency.
     *
     * @param currency the currency to be used to select expense items to be listed
     */
    public void listExpense(String currency) {
        ArrayList<Expense> expensesListed = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getCurrency().equals(currency)) {
                expensesListed.add(items.get(i));
            }
        }
        expensesListed = sortExpenseList(expensesListed);
        if (expensesListed.size() == 0) {
            Ui.dukePrint(Messages.MESSAGE_EMPTY_EXPENSE_LIST);
            return;
        }
        Ui.showLine();
        Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_CURRENCY + currency + ":");
        expensesListed.forEach(expense -> Ui.dukePrintMultiple(expense.toString()));
        printSummary(expensesListed);
        Ui.showLine();
        Ui.showEmptyLine();
    }

    /**
     * Lists all the expense items with the given date.
     *
     * @param date the date to be used to select expense items to be listed
     */
    public void listExpense(LocalDate date) {
        ArrayList<Expense> expensesListed = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getDate().toString().equals(date.toString())) {
                expensesListed.add(items.get(i));
            }
        }
        expensesListed = sortExpenseList(expensesListed);
        if (expensesListed.size() == 0) {
            Ui.dukePrint(Messages.MESSAGE_EMPTY_EXPENSE_LIST);
            return;
        }
        Ui.showLine();
        Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_DATE
                + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ":");
        expensesListed.forEach(expense -> Ui.dukePrintMultiple(expense.toString()));
        printSummary(expensesListed);
        Ui.showLine();
        Ui.showEmptyLine();
    }

    /**
     * Lists all the expense items wit the given totalExpenseType.
     *
     * @param totalExpenseType the {@code TotalExpenseType} to be used to select expense items to be listed
     * @throws DukeException if totalExpenseType if invalid.
     */
    public void listExpense(TotalExpenseType totalExpenseType) throws DukeException {
        ArrayList<Expense> expensesListed = getExpenseItems(totalExpenseType);
        expensesListed = sortExpenseList(expensesListed);
        if (expensesListed.size() == 0) {
            Ui.dukePrint(Messages.MESSAGE_EMPTY_EXPENSE_LIST);
            return;
        }
        Ui.showLine();
        switch (totalExpenseType) {
        case DAY:
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_DATERANGE + "today:");
            break;
        case WEEK:
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_DATERANGE + "this week:");
            break;
        case MONTH:
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_DATERANGE + "this month:");
            break;
        case YEAR:
            Ui.dukePrintMultiple(Messages.MESSAGE_EXPENSE_LIST_DATERANGE + "this year:");
            break;
        default:
            throw new DukeException(Messages.EXCEPTION_EXPENSE_DATERANGE);
        }
        expensesListed.forEach(expense -> Ui.dukePrintMultiple(expense.toString()));
        printSummary(expensesListed);
        Ui.showLine();
        Ui.showEmptyLine();
    }

    /**
     * Prints the total amount of all the expense items listed.
     *
     * @param expensesListed the expense list to be listed
     */
    public void printSummary(ArrayList<Expense> expensesListed) {
        Ui.showEmptyLine();
        HashMap<String, Double> currencyMap = getTotalExpense(expensesListed);
        Ui.dukePrintMultiple(Messages.MESSAGE_TOTAL_EXPENSE);
        for (String key : currencyMap.keySet()) {
            Ui.dukePrintMultiple(String.format("%.2f %s", currencyMap.get(key), key));
        }
    }

    /**
     * Gets the total value of expenses for each currency.
     *
     * @param selectedExpenses An ArrayList of Expense representing the expenses items selected
     * @return a hashmap where the key is the currency and value is the total value of expenses for the currency.
     */
    public HashMap<String, Double> getTotalExpense(ArrayList<Expense> selectedExpenses) {
        HashMap<String, Double> totalMap = new HashMap<>();
        for (Expense item : selectedExpenses) {
            if (!totalMap.containsKey(item.getCurrency())) {
                // Currency does not exist in the hashmap
                totalMap.put(item.getCurrency(), item.getValue());
            } else {
                totalMap.put(item.getCurrency(), totalMap.get(item.getCurrency()) + item.getValue());
            }
        }
        return totalMap;
    }

    /**
     * Retrieves all the expense items in the date range.
     *
     * @param totalExpenseType a {@code TotalExpenseType} value, i.e. DAY, WEEK, MONTH, or YEAR
     * @return an ArrayList of expense items in the date range.
     * @throws DukeException when the argument {@code totalExpenseType} is invalid.
     */
    public ArrayList<Expense> getExpenseItems(TotalExpenseType totalExpenseType) throws DukeException {
        LocalDate today = LocalDate.now();
        ArrayList<Expense> resultExpenses = new ArrayList<>();
        switch (totalExpenseType) {
        case DAY:
            resultExpenses = new ArrayList<>();
            for (Expense item : items) {
                if (item.getDate().compareTo(today) == 0) {
                    resultExpenses.add(item);
                }
            }
            break;
        case WEEK:
            resultExpenses = new ArrayList<>();
            LocalDate monday = today.with(previousOrSame(MONDAY));
            LocalDate sunday = today.with(nextOrSame(SUNDAY));
            for (Expense item : items) {
                if (item.getDate().compareTo(monday) >= 0 && item.getDate().compareTo(sunday) <= 0) {
                    resultExpenses.add(item);
                }
            }
            break;
        case MONTH:
            resultExpenses = new ArrayList<>();
            LocalDate monthBegin = today.withDayOfMonth(1);
            LocalDate monthEnd = today.plusMonths(1).withDayOfMonth(1).minusDays(1);
            for (Expense item : items) {
                if (item.getDate().compareTo(monthBegin) >= 0 && item.getDate().compareTo(monthEnd) <= 0) {
                    resultExpenses.add(item);
                }
            }
            break;
        case YEAR:
            resultExpenses = new ArrayList<>();
            LocalDate firstDay = today.with(firstDayOfYear());
            LocalDate lastDay = today.with(lastDayOfYear());
            for (Expense item : items) {
                if (item.getDate().compareTo(firstDay) >= 0 && item.getDate().compareTo(lastDay) <= 0) {
                    resultExpenses.add(item);
                }
            }
            break;
        default:
            throw new DukeException(Messages.EXCEPTION_EXPENSE_DATERANGE);
        }
        return resultExpenses;
    }

    public ArrayList<Expense> sortExpenseList(ArrayList<Expense> expenses) {
        Collections.sort(expenses);
        return expenses;
    }
}
