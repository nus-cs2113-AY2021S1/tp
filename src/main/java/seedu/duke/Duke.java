package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.database.QuestionLoaders;
import seedu.duke.database.QuestionSavers;
import seedu.duke.exceptions.FilePathInvalidException;
import seedu.duke.exceptions.QuestionTypeException;
import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;
import seedu.duke.question.Question;

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
//        setUserSettingsArrayList(savedSettings, username);
//        loadUserSettings(savedSettings);
//        printDivider();
//        username = savedSettings.get(0);
//
//        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
//        printHelloMessage(username);
//        printFarewellMessage(username);
//        testFbAndQnaSavers();
//        testFbLoaders();
    }

    private static void setUserSettingsArrayList(ArrayList<String> savedSettings, String username) {
        savedSettings.add(username);

    }
/*
    public static void testFbLoaders() {
        try {
            QuestionLoaders.loadFillBlankAndQnaQuestion("\\data\\Quizzes\\Biology\\Human Body", "Circulatory System 3_fb.txt");
            QuestionLoaders.loadFillBlankAndQnaQuestion("\\data\\Quizzes\\Physics\\Newton Laws", "Newton's First Law_qna.txt");
        } catch (FilePathInvalidException e) {
            System.out.println("File path is wrongly formatted");
        }
    }

    public static void testFbAndQnaSavers() {
        System.out.println("TEST FILL BLANK and QNA FILESAVERS");

        System.out.println("Now input a fill in the blank question.");
        System.out.println("Topic: ");
        String fbTopic = SCANNER.nextLine();
        System.out.println("Subtopic: ");
        String fbSubTopic = SCANNER.nextLine();
        System.out.println("Question name: ");
        String fbQName = SCANNER.nextLine();
        System.out.println("String before blank: ");
        String beforeBlank = SCANNER.nextLine();
        System.out.println("Blank: ");
        String blank = SCANNER.nextLine();
        System.out.println("String after blank: ");
        String afterBlank = SCANNER.nextLine();
        Question fb = new FillBlank(blank, beforeBlank, afterBlank);
        try {
            QuestionSavers.saveFillBlankAndQnaQuestionToNewFile(fbTopic, fbSubTopic, fbQName, fb);
        } catch (QuestionTypeException e) {
            System.out.println("FillBlank type not recognizes");
            e.printStackTrace();
        }

        System.out.println("Now input a qna question.");
        System.out.println("Topic: ");
        String qnaTopic = SCANNER.nextLine();
        System.out.println("Subtopic: ");
        String qnaSubTopic = SCANNER.nextLine();
        System.out.println("Question name: ");
        String qnaName = SCANNER.nextLine();
        System.out.println("Question: ");
        String qnaQuestion = SCANNER.nextLine();
        System.out.println("Answer: ");
        String qnaAnswer = SCANNER.nextLine();
        Question qna = new Qna(qnaAnswer, qnaQuestion);
        try {
            QuestionSavers.saveFillBlankAndQnaQuestionToNewFile(qnaTopic, qnaSubTopic, qnaName, qna);
        } catch (QuestionTypeException e) {
            System.out.println("Qna type not recognized");
            e.printStackTrace();
        }
    }
*/

}
