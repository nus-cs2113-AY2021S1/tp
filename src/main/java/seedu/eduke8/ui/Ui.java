package seedu.eduke8.ui;

import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.question.Question;
import seedu.eduke8.topic.TopicList;

import java.util.Scanner;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_STORAGE_FAIL;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_INVALID_QUESTION_NUMBER;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_UNRECOGNIZED_COMMAND;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_UNKNOWN;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_ANSWER_NOT_INDEX;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_WRONG_FORMAT;
import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUIZ_COMMAND_NOT_IMPLEMENTED;

public class Ui {

    private static final int LAST_OPTION = 4;
    private static final String TEXTBOOK_WEBSITE =
            "https://nus-cs2113-ay2021s1.github.io/website/se-book-adapted/index.html";

    private static final String LOGO = " _____        _____\n"
            + "|  ___| ____ |  _  |\n"
            + "| |___ |  _ \\| |_| |\n"
            + "|  ___|| | | |  _  |\n"
            + "| |___ | |_| | |_| |\n"
            + "|_____||____/|_____|";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String HORIZONTAL_LINE = "-------------------------------------------------------------------";
    private static final String MESSAGE_ABOUT = "E-Duke-8 is a desktop app that helps CS2113/T students learn and\n"
            + "understand software engineering and OOP principles through a\ngamified platform and enhance their "
            + "learning experience. It also\nconsolidates key concepts for easy revision.";
    private static final String MESSAGE_GREETINGS = "Hello! I'm E-Duke-8\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye bye. Hope you have a nice day and see you soon!";
    private static final String MESSAGE_HELP = "These are the commands that you can used:\n1) about\n2) help\n"
            + "3) topics\n4) textbook\n5) quiz t/<topic> n/<number of questions>\n6) exit";
    private static final String MESSAGE_QUIZ_START = "Start of quiz:";
    private static final String MESSAGE_QUIZ_END = "This is the end of the quiz!\nHope you have learnt something new!";
    private static final String MESSAGE_ANSWER_WRONG = "Oops! The correct answer is ";
    private static final String MESSAGE_ANSWER_WRONG_SECOND = "! Do visit the textbook to read up more.";
    private static final String MESSAGE_ANSWER_CORRECT = "Congrats! This answer is correct! Well Done!";
    private static final String MESSAGE_TEXTBOOK = "The textbook for this module is available at:\n" + TEXTBOOK_WEBSITE;
    private static final String MESSAGE_HINT = "Hint: ";

    public String getInputFromUser() {
        return SCANNER.nextLine();
    }

    private static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGreetMessage() {
        System.out.println(LOGO);
        printMessage(MESSAGE_GREETINGS);
    }

    public void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    public void printOption(Option option, int optionNumber) {
        System.out.println(optionNumber + ") " + option.getDescription());
        if (optionNumber == LAST_OPTION) {
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public void printQuestion(Question question, int questionNumber) {
        System.out.println(questionNumber + ". " + question.getDescription());
    }

    public void printHint(Hint hint) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_HINT + hint.getDescription());
        System.out.println(HORIZONTAL_LINE);
    }

    //Formatting of topicsChosen: separated by ","
    public void printStartQuizPage(int numberOfQuestionsChosen, String topicsChosen) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_QUIZ_START);

        //Showing the number of questions chosen by user
        printStartQuizQuestions(numberOfQuestionsChosen);

        //Showing the topics chosen by user
        printStartQuizTopics(topicsChosen);

        System.out.println(HORIZONTAL_LINE);
    }

    public void printEndQuizPage() {
        System.out.println(MESSAGE_QUIZ_END);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAnswerIsWrong(int correctAnswer) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_ANSWER_WRONG + correctAnswer + MESSAGE_ANSWER_WRONG_SECOND);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAnswerIsCorrect() {
        printMessage(MESSAGE_ANSWER_CORRECT);
    }

    public void printHelp() {
        printMessage(MESSAGE_HELP);
    }

    public void printAbout() {
        printMessage(MESSAGE_ABOUT);
    }

    public void printAllTopics(TopicList topics) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("These are the available topics:");
        for (int i = 0; i < topics.getCount(); i++) {
            System.out.println(i + 1 + ") " + topics.getInnerList().get(i).getDescription());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public void printTextbook() {
        printMessage(MESSAGE_TEXTBOOK);
    }

    public void printError(String errorMessage) {
        switch (errorMessage) {
        case ERROR_STORAGE_FAIL:
            printMessage(ERROR_STORAGE_FAIL);
            break;
        case ERROR_UNRECOGNIZED_COMMAND:
            printMessage(ERROR_UNRECOGNIZED_COMMAND);
            break;
        case ERROR_QUIZ_WRONG_FORMAT:
            printMessage(ERROR_QUIZ_WRONG_FORMAT);
            break;
        case ERROR_QUIZ_COMMAND_NOT_IMPLEMENTED:
            printMessage(ERROR_QUIZ_COMMAND_NOT_IMPLEMENTED);
            break;
        case ERROR_QUIZ_ANSWER_NOT_INDEX:
            printMessage(ERROR_QUIZ_ANSWER_NOT_INDEX);
            break;
        case ERROR_QUIZ_INVALID_QUESTION_NUMBER:
            printMessage(ERROR_QUIZ_INVALID_QUESTION_NUMBER);
            break;
        case ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS:
            printMessage(ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS);
            break;
        default:
            printMessage(ERROR_UNKNOWN);
            break;
        }
    }

    private void printStartQuizTopics(String topicsChosen) {
        if (topicsChosen.contains(",")) {
            int indexOfSplit = topicsChosen.indexOf(",");
            String topics1 = topicsChosen.substring(0, indexOfSplit).trim();
            String topics2 = topicsChosen.substring(indexOfSplit + 1).trim();
            System.out.println("The topics chosen are: " + topics1 + " and " + topics2);
        } else {
            System.out.println("The topic chosen is: " + topicsChosen);
        }
    }

    private void printStartQuizQuestions(int numberOfQuestionsChosen) {
        System.out.print("You have chosen to complete " + numberOfQuestionsChosen + " question");
        if (numberOfQuestionsChosen < 2) {
            System.out.println(".");
        } else {
            System.out.println("s.");
        }
    }

}
