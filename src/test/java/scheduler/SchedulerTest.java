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

    @Test
    void computeEasyInterval_intervalWithinMaxInterval_incrementedInterval() {
        assertEquals(Scheduler.computeEasyInterval(1), 4);
    }

    @Test
    void computeEasyInterval_previousIntervalEqualsMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeEasyInterval(365), 365);
    }

    @Test
    void computeEasyInterval_previousIntervalBeyondMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeEasyInterval(1000), 365);
    }

    @Test
    void computeMediumInterval_intervalWithinMaxInterval_incrementedInterval() {
        assertEquals(Scheduler.computeMediumInterval(1), 2);
    }

    @Test
    void computeMediumInterval_previousIntervalEqualsMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeMediumInterval(365), 365);
    }

    @Test
    void computMediumInterval_previousIntervalBeyondMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeMediumInterval(1000), 365);
    }

    @Test
    void computeHardInterval_intervalWithinMaxInterval_incrementedInterval() {
        assertEquals(Scheduler.computeHardInterval(1), 1);
    }

    @Test
    void computeHardInterval_previousIntervalEqualsMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeHardInterval(365), 365);
    }

    @Test
    void computeHardInterval_previousIntervalBeyondMaxInterval_MaxInterval() {
        assertEquals(Scheduler.computeHardInterval(1000), 365);
    }

}