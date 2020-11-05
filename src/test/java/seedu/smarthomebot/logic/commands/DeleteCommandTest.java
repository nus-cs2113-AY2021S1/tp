package seedu.smarthomebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    /*
    @Test
    public void deleteApplianceTest() throws InvalidAdditionOfAppliance, InvalidAddtionOfLocation,
            EmptyParameterException {
        //Create Sample Locations and empty appliance list

        HomeLocations homeLocations = new HomeLocations();
        ApplianceList applianceList = new ApplianceList();
        homeLocations.addLocation("BedRoom1");
        Lights l1 = new Lights("l1", "BedRoom1", "50");
        Lights l2 = new Lights("l2", "BedRoom1", "50");
        applianceList.addAppliance(l1);
        applianceList.addAppliance(l2);

        // Prepare to read output of command
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Command deleteAppliance = new DeleteCommand("l3");
        deleteAppliance.setData(applianceList, homeLocations);
        deleteAppliance.execute();

        //Process ui output
        String outputString = outContent.toString().replace(System.getProperty("line.separator"), "");

        //compare outputs
        assertEquals("l3 does not exist.", outputString);
    }

     */
}
