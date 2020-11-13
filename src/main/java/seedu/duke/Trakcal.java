package seedu.duke;

import java.io.File;

import seedu.duke.command.Command;
import seedu.duke.logic.parser.ChainingParser;
import seedu.duke.logic.parser.CommandParser;
import seedu.duke.model.DayMap;
import seedu.duke.storage.Storage;
import seedu.duke.storage.Logging;
import seedu.duke.ui.Ui;
import seedu.duke.userprofile.UserProfile;
import seedu.duke.userprofile.InitialiseUserProfile;
import seedu.duke.userprofile.CheckNewUser;


import java.time.LocalDate;
import java.util.Scanner;

import static seedu.duke.logic.parser.CommandParser.SPACE;
import static seedu.duke.ui.Ui.displayNotSavedMessage;
import static seedu.duke.ui.Ui.displayWelcomeMessage;

/**
 * Entry point of the traKCAL application.
 * Initialises the application and starts the interaction with the user.
 */
public class Trakcal {

    public static DayMap calList = new DayMap();
    public static InitialiseUserProfile profile;
    public static String jarFilePath = getJarFilePath();
    public static Scanner in = new Scanner(System.in);
    public static Storage storage = new Storage(jarFilePath + "/tpdata/tpcsv.csv");
    //public static Storage loggingStorage = new Storage(getJarFilePath() + "/tpdata/tpLogging.txt");
    public static Logging logging = new Logging(jarFilePath + "/tpdata/tpLogging.log");





    /**
     * Main function.
     *
     * @param args args
     */
    public static void main(String[] args) {
        displayWelcomeMessage();
        System.out.println();
        logging.setUpLogger();
        storage.loadData(calList);
        calList.setLastSeenList(calList.getActivityList(LocalDate.now().atStartOfDay()));
        if (CheckNewUser.isNewUser()) {
            profile = UserProfile.createNewProfile();
        } else {
            Ui.displayReturningUserMessage();
            profile = UserProfile.loadProfile();
        }
        Trakcal.run();
    }

    /**
     * Main running loop.
     */
    public static void run()  {
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            CommandParser parser = new CommandParser(userInput);
            try {
                Command command;
                if (userInput.contains(ChainingParser.CHAIN_SEPARATOR)) {
                    new ChainingParser(userInput).parseArgument();
                } else {
                    command = parser.parseArgument();
                    executeCmd(command);
                    storage.updateFile(calList);
                }
            } catch (NullPointerException e) {
                // Exception is already taken care of
            } catch (IndexOutOfBoundsException e) {
                displayNotSavedMessage();
            }
        }
    }

    /**
     * Sets the data for each command and executes the command.
     *
     * @param command command to execute
     * @throws NullPointerException if invalid command
     */
    public static void executeCmd(Command command) throws NullPointerException {
        command.setData(calList);
        command.execute();
    }

    /**
     * Gets the file path of the jar file.
     *
     * @return string of the file path
     */
    public static String getJarFilePath() {
        return new File(Trakcal.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                .getParent().replace("%20", SPACE);
    }

}
