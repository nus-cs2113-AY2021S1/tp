package seedu.financeit.utils;

import seedu.financeit.parser.InputParser;
import seedu.financeit.utils.storage.SaveHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
            LocalDateTime dateTime = RunHistory.getCurrentRunDateTime();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu_MM_dd_HH_mm_ss");
            String paramLog = "./log/" + dateTime.format(format) + "_param.txt";
            String inputLog = "./log/" + dateTime.format(format) + "_input.txt";
            SaveHandler.buildFile("./log", paramLog);
            SaveHandler.buildFile("./log", inputLog);
            FileHandler pm = new FileHandler(paramLog);
            FileHandler in = new FileHandler(inputLog);
            loggerParamChecker.addHandler(pm);
            loggerInputParser.addHandler(in);
            SimpleFormatter formatter = new SimpleFormatter();
            pm.setFormatter(formatter);
            in.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLevel(Level level) {
        this.level = level;
        loggerParamChecker.setLevel(level);
        loggerInputParser.setLevel(level);
    }
}
