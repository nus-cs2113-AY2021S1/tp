package seedu.financeit.utils;

import seedu.financeit.parser.InputParser;
import seedu.financeit.utils.storage.SaveHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class LoggerCentre {
    private Level level;
    public static Logger loggerParamChecker = Logger.getLogger(ParamChecker.class.getName());
    public static Logger loggerInputParser = Logger.getLogger(InputParser.class.getName());
    private static LoggerCentre loggerCentre = null;
    public static boolean logCreated = false;

    private LoggerCentre() {
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
            fileHandler.setLevel(Level.WARNING);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addHandler(StreamHandler consoleHandler) {
        loggerParamChecker.addHandler(consoleHandler);
        loggerInputParser.addHandler(consoleHandler);
    }

    public void setLevel(Level level) {
        this.level = level;
        loggerParamChecker.setLevel(level);
        loggerInputParser.setLevel(level);
    }
}
