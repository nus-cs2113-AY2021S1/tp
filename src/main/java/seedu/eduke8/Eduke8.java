package seedu.eduke8;

import seedu.eduke8.parser.MenuParser;
import seedu.eduke8.storage.TopicsStorage;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class Eduke8 {
    private static final String FILE_PATH = "data";

    private MenuParser menuParser;
    private TopicsStorage topicsStorage;
    private TopicList topicList;
    private Ui ui;

    private Eduke8(String filePath) {
        menuParser = new MenuParser();
        topicsStorage = new TopicsStorage(filePath);
        ui = new Ui();
        topicList = new TopicList(topicsStorage.load());
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
        //boolean isExit = false;

        //while(!isExit) {
        String userInput = ui.getInputFromUser();
        menuParser.parseCommand(topicList, userInput);
        //isExit = parser.parseCommand(userInput);

        //}
    }

    private void exit() {
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Eduke8(FILE_PATH).run();
    }
}
