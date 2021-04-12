package seedu.hdbuy.command;

import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.ui.TextUi;

import java.util.LinkedHashMap;

public class FilterCommand extends Command {

    protected String criteria;
    protected String value;

    /**
     * Input the criteria and value for the filter parameter.
     *
     * @param criteria The filter in which the search is going to be based on.
     * @param value    The value of the filter.
     */
    public FilterCommand(String criteria, String value) {
        this.criteria = criteria;
        this.value = value;
    }

    @Override public void execute() {
        LinkedHashMap<QueryKey, String> inputs = UserInput.getInputs();
        switch (criteria) {
        case "location":
            inputs.put(QueryKey.LOCATION, value);
            break;
        case "type":
            inputs.put(QueryKey.TYPE, value);
            break;
        case "lease_remaining":
            inputs.put(QueryKey.LEASE_REMAINING, value);
            break;
        default:
            return;
        }
        TextUi.showParameters(inputs);
    }
}
