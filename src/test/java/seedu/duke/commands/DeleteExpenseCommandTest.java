package seedu.duke.commands;

//@@author GuoAi

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.model.ListType;
import seedu.duke.model.item.Expense;
import seedu.duke.model.itemlist.ExpenseList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteExpenseCommandTest extends CommandTest {

    private Double testValue = 0.0;
    private String testValueString = "0.0";
    private int index = 0;

    @Test
    void execute_validIndex_deleteExpensesWithIndex() throws DukeException {
        resetFields();

        ExpenseList expenses = (ExpenseList) model.getList(ListType.EXPENSE_LIST);
        expenses.addItem(new Expense(TEST_DESCRIPTION, testValue));

        assertEquals(1, expenses.size());

        new DeleteExpenseCommand(index + 1).execute(model);

        assertEquals(0, expenses.size());
    }
}
