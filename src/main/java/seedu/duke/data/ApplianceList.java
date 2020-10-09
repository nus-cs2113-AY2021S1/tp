package seedu.duke.data;

import seedu.duke.data.framework.Appliance;
import seedu.duke.exceptions.InvalidAdditionOfAppliance;

import java.util.ArrayList;

public class ApplianceList {

    private static ArrayList<Appliance> appliances;

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
