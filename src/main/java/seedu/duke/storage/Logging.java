package seedu.duke.storage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.duke.ui.ExceptionMessages.displayIoExceptionMessage;
import static seedu.duke.ui.ExceptionMessages.displaySecurityExceptionMessage;


//@@author chewyang-reused
//Reused from https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging
// -logger#:~:text=To%20send%20logs%20to%20a,will%20enable%20file%20logging%20globally. with some modifications
/**
 * Logger class to store logs into a file.
 */
public class Logging extends Storage {
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    /**
     * Constructor for the logging class.
     * @param filePath path of file to be stored into
     */
    public Logging(String filePath) {
        super(filePath);
    }


    /**
     * Checks if the file to input the loggers is present and sets up the logging mechanism.
     */
    public void setUpLogger() {
        try {
            //Ensures that the files with the proper directories are created.
            createFileHierarchy();
            // This block configure the logger with handler and formatter.
            fh = new FileHandler(this.filePath);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            displaySecurityExceptionMessage();
        } catch (IOException e) {
            displayIoExceptionMessage();
        }
    }

    /**
     * Writes a log of INFO level.
     * @param logMessage is the message to be logged
     */
    public void writeToLogInfo(String logMessage) {
        this.logger.setUseParentHandlers(false);
        this.logger.info(logMessage);
    }

    /**
     * Writes a log of SEVERE level.
     * @param logMessage is the message to be logged
     */
    public void writeToLogSevere(String logMessage) {
        this.logger.setUseParentHandlers(false);
        this.logger.severe(logMessage);
    }

    /**
     * Writes a log of WARNING level.
     * @param logMessage is the message to be logged
     */
    public void writeToLogWarning(String logMessage) {
        this.logger.setUseParentHandlers(false);
        this.logger.warning(logMessage);
    }



}
