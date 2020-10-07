package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.*;

class RepeatCommandTest {

    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data");

    @Test
    public void repeat_validPersonalEventIndex_repeatPassMessagePrinted () {

        //create the user data to have one item first
        String userInput = "personal holiday; 2020-10-01";
        AddCommand addPersonalEvent = new AddCommand(userInput.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        //execute the repeat command
        RepeatCommand testing = new RepeatCommand("1; personal; weekly; 3");
        testing.execute(data, ui, storage);

        //retrieve the modified item
        Event item = data.getEventList("Personal").getEventByIndex(0);
        assertEquals("Repeat Status: Weekly for 3 time(s) starting from 2020-10-01", item.getRepeatStatus());
    }
}