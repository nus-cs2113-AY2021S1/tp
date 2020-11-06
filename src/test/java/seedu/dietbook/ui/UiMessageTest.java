package seedu.dietbook.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UiMessageTest {

    private UiMessage uiMessage;

    @BeforeEach
    public void setUp() {
        uiMessage = new UiMessage();
    }

    @Test
    void stringDateTimePeriod_startDateTimeIsNullInput_expectAssertionError() {
        LocalDateTime end = LocalDateTime.parse("2020-10-21T23:59");
        assertThrows(AssertionError.class, () -> uiMessage.stringDateTimePeriod(null, end));
    }

    @Test
    void stringDateTimePeriod_endDateTimeIsNullInput_expectAssertionError() {
        LocalDateTime start = LocalDateTime.parse("2020-10-21T23:59");
        assertThrows(AssertionError.class, () -> uiMessage.stringDateTimePeriod(start, null));
    }

    @Test
    void stringDateTimePeriod_endDateTimeIsBeforeStartTime_expectAssertionError() {
        LocalDateTime start = LocalDateTime.parse("2020-10-21T23:59");
        LocalDateTime end = LocalDateTime.parse("2020-10-20T23:59");
        assertThrows(AssertionError.class, () -> uiMessage.stringDateTimePeriod(start, end));
    }

    @Test
    void stringDateTimePeriod_endDateTimeIsInTheFuTure_expectAssertionError() {
        LocalDateTime start = LocalDateTime.parse("2020-10-21T23:59");
        LocalDateTime end = LocalDateTime.now().plusDays(3);
        assertThrows(AssertionError.class, () -> uiMessage.stringDateTimePeriod(start, end));
    }

    @Test
    void stringDateTimePeriod_StartDateTimeIsInTheFuture_expectAssertionError() {
        LocalDateTime start = LocalDateTime.now().plusDays(3);
        LocalDateTime end = LocalDateTime.now().plusDays(5);
        assertThrows(AssertionError.class, () -> uiMessage.stringDateTimePeriod(start, end));
    }


    @Test
    void stringDateTimePeriod_sameStartAndEndDateTime_returnsStringOfTimePeriod() {
        LocalDateTime start = LocalDateTime.parse("2020-10-21T23:59");
        LocalDateTime end = LocalDateTime.parse("2020-10-21T23:59");
        assertEquals(" between 21 Oct 2020 2359 and 21 Oct 2020 2359",
                uiMessage.stringDateTimePeriod(start, end));
    }

    @Test
    void stringDateTimePeriod_validStartAndEndDateTime_returnsStringOfTimePeriod() {
        LocalDateTime start = LocalDateTime.parse("2020-10-19T23:59");
        LocalDateTime end = LocalDateTime.parse("2020-10-21T23:59");
        assertEquals(" between 19 Oct 2020 2359 and 21 Oct 2020 2359",
                uiMessage.stringDateTimePeriod(start, end));
    }

    @Test
    void stringDateTimePeriod_validStartAndEndDateTimeWithSeconds_returnsStringOfTimePeriodWithoutSeconds() {
        LocalDateTime start = LocalDateTime.parse("2020-10-19T23:59:22");
        LocalDateTime end = LocalDateTime.parse("2020-10-21T23:59:22");
        assertEquals(" between 19 Oct 2020 2359 and 21 Oct 2020 2359",
                uiMessage.stringDateTimePeriod(start, end));
    }

}
