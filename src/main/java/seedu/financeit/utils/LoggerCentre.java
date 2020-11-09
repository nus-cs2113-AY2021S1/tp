package seedu.financeit.utils;

import seedu.financeit.parser.InputParser;
import seedu.financeit.utils.storage.SaveHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 * Logs messages into logs/*.txt.
 */
public class LoggerCentre {
    public static Logger loggerParamChecker = Logger.getLogger(ParamChecker.class.getName());
    public static Logger loggerInputParser = Logger.getLogger(InputParser.class.getName());
    public static Logger loggerSystemMessages = Logger.getLogger(LoggerCentre.class.getName());
    public static ArrayList<Logger> loggers = new ArrayList<>() {
        {
            add(loggerParamChecker);
            add(loggerInputParser);
            add(loggerSystemMessages);
        }
    };

    private static LoggerCentre loggerCentre = null;

    private LoggerCentre() {
    }

    private void init() {

    }

    public static LoggerCentre getInstance() {
        if (loggerCentre == null) {
            loggerCentre = new LoggerCentre();
        }
        return loggerCentre;
    }

    public static void createLog() {
        try {
            // Setting name for the log file
            LogManager.getLogManager().reset();
            LocalDateTime dateTime = RunHistory.getCurrentRunDateTime();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu_MM_dd_HH_mm_ss");
            String paramLog = "./logs/" + dateTime.format(format) + ".log";
            SaveHandler.buildFile("./logs", paramLog);

            // Disabling console output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.OFF);
            addHandler(consoleHandler);

            // Setting file logger to log only warning messages
            FileHandler fileHandler = new FileHandler(paramLog);
            fileHandler.setLevel(Level.ALL);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addHandler(StreamHandler consoleHandler) {
        for (Logger logger : loggers) {
            logger.addHandler(consoleHandler);
        }
    }

    public void setLevel(Level level) {
        for (Logger logger : loggers) {
            logger.setLevel(level);
        }
    }
}
