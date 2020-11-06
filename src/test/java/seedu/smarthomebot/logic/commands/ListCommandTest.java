package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_APPLIANCES;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LIST_NO_LOCATIONS;

public class ListCommandTest {

    @Test
    void listLocation_EmptyLocationList_catch_EmptyLocationListException() {
        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        Command listLocation = new ListCommand("location", "");
        listLocation.setData(applianceList, locationList);
        CommandResult actualCommandResult = listLocation.execute();
        assertEquals(MESSAGE_LIST_NO_LOCATIONS, actualCommandResult.feedbackToUser);
    }

    @Test
    void listAppliance_EmptyApplianceList_catch_EmptyApplianceListException() {
        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        Command listAppliance = new ListCommand("appliance", "");
        listAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = listAppliance.execute();
        assertEquals(MESSAGE_LIST_NO_APPLIANCES, actualCommandResult.feedbackToUser);
    }

    @Test
    void listApplianceByFilteredLocation_NoApplianceInFilteredLocation_catch_NoApplianceInLocationException() throws
            DuplicateDataException {
        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        locationList.addLocation("BedRoom1");
        Command listFilteredAppliance = new ListCommand("appliance", "BedRoom1");
        listFilteredAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = listFilteredAppliance.execute();
        assertEquals("There is no Appliance in \"BedRoom1\".",actualCommandResult.feedbackToUser);
    }

    @Test
    void listApplianceByFilteredLocation_FilteredLocationNotFound_catch_LocationNotFoundException() throws
            DuplicateDataException {
        LocationList locationList = new LocationList();
        ApplianceList applianceList = new ApplianceList();
        locationList.addLocation("BedRoom1");
        Command listFilteredAppliance = new ListCommand("appliance", "BedRoom2");
        listFilteredAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = listFilteredAppliance.execute();
        assertEquals("Location: \"BedRoom2\" does not exist.", actualCommandResult.feedbackToUser);
    }
}
