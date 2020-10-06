package seedu.duke;

import seedu.duke.question.QuestionInterface;

public interface UiInterface {
    String getInputFromUser();

    void showToUser();

    void printGreetMessage();

    void printExitMessage();

    void printOption(OptionInterface option);

    void printQuestion(QuestionInterface question);

    void printHint(HintInterface hint);

    void printStartQuizPage();

    void printEndQuizPage();

    void printAnswerIsWrong();

    void printAnswerIsCorrect();

    void printHelp();

    void printAbout();

    void printTextbook();

    void printError();

}
