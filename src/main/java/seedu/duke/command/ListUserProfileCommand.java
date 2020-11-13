package seedu.duke.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static seedu.duke.Trakcal.jarFilePath;
import static seedu.duke.ui.ExceptionMessages.displayFileError;

//@@author jlifah
/**
 * Lists the user profile details.
 */
public class ListUserProfileCommand extends Command {

    @Override
    public void execute() {
        try {

            String filePath = jarFilePath + "/tpdata/tp.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            System.out.println("Here is your user profile:");
            System.out.println("Name : " + line);
            line = reader.readLine();
            System.out.println("Gender : " + line);
            line = reader.readLine();
            System.out.println("Weight : " + line);
            line = reader.readLine();
            System.out.println("Height : " + line);
            line = reader.readLine();
            System.out.println("Age : " + line);
            line = reader.readLine();
            System.out.println("Activity Level : " + line);
            line = reader.readLine();
            System.out.println("Weight Goal : " + line + "\n");
            reader.close();
        } catch (IOException e) {
            displayFileError();
        }
    }
}
//@@author jlifah
