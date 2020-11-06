package seedu.smarthomebot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_LOCATION_CONFLICT;
import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_LOCATION_NOT_EXIST;

public class AddCommandTest {

    private LocationList locationList = new LocationList();
    private ApplianceList applianceList = new ApplianceList();

    @BeforeEach
    public void setup() throws DuplicateDataException, InvalidApplianceNameException, LocationNotFoundException {
        locationList.addLocation("BedRoom1");
        Lights l1 = new Lights("l1", "BedRoom1", "50", locationList);
        applianceList.addAppliance(l1);
    }

    @Test
    void addApplianceTest_Fan_addFanSuccess() {
        Command addAppliance = new AddCommand("Fan1", "BedRoom1", "50", "Fan");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals("ADDING Fan1(50W), located at BedRoom1 ......ADDED", actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_Light_addLightSuccess() {
        Command addAppliance = new AddCommand("l2", "BedRoom1", "50", "light");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals("ADDING l2(50W), located at BedRoom1 ......ADDED", actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_AirConditioner_addAirConditionerSuccess() {
        Command addAppliance = new AddCommand("AC1", "BedRoom1", "500", "aircon");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals("ADDING AC1(500W), located at BedRoom1 ......ADDED", actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_SmartPlug_addSmartPlugSuccess() {
        Command addAppliance = new AddCommand("Plug1", "BedRoom1", "500", "smartplug");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals("ADDING Plug1(500W), located at BedRoom1 ......ADDED", actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_TypeNotFound_addApplianceFailed() {
        Command addAppliance = new AddCommand("Plug1", "BedRoom1", "500", "plug");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals(MESSAGE_APPLIANCE_TYPE_NOT_EXIST, actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_DuplicateAppliance_catch_DuplicateDataException() {
        Command addAppliance = new AddCommand("l1", "BedRoom1", "500", "light");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals(MESSAGE_APPLIANCE_EXIST, actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_LocationNotCreated_catch_LocationNotFoundException() {
        Command addAppliance = new AddCommand("l1", "BedRoom2", "500", "light");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals(MESSAGE_LOCATION_NOT_EXIST, actualCommandResult.feedbackToUser);
    }

    @Test
    void addApplianceTest_DuplicateNameAsLocation_catch_InvalidApplianceNameException() {
        Command addAppliance = new AddCommand("BedRoom1", "BedRoom1", "500", "light");
        addAppliance.setData(applianceList, locationList);
        CommandResult actualCommandResult = addAppliance.execute();
        assertEquals(MESSAGE_APPLIANCE_LOCATION_CONFLICT, actualCommandResult.feedbackToUser);
    }
}
