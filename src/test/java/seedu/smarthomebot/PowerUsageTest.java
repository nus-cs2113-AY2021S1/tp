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
        assertEquals("0", brLight.getPowerConsumption());
    }

    @Test
    void powerUsageTest() throws InterruptedException {
        Fan fan = new Fan("Cooling Fan", "Kitchen", "950");
        fan.switchOn();
        // Appliance wil be on for 5000ms which equals to 5hours in SmartHomeBot
        Thread.sleep(5000);
        fan.switchOff();
        assertEquals("5", fan.getPowerConsumption());
    }

}
