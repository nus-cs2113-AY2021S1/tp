package seedu.smarthomebot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.smarthomebot.commands.Command;
import seedu.smarthomebot.commands.InvalidCommand;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.Lights;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerUsageTest {

    @Test
    void getPowerTest() {
        Assertions.assertEquals("45", new Lights("Xiao Mi Light", "BedRoom 1", "45").getPower());
    }

    @Test
    void invalidPowerTest() {
        Parser parser = new Parser();
        final String input = "add Xiao Mi Light l/Bedroom w/ABCerror t/Light";
        final Command result = parser.parseCommand(input);
        // Non numerical value is used for power, return InvalidCommand
        assertTrue(result.getClass().isAssignableFrom(InvalidCommand.class));
    }

    @Test
    void initialPowerUsageTest() {
        Lights brLight = new Lights("Main Light", "BedRoom 1", "100");
        // New appliance wil not have power consumption until user switch on
        assertEquals("0.00", brLight.getPowerConsumption());
    }

    @Test
    void powerUsageTest() throws InterruptedException {
        Fan fan = new Fan("Cooling Fan", "Kitchen", "40010");
        fan.switchOn();
        // Appliance will be on for 3000ms which equals to 30 minutes in SmartHomeBot
        Thread.sleep(3000);
        fan.switchOff();
        assertEquals("20.01", fan.getPowerConsumption());
    }

}
