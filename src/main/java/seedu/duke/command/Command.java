package seedu.duke.command;

import seedu.duke.RepaymentList;
import seedu.duke.SpendingList;
import seedu.duke.Ui;
import seedu.duke.exceptions.InvalidMonthException;

import java.io.IOException;

public class Command {
    public void execute(SpendingList spendingList, Ui ui) throws IOException,
            InvalidMonthException {
    }

    public void execute(RepaymentList repaymentList, Ui ui) throws IOException {
    }

    public void execute(SpendingList spendingList, RepaymentList repaymentList, Ui ui) throws IOException {
    }

    public boolean isExit() {
        return false;
    }
}
