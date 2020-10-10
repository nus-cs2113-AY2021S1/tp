package seedu.duke.commands;

import seedu.duke.data.AirConditioner;
import seedu.duke.data.Fan;
import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.data.framework.Appliance;
import seedu.duke.exceptions.InvalidParameter;

import static seedu.duke.common.Messages.LINE;

public class OnCommand extends Command {

    public static final String COMMAND_WORD = "on";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Turns on specified appliance by its indicated NAME \n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " Aircon 1 ";
    private final String name;
    private final String parameter;

    public OnCommand(String name, String parameter) throws EmptyParameterException {
        if (name.isEmpty()) {
            throw new EmptyParameterException();
        }
        this.name = name;
        this.parameter = parameter;
    }

    @Override
    public void execute() {
        for (Appliance i : appliances.getAllAppliance()) {
            String location = i.getLocation();
            if (i.getName().equals((this.name))) {
                if (i.getClass().toString().contains("AirConditioner")) {
                    i = setACTemperature(i);
                } else if (i.getClass().toString().contains("Fan")) {
                    i = setFanSpeed(i);
                }
                System.out.printf(LINE + "Switching on %s in %s ......ON!\n", name, location);
                i.switchOn();
                System.out.println(i);
                return;
            }
        }
        ui.showToUser(LINE + name + " does not exist.");
    }

    private Appliance setACTemperature(Appliance i) {
        try {
            AirConditioner ac = (AirConditioner) i;
            int temperature = Integer.parseInt(parameter);
            if (!(temperature < 16 || temperature > 30)) {
                ac.setTemperature(parameter);
                i = ac;
            } else {
                throw new InvalidParameter();
            }
        } catch (InvalidParameter | NumberFormatException e) {
            System.out.println("Invalid temperature, temperature should within 16-25.");
        }
        return i;
    }

    private Appliance setFanSpeed(Appliance i) {
        try {
            Fan f = (Fan) i;
            int speed = Integer.parseInt(parameter);
            if (!(speed < 1 || speed > 3)) {
                f.setSpeed(parameter);
                i = f;
            } else {
                throw new InvalidParameter();
            }
        } catch (InvalidParameter | NumberFormatException e) {
            System.out.println("Invalid fan speed, speed should be 1-3 only!");
        }
        return i;
    }

}
