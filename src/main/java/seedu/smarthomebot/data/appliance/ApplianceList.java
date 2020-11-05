package seedu.smarthomebot.data.appliance;

import seedu.smarthomebot.commons.exceptions.ApplianceNotFoundException;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;

import java.util.ArrayList;

//@@author leonlowzd

/**
 * Represents the Appliances of SmartHomeBot.
 */
public class ApplianceList {

    private static ArrayList<Appliance> applianceList;

    /**
     * Constructor of ApplianceList.
     */
    public ApplianceList() {
        this.applianceList = new ArrayList<>();
    }

    /**
     * Method to add a new Appliance to the ApplianceList.
     *
     * @param appliance Appliance to add.
     */
    public void addAppliance(Appliance appliance) throws DuplicateDataException {
        if (!isApplianceExist(appliance.getName())) {
            this.applianceList.add(appliance);
        } else {
            throw new DuplicateDataException();
        }
    }

    /**
     * Method to delete a Appliance from the ApplianceList.
     *
     * @param userEnteredName Appliance to remove.
     */
    public Appliance deleteAppliance(String userEnteredName) throws ApplianceNotFoundException {
        for (Appliance appliance : this.getAllAppliance()) {
            if (appliance.getName().equals(userEnteredName)) {
                this.applianceList.remove(appliance);
                return appliance;
            }
        }
        throw new ApplianceNotFoundException();
    }

    /**
     * Method to retrieve the Appliance from the ApplianceList.
     *
     * @param index Appliance to retrieve.
     */
    public Appliance getAppliance(int index) {
        return this.applianceList.get(index);
    }

    /**
     * Method to retrieve all of the Appliances from the ApplianceList.
     *
     * @return the full list of the Appliances.
     */
    public ArrayList<Appliance> getAllAppliance() {
        return this.applianceList;
    }

    /**
     * Method to check if the Appliance exists in the ApplianceList.
     *
     * @param toAddApplianceName Appliance to check.
     */
    public Boolean isApplianceExist(String toAddApplianceName) {
        boolean isExist = false;
        for (Appliance a : this.applianceList) {
            if (a.getName().equals(toAddApplianceName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    /**
     * Method to remove Appliances located in the user selected location in the ApplianceList.
     *
     * @param usersEnteredLocation Appliance to check.
     */
    public void deleteByLocation(String usersEnteredLocation) throws ApplianceNotFoundException {
        for (int x = this.getAllAppliance().size() - 1; x >= 0; x--) {
            if (this.getAppliance(x).getLocation().equals(usersEnteredLocation)) {
                this.deleteAppliance((this.getAppliance(x).getName()));
            }
        }
    }

}
