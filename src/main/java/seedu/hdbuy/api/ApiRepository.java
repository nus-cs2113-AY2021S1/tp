package seedu.hdbuy.api;

import java.util.HashMap;

import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.exception.EmptyResponseException;

public class ApiRepository {

    /**
     * Gets all units from database matching user's filter criteria.
     * If there are no units retrieved, returns empty map.
     *
     * @param inputs User's filter criteria.
     */
    public static void fetchUnits(HashMap<QueryKey, String> inputs) {
        assert !inputs.isEmpty() : "User did not add any parameters";
        String query = QueryFormatter.formQuery(inputs);
        assert !query.isEmpty() : "Query is empty";
        try {
            GetRequest.getResponse(query);
        } catch (EmptyResponseException emptyResponseException) {
            HdBuyLogger.warning(emptyResponseException.getMessage());
        }
    }
}
