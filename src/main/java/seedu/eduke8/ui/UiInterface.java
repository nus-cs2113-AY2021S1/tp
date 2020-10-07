<<<<<<< HEAD:src/main/java/seedu/eduke8/UiInterface.java
package seedu.eduke8;


import seedu.eduke8.hint.HintInterface;
import seedu.eduke8.question.QuestionInterface;
import seedu.eduke8.option.OptionInterface;

=======
package seedu.duke.ui;

import seedu.duke.hint.HintInterface;
import seedu.duke.question.QuestionInterface;
import seedu.duke.option.OptionInterface;
>>>>>>> master:src/main/java/seedu/eduke8/ui/UiInterface.java

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
