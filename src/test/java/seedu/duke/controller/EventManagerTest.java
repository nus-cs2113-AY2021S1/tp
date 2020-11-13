package seedu.duke.controller;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.EmptyParameterException;
import seedu.duke.exception.MissingParameterException;
import seedu.duke.exception.SwappedParameterException;
import seedu.duke.model.ConfigParameter;
import seedu.duke.model.event.EventManager;
import seedu.duke.model.event.EventParameter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventManagerTest {

    @Test
    void addTestToList_exceedTime() throws MissingParameterException, EmptyParameterException,
            SwappedParameterException {
        EventManager eventManager = new EventManager(new EventParameter(), new ConfigParameter("me",
                10, true));
        eventManager.getTestManager().add("add test /n Math test "
                + "/s 2020-09-26 1000 /e 2020-09-26 1500");
        eventManager.getTestManager().add("add test /n Eng test "
                + "/s 2020-09-26 1600 /e 2020-09-26 2300");
        int actualOutputs = eventManager.getTestManager().getTestListSize();
        int expectedOutputs = 1;
        assertEquals(expectedOutputs, actualOutputs);
    }

}