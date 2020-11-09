package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Data;
import seedu.duke.data.Item;
import seedu.duke.data.SpendingList;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@author k-walter
public class SpendingListTest {
    @Test
    public void addItem() throws IOException {
        SpendingList expectedList = initSpendingList(
            new Item("buy book", "S$", 10, "")
        );

        SpendingList realList = new SpendingList((Storage) null);
        realList.addItem("buy book", "S$", 10, "");

        assert realList.getListSize() == 1 : "item not added";
        assertEqualList(expectedList, realList);
    }

    public static void assertEqualList(ArrayList<Item> expectedList, ArrayList<Item> actualList, String message) {
        String expectedString = Arrays.toString(expectedList.toArray());
        String realString = Arrays.toString(actualList.toArray());
        assertEquals(expectedString, realString, message);
    }

    /**
     * Converts list to string format, because double comparison fails.
     *
     * @param expectedList expected list of items
     * @param actualList   actual list of items
     */
    public static void assertEqualList(ArrayList<Item> expectedList, ArrayList<Item> actualList) {
        assertEqualList(expectedList, actualList, null);
    }

    public static void assertEqualList(SpendingList expectedList, SpendingList actualList) {
        assertEqualList(expectedList.getSpendingList(), actualList.getSpendingList());
    }

    public static ArrayList<Item> initList(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static SpendingList initSpendingList(Item... items) {
        return new SpendingList(initList(items));
    }

    @Test
    public void deleteItem() throws IOException {
        SpendingList expectedList = initSpendingList(
            new Item("noodle", "S$", 1.5, ""),
            new Item("fish", "S$", 10, ""),
            new Item("books", "S$", 8.9, "")
        );

        SpendingList realList = initSpendingList(
            new Item("rice", "S$", 2, ""),
            new Item("noodle", "S$", 1.5, ""),
            new Item("fish", "S$", 10, ""),
            new Item("books", "S$", 8.9, "")
        );
        realList.deleteItem(0);

        assert realList.getListSize() == 3 : "item is not deleted";
        assertEqualList(expectedList, realList);
    }

    @Test
    public void getItem() {
        SpendingList realList = initSpendingList(
            new Item("buy book", "S$", 10)
        );

        Item firstItem = realList.getItem(0);
        assertEquals(firstItem.getDescription(), "buy book");
        assertEquals(firstItem.getSymbol(), "S$");
        assertEquals(firstItem.getAmount(), 10);
    }

    @Test
    void getListSize() throws IOException {
        SpendingList realList = new SpendingList((Storage) null);

        realList.addItem("buy book", "S$", 10, "");
        assertEquals(realList.getListSize(), 1);

        realList.addItem("buy stationary", "S$", 5, "");
        assertEquals(realList.getListSize(), 2);

        realList.deleteItem(0);
        assertEquals(realList.getListSize(), 1);

        realList.addItem("buy grocery", "S$", 10, "");
        assertEquals(realList.getListSize(), 2);
    }

    @Test
    void getList() {
        ArrayList<Item> expectedList = initList(
            new Item("rice", "S$", 2, ""),
            new Item("noodle", "S$", 1.5, ""),
            new Item("fish", "S$", 10, ""),
            new Item("books", "S$", 8.9, "")
        );

        SpendingList realSL = new SpendingList(expectedList);
        ArrayList<Item> realList = realSL.getSpendingList();

        assertEquals(expectedList, realList, "List not same");
    }

    @Test
    void clearAllItems() throws IOException {
        final SpendingList expectedList = new SpendingList((Storage) null);

        SpendingList realList = initSpendingList(
            new Item("buy book", "S$", 10, ""),
            new Item("buy stationary", "S$", 5, "")
        );
        assertEquals(realList.getListSize(), 2, "List not instantiated with 2 items");
        realList.clearAllItems();

        assert realList.getListSize() == 0 : "List is not cleared";
        assertEqualList(expectedList, realList);
    }

    /*@Test
    void editItem() throws IOException {
        SpendingList realList = initSpendingList(
                new Item("buy book", "S$", 10, "Education")
        );
        realList.editItem(0, 12);

        assertEquals(realList.getItem(0).getAmount(), 12);
    }*/

    @Test
    public void getSpendingAmountTime() {
        double expectedAmount = 22.4;
        String expectedYear = Integer.toString(LocalDate.now().getYear());

        SpendingList realList = initSpendingList(
            new Item("rice", "S$", 2, ""),
            new Item("noodle", "S$", 1.5, ""),
            new Item("fish", "S$", 10, ""),
            new Item("books", "S$", 8.9, "")
        );

        assertEquals(realList.getSpendingAmountTime(expectedYear), expectedAmount);
    }

    //@author k-walter
    @Test
    public void filterByCategory() {
        SpendingList sl = initSpendingList(
            new Item("rice", "SGD", 2, "Food"),
            new Item("pencil", "S$", 1.5)
        );
        ArrayList<Item> expectedSl = initList(
            new Item("rice", "SGD", 2, "Food"),
            new Item("pencil", "S$", 1.5, "Others")
        );
        assertEqualList(expectedSl, sl.getSpendingList());
        ArrayList<Item> expectedFood = initList(new Item("rice", "SGD", 2, "Food"));
        ArrayList<Item> food = sl.filterSpendingList("Food", null);
        assertEqualList(expectedFood, food);
        ArrayList<Item> expectedOther = initList(new Item("pencil", "S$", 1.5, "Others"));
        ArrayList<Item> other = sl.filterSpendingList("Others", null);
        assertEqualList(expectedOther, other);
        ArrayList<Item> expectedEmpty = initList();
        ArrayList<Item> empty = sl.filterSpendingList("School", null);
        assertEqualList(expectedEmpty, empty);
    }

    //@author k-walter
    @Test
    public void filterByDate() {
        SpendingList sl = initSpendingList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("chicken", "SGD", 2, "Food", "2020-11-01"),
            new Item("pencil", "S$", 1.5, null, "2020-10-29"),
            new Item("fish", "S$", 10, null, "2019-10-29")
        );
        ArrayList<Item> filterAll = initList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("chicken", "SGD", 2, "Food", "2020-11-01"),
            new Item("pencil", "S$", 1.5, null, "2020-10-29"),
            new Item("fish", "S$", 10, null, "2019-10-29")
        );
        assertEqualList(filterAll, sl.filterSpendingList(null, null), "No filter");
        ArrayList<Item> filterYear = initList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("chicken", "SGD", 2, "Food", "2020-11-01"),
            new Item("pencil", "S$", 1.5, null, "2020-10-29")
        );
        assertEqualList(filterYear, sl.filterSpendingList(null, "2020"), "Filter 2020");
        ArrayList<Item> filterMonth = initList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("pencil", "S$", 1.5, null, "2020-10-29")
        );
        assertEqualList(filterMonth, sl.filterSpendingList(null, "2020-10"), "Filter 2020 Oct");
    }

    //@author k-walter
    @Test
    public void filterByDateAndCategory() {
        SpendingList sl = initSpendingList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("chicken", "SGD", 2, "Food", "2020-11-01"),
            new Item("pencil", "S$", 1.5, null, "2020-10-29"),
            new Item("fish", "S$", 10, null, "2019-10-29")
        );
        ArrayList<Item> filterFood2020 = initList(
            new Item("rice", "SGD", 2, "Food", "2020-10-01"),
            new Item("chicken", "SGD", 2, "Food", "2020-11-01")
        );
        assertEqualList(filterFood2020, sl.filterSpendingList("Food", "2020"), "Filter food 2020");
        ArrayList<Item> filterFood2019 = initList();
        assertEqualList(filterFood2019, sl.filterSpendingList("Food", "2019"), "Filter food 2019");
        ArrayList<Item> filterOthers2019 = initList(
            new Item("fish", "S$", 10, null, "2019-10-29")
        );
        assertEqualList(filterOthers2019, sl.filterSpendingList("Others", "2019"), "Filter others 2019");
    }

    //@@author killingbear999
    @Test
    public void getCurrentAmountTest() {
        double expectedAmount = 20.0;
        Data data = new Data(null, null, null);
        SpendingList realList = initSpendingList(
            new Item("sushi", "S$", 11.0, ""),
            new Item("bubble tea", "S$", 4.0, ""),
            new Item("medicine", "S$", 5.0, "")
        );
        assertEquals(realList.getCurrentAmount(data), expectedAmount);
    }
}
