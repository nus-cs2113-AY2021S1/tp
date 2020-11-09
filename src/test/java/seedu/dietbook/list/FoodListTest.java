package seedu.dietbook.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import seedu.dietbook.food.Food;


class FoodListTest {

    private FoodList list;
    private FoodList datedList;
    private Food food;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    protected void setUp() {
        this.list = new FoodList();
        this.datedList = new FoodList();

        Food food = new Food("Kobe Beef", 480,50,40,30);
        this.food = food;

        list.addFood(3, food);
        list.addFood(2, "Sashimi", 100, 0, 30, 10);

        this.start = LocalDateTime.of(2000, 6, 6, 12, 15);
        this.end = LocalDateTime.of(2000, 6, 10, 0, 0);

        datedList.addFoodAtDateTime(2, food, start);
        datedList.addFoodAtDateTime(3, food, end);

    }

    /**
     * getPortionedList() should return list of food with scaled up nutritional values.
     * getFoods() should return a list of food (Not food entries).
     * Essentially 2 tests in 1.
     */
    @Test
    @DisplayName("Portion scaling test")
    void foodPortionScaling_standardList_scaledFoodList() {
        FoodList testList = new FoodList();

        Food food = new Food("Kobe Beef", 480 * 3, 50 * 3, 40 * 3, 30 * 3);
        testList.addFood(1, food);
        testList.addFood(1, "Sashimi", 200, 0, 60, 20);

        assertEquals(testList.getFoods().toString(), list.getPortionedFoods().toString());
    }

    @Test
    void deleteItemTest() {
        // Positive Test:
        FoodEntry entry = new FoodEntry(3, food);
        assertEquals(entry.toString(), list.delete(1));
        // Negative Tests:
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(5));
    }

    @Test
    @DisplayName("Dated entry comparison test")
    void compareDatedEntries_datedEntriesAtDiffDate_compareToResult() {
        DatedFoodEntry entry = new DatedFoodEntry(2, food);
        DatedFoodEntry pastEntry = new DatedFoodEntry(2, food, LocalDateTime.MIN);
        assertTrue(entry.compareTo(pastEntry) > 0);
    }

    @Test
    void dateFilterAfterTest() {
        assertEquals(list.toString(), list.getAfterDateTimeToString(LocalDateTime.MIN));

        LocalDateTime timeNow = LocalDateTime.now();
        
        assertTrue(datedList.getFoodsAfterDateTime(timeNow).size() == 0);
        
        assertEquals(list.getFoods().toString(),
                list.getFoodsAfterDateTime(LocalDateTime.MIN).toString());

        datedList.addFood(1, food);
        assertEquals(food.toString(), datedList.getFoodsAfterDateTime(end).get(1).toString());

    }

    @Test
    void dateFilterRangeTest() {

        assertEquals(list.getPortionedFoods().toString(), 
                list.getPortionedFoodsInDateTimeRange(LocalDateTime.MIN, LocalDateTime.MAX).toString());
        assertEquals(datedList.toString(), 
                datedList.getInDateTimeRangeToString(start, end));

        LocalDateTime timeNow = LocalDateTime.now();
        assertTrue(datedList.getFoodsInDateTimeRange(timeNow, LocalDateTime.MAX).size() == 0);

    }

    @Test
    @DisplayName("Getter methods test")
    void getFoodEntryProperties_standardList_FoodEntryProperties() {
        assertTrue(list.getDateTimes().get(0) instanceof LocalDateTime);
        assertEquals(list.getPortionSizes().get(0), 3);
        assertEquals(list.getFoods().get(0), food);
    }

    @Test
    @DisplayName("Adding backlog entry test")
    void addAndRetrieveEntryAtDateTime_entryAddedAtDateTimeMax_entryAtDateTimeMax() {
        list.addFoodAtDateTime(2, food, LocalDateTime.MAX);
        
        assertEquals(food, list.getFoodsAfterDateTime(LocalDateTime.now()).get(0));
        // Further test that boundary is inclusive:
        assertEquals(food, list.getFoodsAfterDateTime(LocalDateTime.MAX).get(0));
    }

    @Test
    @DisplayName("Dated String representation test")
    void printDatedList_datedList_matchingStrings() {
        assertTrue(! datedList.toString().equals(datedList.toDatedString()));
        assertTrue(datedList.toDatedString().stripTrailing().endsWith(String.format("[%s]",
                end.format(DatedFoodEntry.DATE_TIME_FORMAT))));
        // Bound inclusivity should render the following true:
        assertEquals(datedList.toDatedString(), datedList.toDatedString(start));
        assertEquals(datedList.toDatedString(), datedList.toDatedString(start, end));
        assertTrue(datedList.toDatedString(end).stripTrailing().endsWith(String.format("[%s]",
                end.format(DatedFoodEntry.DATE_TIME_FORMAT))));

    }

    @Test
    @DisplayName("List sorting test")
    void getSortedItem_listWithItemsAddedAtEarlierDates_getItemsAccordingToDateTimeOrder() {
        datedList.addFoodAtDateTime(3, food, LocalDateTime.MIN);
        datedList.addFoodAtDateTime(2, food, LocalDateTime.of(2000, 6, 7, 0, 0));
        
        datedList.sort();
        
        for (int i = 0; i < datedList.getFoods().size() - 1; i++) {
            assertTrue(datedList.getDateTimes().get(i).isBefore(datedList.getDateTimes().get(i + 1)));
        }
    }


}
