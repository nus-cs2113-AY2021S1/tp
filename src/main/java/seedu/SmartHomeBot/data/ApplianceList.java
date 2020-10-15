package seedu.SmartHomeBot.data;

import seedu.SmartHomeBot.data.framework.Appliance;
import seedu.SmartHomeBot.exceptions.InvalidAdditionOfAppliance;
import seedu.SmartHomeBot.ui.TextUi;

import java.util.ArrayList;

import static seedu.SmartHomeBot.common.Messages.LINE;

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
                String result = String.format(LINE + "Deleting %s: %s (%s) in %s......DELETED!",
                        type, name, power, location);
                ui.showToUser(result);
                appliances.remove(i);
                break;
            }
            if (i == getAllAppliance().size() - 1) {
                throw new IndexOutOfBoundsException();
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
