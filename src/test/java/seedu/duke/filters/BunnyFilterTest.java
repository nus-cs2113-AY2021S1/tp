package seedu.duke.filters;

import org.junit.jupiter.api.Test;

import seedu.duke.bunnylist.BunnyList;
import seedu.duke.exceptions.CommandInvalidException;
import seedu.duke.exceptions.MissingFilterOptionsException;
import seedu.duke.exceptions.NoFilteredItemsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.bunnylist.BunnyListTest.initializeBunnyListTestDatabase;


class BunnyFilterTest {

    @Test
    void filterBunny_filterIdeaTerm_getTwoResults() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        try {
            BunnyFilter.filterBunny("filter bunny i\\ fun", bunniesList);
        } catch (MissingFilterOptionsException e) {
            e.printStackTrace();
        } catch (NoFilteredItemsException e) {
            e.printStackTrace();
        } catch (CommandInvalidException e) {
            e.printStackTrace();
        }
        assertEquals(2, BunnyFilter.filteredBunny.size());
    }

    @Test
    void filterBunny_filterGenreTerm_getTwoResults() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        try {
            BunnyFilter.filterBunny("filter bunny g\\ awesome", bunniesList);
        } catch (MissingFilterOptionsException e) {
            e.printStackTrace();
        } catch (NoFilteredItemsException e) {
            e.printStackTrace();
        } catch (CommandInvalidException e) {
            e.printStackTrace();
        }
        assertEquals(2, BunnyFilter.filteredBunny.size());
    }

    @Test
    void filterBunny_filterIdeaAndGenreTerm_getOneResult() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        try {
            BunnyFilter.filterBunny("filter bunny i\\ some g\\fun ", bunniesList);
        } catch (MissingFilterOptionsException e) {
            e.printStackTrace();
        } catch (NoFilteredItemsException e) {
            e.printStackTrace();
        } catch (CommandInvalidException e) {
            e.printStackTrace();
        }
        assertEquals(1, BunnyFilter.filteredBunny.size());
    }
}