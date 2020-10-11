package seedu.eduke8;

import org.json.simple.parser.ParseException;
import seedu.eduke8.parser.MenuParser;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

import java.io.IOException;

public class Eduke8 {
    private static final String FILE_PATH = "data/test/example.json"; // Real path will be data/main/topics.json

    private MenuParser menuParser;
    private TopicsStorage topicsStorage;
    private TopicList topicList;
    private Ui ui;

    private Eduke8(String filePath) {
        menuParser = new MenuParser();
        topicsStorage = new TopicsStorage(filePath);
        ui = new Ui();
        try {
            topicList = new TopicList(topicsStorage.load());
        } catch (ParseException | IOException e) {
            ui.printError();
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
            Command command = menuParser.parseCommand(topicList, userInput);
            // command.execute(topicList, ui);
            isExit = true; //command.isExit();
        }
    }

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Eduke8(FILE_PATH).run();
    }
}
