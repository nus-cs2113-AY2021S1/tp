package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.constants.Logos;
import seedu.duke.database.FileSavers;
import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;

import static seedu.duke.database.UserSettingsLoader.loadUserSettings;
import static seedu.duke.ui.UI.printDivider;
import static seedu.duke.ui.UI.printFarewellMessage;
import static seedu.duke.ui.UI.printHelloMessage;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Settings set to defaults.
     **/
    private static final int NUMBER_OF_SETTINGS = 1; // currently only, username
    public static String username = "User";
    public static ArrayList<String> savedSettings = new ArrayList<>(NUMBER_OF_SETTINGS);


    /**
     * Main entry-point for the CLIcker application.
     */
    public static void main(String[] args) {
        setUserSettingsArrayList(savedSettings, username);
        loadUserSettings(savedSettings);
        printDivider();
        username = savedSettings.get(0);

        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
        printHelloMessage(username);
        printFarewellMessage(username);

    }

        //testFbAndQnaSavers();

    private static void setUserSettingsArrayList(ArrayList<String> savedSettings, String username) {
        savedSettings.add(username);

    }

    /*
    public static void testFbAndQnaSavers() {
        System.out.println("TEST FILL BLANK and QNA FILESAVERS");

        System.out.println("Now input a fill in the blank question.");
        System.out.println("Topic: ");
        String fbTopic = SCANNER.nextLine();
        System.out.println("Subtopic");
        String fbSubTopic = SCANNER.nextLine();
        System.out.println("Question name:");
        String fbQName = SCANNER.nextLine();
        System.out.println("String before blank: ");
        String beforeBlank = SCANNER.nextLine();
        System.out.println("Blank:");
        String blank = SCANNER.nextLine();
        System.out.println("String after blank: ");
        String afterBlank = SCANNER.nextLine();
        FillBlank fb = new FillBlank(blank, beforeBlank, afterBlank);
        FileSavers.saveFillBlankQuestionToNewFile(fbTopic, fbSubTopic, fbQName, fb);

        System.out.println("Now input a qna question.");
        System.out.println("Topic: ");
        String qnaTopic = SCANNER.nextLine();
        System.out.println("Subtopic");
        String qnaSubTopic = SCANNER.nextLine();
        System.out.println("Question name:");
        String qnaName = SCANNER.nextLine();
        System.out.println("Question: ");
        String qnaQuestion = SCANNER.nextLine();
        System.out.println("Answer: ");
        String qnaAnswer = SCANNER.nextLine();
        Qna qna = new Qna(qnaAnswer, qnaQuestion);
        FileSavers.saveQnaToNewFile(qnaTopic, qnaSubTopic, qnaName, qna);
    }
     */
}
