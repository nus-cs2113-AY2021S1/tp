package seedu.duke;

import java.util.Scanner;
import seedu.duke.constants.Logos;
import seedu.duke.database.FileSavers;
import seedu.duke.question.FillBlank;
import seedu.duke.question.Qna;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        System.out.println("Take a quiz with\n" + Logos.DOTTED_CLICKER_LOGO);
        System.out.println("What is your name?");

        System.out.println("Hello " + SCANNER.nextLine());

        //testFbAndQnaSavers();
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
