package fitr.common;

import java.time.LocalDate;
import static fitr.common.DateManager.getCurrentDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateManagerTest {
    @Test
    public void dateManagerTest_correctResult() {
        LocalDate date1 = getCurrentDate();
        LocalDate date2 = getCurrentDate();
        assertEquals(date1, date2);
    }
}
