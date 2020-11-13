package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.model.DayMap;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayMapTest {
    LocalDate date = LocalDate.of(2020, Month.AUGUST, 9);
    LocalDate date2 = LocalDate.of(2020, Month.AUGUST, 10);

    void createObjects(DayMap dummyMap) {
        dummyMap.addActivity(date.atStartOfDay(), new Food("Apple", 50, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Food("Banana", 100, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Food("Orange", 25, date, false));

        dummyMap.addActivity(date2.atStartOfDay(), new Food("Apple2", 51, date2, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("Banana2", 101, date2, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("Orange2", 26, date2, false));
    }

    @Test
    void listingCorrectKeyValueMatch() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);
        assertEquals("2020-08-09, [F] | Apple | 50, [F] | Banana | 100, [F] | Orange | 25",
                dummyMap.toString(date.atStartOfDay()));
        assertEquals("2020-08-10, [F] | Apple2 | 51, [F] | Banana2 | 101, [F] | Orange2 | 26",
                dummyMap.toString(date2.atStartOfDay()));
    }
}
