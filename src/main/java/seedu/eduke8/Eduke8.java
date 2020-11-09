package seedu.eduke8;

import org.json.simple.parser.ParseException;
import seedu.eduke8.bookmark.BookmarkList;
import seedu.eduke8.command.Command;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.parser.MenuParser;
import seedu.eduke8.storage.LogStorage;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.storage.UserStorage;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_STORAGE_LOAD_FAIL;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_STORAGE_SAVE_FAIL;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_USER_JSON_LOAD;

public class Eduke8 {
    private static final String DATA_PATH = "data/main/topics.json";
    private static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private static final String LOG_PATH = "data/logs/" + DATE_TIME_NOW.format(DATE_TIME_FORMATTER) + ".log";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USER_PATH = "data/main/user.json";

    private MenuParser menuParser;
    private TopicsStorage topicsStorage;
    private UserStorage userStorage;
    private LogStorage logStorage;
    private TopicList topicList;
    private BookmarkList bookmarkList;
    private Ui ui;

    private Eduke8(String dataPath, String logPath, String userPath) {
        ui = new Ui();
        bookmarkList = new BookmarkList();

        ui.printDataLoading();
        try {
            topicsStorage = new TopicsStorage(dataPath);
            logStorage = new LogStorage(logPath);

            logStorage.save();
            topicList = new TopicList(topicsStorage.load());
        } catch (ParseException | IOException | ClassCastException | NullPointerException e) {
            ui.printError(ERROR_STORAGE_LOAD_FAIL);
            LOGGER.log(Level.WARNING, ERROR_STORAGE_LOAD_FAIL);
            System.exit(1);
        } catch (Eduke8Exception e) {
            ui.printError(e.getMessage());
            LOGGER.log(Level.INFO, e.getMessage());
            System.exit(1);
        }

        userStorage = new UserStorage(userPath, topicList, bookmarkList);
        try {
            userStorage.load();
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            ui.printError(ERROR_USER_JSON_LOAD);
            LOGGER.log(Level.WARNING, ERROR_USER_JSON_LOAD);
        } catch (Eduke8Exception e) {
            ui.printError(e.getMessage());
            LOGGER.log(Level.INFO, e.getMessage());
        }

        ui.printDataLoaded();

        menuParser = new MenuParser(bookmarkList);
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

            Command command = menuParser.parseCommand(topicList, userInput);
            command.execute(topicList, ui);
            isExit = command.isExit();
            saveUserData();
        }
    }

    private void exit() {
        ui.printDataSaving();
        saveUserData();
        ui.printDataSaved();

        ui.printExitMessage();
        System.exit(0);
    }

    private void saveUserData() {
        try {
            userStorage.save();
        } catch (IOException e) {
            ui.printError(ERROR_STORAGE_SAVE_FAIL);
            LOGGER.log(Level.WARNING, ERROR_STORAGE_SAVE_FAIL);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Eduke8(DATA_PATH, LOG_PATH, USER_PATH).run();
    }
}
