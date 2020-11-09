package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.data.SpendingList;
import seedu.duke.exceptions.InvalidMonthException;
import seedu.duke.exceptions.InvalidYearException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;


public class SummaryCommandTest {
    public static ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static SpendingList initSpendingList(Item... items) {
        return new SpendingList(initList(items));
    }

    SpendingList spendingList = initSpendingList(
        new Item("noodle", "S$", 1.5),
        new Item("fish", "S$", 10),
        new Item("books", "S$", 8.9)
    );

    @Test
    public void goodSummary() throws InvalidMonthException, InvalidYearException {
        Ui ui = new Ui();
        SummaryCommand validSummaryMonth = new SummaryCommand("2020", "Jan");
        SummaryCommand summaryYear = new SummaryCommand("2020", null);
        SummaryCommand summary = new SummaryCommand();
        Data data = new Data(null, null, null);
        validSummaryMonth.execute(data, ui);
        summaryYear.execute(data, ui);
        summary.execute(data, ui);
    }
}
