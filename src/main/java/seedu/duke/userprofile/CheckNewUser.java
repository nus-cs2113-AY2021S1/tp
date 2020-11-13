package seedu.duke.userprofile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static seedu.duke.Trakcal.getJarFilePath;
import static seedu.duke.Trakcal.jarFilePath;

//@@author jlifah
/**
 * Checks if the user is a new user.
 */
public class CheckNewUser {

    public static final String FILE_PATH = jarFilePath + "/tpdata/tp.txt";

    /**
     * Check if it is a new user.
     *
     * @return boolean for new user outcome
     */
    public static boolean isNewUser() {
        if (!Files.exists(Path.of(FILE_PATH))) {
            System.out.println("Hey there! We do not have a record of your profile. Please create one now! :)\n");
            return true;
        }
        return false;
    }
}
//@@author jlifah
