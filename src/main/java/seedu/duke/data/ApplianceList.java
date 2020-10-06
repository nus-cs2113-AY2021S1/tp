package seedu.duke.data;

import seedu.duke.data.framework.Appliance;

import java.util.ArrayList;

public class ApplianceList {

    private static ArrayList<Appliance> appliances;

    public ApplianceList() {
        appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    public void removeAppliance(int index) {
        appliances.remove(index);
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

}
