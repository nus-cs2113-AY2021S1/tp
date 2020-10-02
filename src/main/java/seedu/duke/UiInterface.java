package seedu.duke;

public interface UiInterface {
    public String getInputFromUser();
    public void showToUser();
    public void printGreetMessage();
    public void printExitMessage();
    public void printOption(OptionInterface option);
    public void printQuestion(QuestionInterface question);
    public void printHint(HintInterface hint);
    public void printStartQuizPage();
    public void printEndQuizPage();
    public void printAnswerIsWrong();
    public void printAnswerIsCorrect();
    public void printHelp();
    public void printAbout();
    public void printTextbook();
    public void printError();
}
