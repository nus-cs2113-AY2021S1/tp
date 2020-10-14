package seedu.eduke8;

import org.json.simple.parser.ParseException;
import seedu.eduke8.command.Command;
import seedu.eduke8.command.IncorrectCommand;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.parser.MenuParser;
import seedu.eduke8.storage.LogStorage;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_STORAGE_FAIL;

public class Eduke8 {
    private static final String DATA_PATH = "data/main/topics.json";
    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private static final String LOG_PATH = "data/logs/" + DATE_TIME_NOW.format(DATE_TIME_FORMATTER) + ".log";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private MenuParser menuParser;
    private TopicsStorage topicsStorage;
    private LogStorage logStorage;
    private TopicList topicList;
    private Ui ui;

    private Eduke8(String dataPath, String logPath) {
        menuParser = new MenuParser();
        topicsStorage = new TopicsStorage(dataPath);
        logStorage = new LogStorage(logPath);
        ui = new Ui();
        try {
            logStorage.save();
            topicList = new TopicList(topicsStorage.load());
        } catch (ParseException | IOException e) {
            new IncorrectCommand(ERROR_STORAGE_FAIL);
            LOGGER.log(Level.WARNING, "Error reading or writing local files.");
        }
    }

    private void run() {
        start();
        runCommandLoopUntilExit();
        exit();
    }

    private void start() {
        ui.printGreetMessage();
    }

    private void runCommandLoopUntilExit() {
        boolean isExit = false;

        while (!isExit) {
            String userInput = ui.getInputFromUser();

            try {
                Command command = menuParser.parseCommand(topicList, userInput);
                command.execute(topicList, ui);
                isExit = command.isExit();
            } catch (Eduke8Exception e) {
                new IncorrectCommand(e.getMessage());
            }
        }
    }

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Eduke8(DATA_PATH, LOG_PATH).run();
    }
}
