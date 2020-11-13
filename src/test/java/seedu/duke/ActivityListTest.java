package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.model.ActivityList;
import seedu.duke.model.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityListTest {
    protected ActivityList dummyList = new ActivityList();
    LocalDate date = LocalDate.now();

    void createObjects(ActivityList dummyList) {
        dummyList.addActivity(new Food("Apple", 50, date, false));
        dummyList.addActivity(new Food("Banana", 100, date, false));
        dummyList.addActivity(new Food("Orange", 25, date, false));
    }

    @Test
    void removeActivity_validIndex_success() {
        createObjects(dummyList);
        dummyList.removeActivity(1);
        assertEquals("[F] | Apple | 50, [F] | Orange | 25", dummyList.toString());
    }

    @Test
    void clearList_success() {
        createObjects(dummyList);
        dummyList.clearList();
        assertEquals(0, dummyList.getNumberOfActivities());
    }

    @Test
    void removeActivity_InvalidPositiveIndex_success() {
        createObjects(dummyList);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyList.removeActivity(10);;
        });
    }

    @Test
    void removeActivity_InvalidNegativeIndex_success() {
        createObjects(dummyList);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyList.removeActivity(-1);;
        });
    }

}