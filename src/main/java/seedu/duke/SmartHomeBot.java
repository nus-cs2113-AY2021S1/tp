package seedu.duke;

import seedu.duke.data.ApplianceList;
import seedu.duke.data.framework.Appliance;
import seedu.duke.data.type.AirConditioner;
import seedu.duke.data.type.Lights;

import java.util.ArrayList;

public class SmartHomeBot {
    private static final ApplianceList appliances = new ApplianceList();

    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner("AC_1 BR1", "BR1");
        Lights bedroomLight = new Lights("Lights_1 BR1", "BR1");
        appliances.addAppliance(ac);
        appliances.addAppliance(bedroomLight);

        //Changing temperature for aircondtioner
        AirConditioner temp = (AirConditioner) appliances.getAppliance(0);
        temp.setTemperature("25");
        appliances.setAppliance(0,temp);
        appliances.getAppliance(1).switchOn();

        for (Appliance a: appliances.getAllAppliance()) {
            System.out.println(a);
        }

    }
}
