package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatCommandTest {

    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data");

    @Test
    public void repeat_validPersonalEventIndex_repeatPassMessagePrinted() {

        //create the user data to have one item first
        String userInput = "personal holiday; 2020-10-01";
        AddCommand addPersonalEvent = new AddCommand(userInput.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        //execute the repeat command
        RepeatCommand testing = new RepeatCommand("1; personal; weekly; 3");
        testing.execute(data, ui, storage);

        //retrieve the modified item
        Event item = data.getEventList("Personal").getEventByIndex(0);
        assertEquals("Repeat Status: Weekly for 3 time(s) starting from 2020-10-01", item.getRepeatStatusString());
    }

    @Test
    public void repeat_validPersonalEventIndex_repeatDatesIncrementCorrectWeekly() {

        //create the user data to have one item first
        String userInput = "personal holiday; 2020-10-01";
        AddCommand addPersonalEvent = new AddCommand(userInput.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        //execute the repeat command
        RepeatCommand testing = new RepeatCommand("1; personal; weekly; 5");
        testing.execute(data, ui, storage);

        //retrieve the modified item date
        Event item = data.getEventList("Personal").getEventByIndex(0);
        LocalDate baseDate = LocalDate.parse("2020-10-01");
        assertEquals(baseDate, item.getDate(0));
        LocalDate nextDate = LocalDate.parse("2020-10-08");
        assertEquals(nextDate, item.getDate(1));
        nextDate = LocalDate.parse("2020-10-15");
        assertEquals(nextDate, item.getDate(2));
        nextDate = LocalDate.parse("2020-10-22");
        assertEquals(nextDate, item.getDate(3));
        nextDate = LocalDate.parse("2020-10-29");
        assertEquals(nextDate, item.getDate(4));
        nextDate = LocalDate.parse("2020-11-05");
        assertEquals(nextDate, item.getDate(5));

    }

    @Test
    public void repeat_validPersonalEventIndex_repeatDatesIncrementCorrectMonthly() {

        //create the user data to have one item first
        String userInput = "personal celebration feast; 2020-10-01";
        AddCommand addPersonalEvent = new AddCommand(userInput.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        //execute the repeat command
        RepeatCommand testing = new RepeatCommand("1; personal; monthly; 5");
        testing.execute(data, ui, storage);

        //retrieve the modified item date
        Event item = data.getEventList("Personal").getEventByIndex(0);
        LocalDate baseDate = LocalDate.parse("2020-10-01");
        assertEquals(baseDate, item.getDate(0));
        LocalDate nextDate = LocalDate.parse("2020-11-01");
        assertEquals(nextDate, item.getDate(1));
        nextDate = LocalDate.parse("2020-12-01");
        assertEquals(nextDate, item.getDate(2));
        nextDate = LocalDate.parse("2021-01-01");
        assertEquals(nextDate, item.getDate(3));
        nextDate = LocalDate.parse("2021-02-01");
        assertEquals(nextDate, item.getDate(4));
        nextDate = LocalDate.parse("2021-03-01");
        assertEquals(nextDate, item.getDate(5));

    }
}