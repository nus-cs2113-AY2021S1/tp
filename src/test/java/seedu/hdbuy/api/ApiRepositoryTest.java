package seedu.hdbuy.api;

import org.junit.jupiter.api.Test;
import seedu.hdbuy.common.QueryKey;
import seedu.hdbuy.common.exception.NoSearchException;
import seedu.hdbuy.data.SearchedUnits;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ApiRepositoryTest {

    @Test void fetchUnits() {
        LinkedHashMap<QueryKey, String> inputs = new LinkedHashMap<>();
        inputs.put(QueryKey.LOCATION, "ang mo kio");
        inputs.put(QueryKey.TYPE, "4 room");
        inputs.put(QueryKey.LEASE_REMAINING, "80");
        ApiRepository.fetchUnits(inputs);
        try {
            assertNotNull(SearchedUnits.getSearchedUnits());
        } catch (NoSearchException e) {
            fail();
        }
    }

    @Test public void testEmptyResponseException() {
        LinkedHashMap<QueryKey, String> inputs = new LinkedHashMap<>();
        inputs.put(QueryKey.LEASE_REMAINING, "101");
        try {
            SearchedUnits.clearSearchedUnits();
            ApiRepository.fetchUnits(inputs);
            assertTrue(SearchedUnits.getSearchedUnits().isEmpty());
        } catch (NoSearchException e) {
            fail();
        }
    }
}