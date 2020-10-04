package seedu.duke.parsers;

import seedu.duke.exceptions.SettingObjectWrongFormatException;

public class Parsers {
    /**
     * Extract the value string from line in settings save file.
     *
     * @param fileLine    a line read from the file
     * @param objectTitle the string indicating the type of object
     * @return returns the setting extracted from line in the settings file
     * @throws SettingObjectWrongFormatException the linel in the settings file was wrongly formatted
     */
    public static String parseFileObject(String fileLine, String objectTitle)
            throws SettingObjectWrongFormatException {

        int settingTitleLength = objectTitle.length();
        String fileObject;

        // identify placements
        int settingObjectPosition = fileLine.indexOf(objectTitle);

        // check if placement is correct
        if (settingObjectPosition == -1) {
            throw new SettingObjectWrongFormatException();
        } else {
            try {
                fileObject = fileLine.substring(settingObjectPosition + settingTitleLength).trim();
            } catch (StringIndexOutOfBoundsException exception) {
                throw new SettingObjectWrongFormatException();
            }
        }
        return fileObject;
    }
}
