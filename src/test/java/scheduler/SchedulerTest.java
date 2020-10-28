package scheduler;

import manager.card.Card;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SchedulerTest {

    @Test
    void isDeadlineDue_deadlineIsDue_true() {
        assertTrue(Scheduler.isDeadlineDue(Scheduler.getCurrentDate().minusDays(1)));
    }

    @Test
    void isDeadlineDue_deadlineNotDue_false() {
        assertFalse(Scheduler.isDeadlineDue(Scheduler.getCurrentDate().plusDays(1)));
    }

}