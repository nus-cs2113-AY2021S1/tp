package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;
import seedu.duke.model.GraphProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;


class GraphPropertyTest {
    private LocalDate date1 = LocalDate.of(2020, Month.AUGUST, 9);
    private LocalDate date2 = LocalDate.of(2020, Month.MARCH, 12);

    public DayMap setDummyMap() {
        DayMap dayMap = new DayMap();
        return dayMap;
    }

    @Test
    public void setProperty_TargetCalorieAsMiddle_success() {
        DayMap dummyMap = setDummyMap();
        dummyMap.addActivity(date1.atStartOfDay(), new Food("apple", 1000, date1, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("banana", 2000, date2, false));
        GraphProperty graphProperty = new GraphProperty(dummyMap, 1500);
        graphProperty.setProperties();
        assertEquals(2, graphProperty.row);
        assertEquals(2000, graphProperty.maxCalories);
        assertEquals(1000, graphProperty.minCalories);
    }

    @Test
    public void setProperty_TargetCalorieInLower_success() {
        DayMap dummyMap = setDummyMap();
        dummyMap.addActivity(date1.atStartOfDay(), new Food("apple", 1000, date1, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("banana", 2000, date2, false));
        GraphProperty graphProperty = new GraphProperty(dummyMap, 500);
        graphProperty.setProperties();
        assertEquals(2, graphProperty.row);
        assertEquals(2000, graphProperty.maxCalories);
        assertEquals(500, graphProperty.minCalories);
    }

    @Test
    public void setProperty_TargetCalorieInHigher_success() {
        DayMap dummyMap = setDummyMap();
        dummyMap.addActivity(date1.atStartOfDay(), new Food("apple", 1000, date1, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("banana", 2000, date2, false));
        GraphProperty graphProperty = new GraphProperty(dummyMap, 3000);
        graphProperty.setProperties();
        assertEquals(2, graphProperty.row);
        assertEquals(3000, graphProperty.maxCalories);
        assertEquals(1000, graphProperty.minCalories);
    }

    @Test
    public void setProperty_EmptyList_Exception() {
        DayMap dummyMap = setDummyMap();
        Assertions.assertThrows(AssertionError.class, () -> {
            GraphProperty graphProperty = new GraphProperty(dummyMap, 3000);
            graphProperty.setProperties();
        });
    }

    @Test
    public void setProperty_NegativeCalories_success() {
        DayMap dummyMap = setDummyMap();
        dummyMap.addActivity(date1.atStartOfDay(), new Exercise("pushup", 1000, date1, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("banana", 2000, date2, false));
        GraphProperty graphProperty = new GraphProperty(dummyMap, 1000);
        graphProperty.setProperties();
        assertEquals(2, graphProperty.row);
        assertEquals(2000, graphProperty.maxCalories);
        assertEquals(-1000, graphProperty.minCalories);
    }

    @Test
    public void setProperty_sameCalories_success() {
        DayMap dummyMap = setDummyMap();
        dummyMap.addActivity(date1.atStartOfDay(), new Food("apple", 1000, date1, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("banana", 1000, date2, false));
        GraphProperty graphProperty = new GraphProperty(dummyMap, 1000);
        graphProperty.setProperties();
        assertEquals(2, graphProperty.row);
        assertEquals(1005, graphProperty.maxCalories);
        assertEquals(995, graphProperty.minCalories);
    }


}