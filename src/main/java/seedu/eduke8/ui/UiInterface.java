package seedu.eduke8.ui;

import seedu.eduke8.hint.HintInterface;
import seedu.eduke8.question.QuestionInterface;
import seedu.eduke8.option.OptionInterface;

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
