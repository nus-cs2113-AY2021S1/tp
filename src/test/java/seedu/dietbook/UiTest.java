package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.person.Person;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    @Test
    void stringDateTime_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> ui.stringDateTime(null));
    }

    @Test
    void stringDateTime_LocalDateTime_returnsStringOfLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-21T23:59");
        assertEquals("21 Oct 2020 2359",ui.stringDateTime(dateTime));
    }

    @Test
    void stringDateTime_LocalDateTimeWithSeconds_returnsStringOfLocalDateTimeWithoutSeconds() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-21T23:59:22");
        assertEquals("21 Oct 2020 2359",ui.stringDateTime(dateTime));
    }
}
