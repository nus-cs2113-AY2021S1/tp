package seedu.smarthomebot.storage;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;

import java.util.logging.Logger;

public abstract class StorageFile {

    protected static ApplianceList applianceList;
    protected static LocationList locationList;
    public static Logger storageLogger = Logger.getLogger("SmartHomeBotLogger");

    public StorageFile(ApplianceList applianceList, LocationList locationList) {
        StorageFile.locationList = locationList;
        StorageFile.applianceList = applianceList;
    }

    public abstract void execute();

}
