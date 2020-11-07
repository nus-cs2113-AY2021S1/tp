package seedu.smarthomebot.storage;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

import java.util.logging.Logger;

//@@author TanLeeWei

/**
 * Represent the parent class of ReadStorageFile and WriteStorageFile.
 */
public abstract class StorageFile {

    protected static ApplianceList applianceList;
    protected static LocationList locationList;
    public static Logger storageLogger = Logger.getLogger("SmartHomeBotLogger");

    /**
     * Passes the ApplianceList and LocationList to allow ReadStorageFile and WriteStorageFile to use.
     * @param applianceList stores the appliances in SmartHomeBot
     * @param locationList stores the locations in SmartHomeBot
     */
    public StorageFile(ApplianceList applianceList, LocationList locationList) {
        StorageFile.locationList = locationList;
        StorageFile.applianceList = applianceList;
    }

    public abstract void execute();

}
