package seedu.duke.quiz;

import seedu.duke.TopicInterface;
import seedu.duke.UiInterface;
import seedu.duke.option.OptionInterface;
import seedu.duke.question.QuestionInterface;
import seedu.duke.question.QuestionListInterface;

import java.util.ArrayList;

public class Quiz implements QuizInterface {
    private TopicInterface topic;
    private int numberOfQuestions;

    public Quiz(TopicInterface topic, int numberOfQuestions) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public void startQuiz(UiInterface ui) {
        ui.printStartQuizPage();
        QuestionListInterface questionList = topic.getQuestionList();
        questionList.setQuizQuestions(numberOfQuestions);
        while (!questionList.areAllQuestionsAnswered()) {
            QuestionInterface question = questionList.getNextQuestion();
            ui.printQuestion(question);
            ArrayList<OptionInterface> options = question.getOptions();
            for (OptionInterface option: options) {
                ui.printOption(option);
            }
            int chosen = Integer.parseInt(ui.getInputFromUser());

        }
    }
}
