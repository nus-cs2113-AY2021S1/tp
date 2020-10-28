package seedu.financeit.utils;

import seedu.financeit.parser.InputParser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerCentre {
    private Level level;
    public static Logger loggerParamChecker = Logger.getLogger(ParamChecker.class.getName());
    public static Logger loggerInputParser = Logger.getLogger(InputParser.class.getName());
    private static LoggerCentre loggerCentre = null;

    private LoggerCentre() {
    }

    public static LoggerCentre getInstance() {
        if (loggerCentre == null) {
            loggerCentre = new LoggerCentre();
        }
        return loggerCentre;
    }

    public void setLevel(Level level) {
        this.level = level;
        loggerParamChecker.setLevel(level);
        loggerInputParser.setLevel(level);
    }
}
