package seedu.duke.data;

import seedu.duke.data.framework.Appliance;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

import static seedu.duke.common.Messages.LINE;

public class ApplianceList {

    private static ArrayList<Appliance> appliances;
    private static TextUi ui = new TextUi();

    public ApplianceList() {
        appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) throws InvalidAdditionOfAppliance {
        if (!isAppliance(appliance.getName())) {
            appliances.add(appliance);
        } else {
            throw new InvalidAdditionOfAppliance();
        }
    }

    public void removeAppliance(String userEnteredName) {
        for (int i = 0; i < getAllAppliance().size(); i++) {
            if (getAppliance(i).getName().equals(userEnteredName)) {
                String location = getAppliance(i).getLocation();
                String type = getAppliance(i).getType();
                String name = getAppliance(i).getName();
                String power = getAppliance(i).getPower();
                System.out.printf(LINE + "Deleting %s: %s (%s) in %s......DELETED!\n", type, name, power, location);
                appliances.remove(i);
                break;
            }
        }
    }

    public Appliance getAppliance(int index) {
        return appliances.get(index);
    }

    public ArrayList<Appliance> getAllAppliance() {
        return appliances;
    }

    public void setAppliance(int index, Appliance appliance) {
        appliances.set(index, appliance);
    }

    public Boolean isAppliance(String toAddApplianceName) {
        boolean isExist = false;
        for (Appliance a : appliances) {
            if (a.getName().equals(toAddApplianceName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

}
