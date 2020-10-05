package seedu.duke;

import seedu.duke.data.ApplianceList;
import seedu.duke.data.type.AirConditioner;
import seedu.duke.data.type.Lights;

public class SmartHomeBot {
    private static final ApplianceList appliances = new ApplianceList();

    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner("AC_1 BR1", "BR1");
        Lights bedroomLight = new Lights("Lights_1 BR1", "BR1");
        appliances.addAppliance(ac);
        appliances.addAppliance(bedroomLight);

        System.out.println(appliances.getAllAppliance());
    }
}
