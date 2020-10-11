package scheduler;

import manager.card.Card;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SchedulerTest {

    @Test
    void computeEasyDeadline_newIntervalOverLimit_intervalUnchanged() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(333), Scheduler.computeEasyDeadline(c, 333));
    }

    @Test
    void computeEasyDeadline_newIntervalWithinLimit_increasedInterval() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(1), Scheduler.computeEasyDeadline(c, 1));
    }

    @Test
    void computeMediumDeadline_newIntervalOverLimit_intervalUnchanged() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(333), Scheduler.computeMediumDeadline(c, 333));
    }

    @Test
    void computeMediumDeadline_newIntervalWithinLimit_increasedInterval() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(2), Scheduler.computeMediumDeadline(c,1));
    }

    @Test
    void computeHardDeadline_newIntervalOverLimit_intervalUnchanged() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(333), Scheduler.computeHardDeadline(c, 333));
    }

    @Test
    void computeHardDeadline_newIntervalWithinLimit_increasedInterval() {
        Card c = new Card("1+1", "2");
        assertEquals(LocalDate.now().plusDays(4), Scheduler.computeHardDeadline(c,1));
    }

    @Test
    void computeDeckDeadline_newIntervalOverLimit_intervalUnchanged() {
        assertEquals(LocalDate.now().plusDays(333), Scheduler.computeDeckDeadline(20, 1, 333));
    }

    @Test
    void computeDeckDeadline_newIntervalWithinLimit_increasedInterval() {
        assertEquals(LocalDate.now().plusDays(20), Scheduler.computeDeckDeadline(20, 1, 1));
    }

    @Test
    void isDeadlineDue_deadlineIsDue_true() {
        assertTrue(Scheduler.isDeadlineDue(Scheduler.getCurrentDate().minusDays(1)));
    }

    @Test
    void isDeadlineDue_deadlineNotDue_false() {
        assertFalse(Scheduler.isDeadlineDue(Scheduler.getCurrentDate().plusDays(1)));
    }

}