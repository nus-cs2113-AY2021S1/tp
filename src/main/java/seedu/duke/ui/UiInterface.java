package seedu.duke.ui;

import seedu.duke.HintInterface;
import seedu.duke.QuestionInterface;
import seedu.duke.option.OptionInterface;

import seedu.duke.option.OptionInterface;
import seedu.duke.hint.HintInterface;
import seedu.duke.question.QuestionInterface;

public interface UiInterface {
    String getInputFromUser();

    void printGreetMessage();

    void printExitMessage();

    void printOption(OptionInterface option, int optionNumber);

    void printQuestion(QuestionInterface question, int questionNumber);

    void printHint(HintInterface hint);

    void printStartQuizPage(int numberOfQuestionsChosen, String topicsChosen);

    void printEndQuizPage();

    void printAnswerIsWrong();

    void printAnswerIsCorrect();

    void printHelp();

    void printAbout();

    void printTextbook();

    void printError();

}
