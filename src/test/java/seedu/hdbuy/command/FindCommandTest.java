package seedu.hdbuy.command;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.data.UserInput;

class FindCommandTest {

    @Test void findExecuteTest() {
        UserInput.getInputs().clear();
        FindCommand findCommand = new FindCommand();
        findCommand.execute(); // catch EmptyParameterException

        UserInput.getInputs().clear();
        UserInput.getInputs().put(QueryKey.LEASE_REMAINING, "101");
        findCommand.execute(); // catch NoFlatsException

        UserInput.getInputs().clear();
        UserInput.getInputs().put(QueryKey.LOCATION, "jurong");
        findCommand.execute();
    }
}