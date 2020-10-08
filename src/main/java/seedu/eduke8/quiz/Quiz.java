package seedu.eduke8.quiz;

import seedu.eduke8.Parser;
import seedu.eduke8.topic.TopicInterface;
import seedu.eduke8.ui.UiInterface;
import seedu.eduke8.option.OptionInterface;
import seedu.eduke8.question.QuestionInterface;
import seedu.eduke8.question.QuestionListInterface;

import java.util.ArrayList;

public class Quiz implements QuizInterface {
    private TopicInterface topic;
    private int numberOfQuestions;
    Parser parser = new Parser();

    public Quiz(TopicInterface topic, int numberOfQuestions) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public void startQuiz(UiInterface ui) {
        ui.printStartQuizPage(numberOfQuestions, topic.getTopic());
        QuestionListInterface questionList = topic.getQuestionList();
        questionList.setQuizQuestions(numberOfQuestions);

        goThroughQuizQuestions(ui, questionList);

        ui.printEndQuizPage();
    }

    private void goThroughQuizQuestions(UiInterface ui, QuestionListInterface questionList) {
        while (!questionList.areAllQuestionsAnswered()) {
            QuestionInterface question = questionList.getNextQuestion();
            ui.printQuestion(question, questionList.getCurrentQuestionNumber());

            ArrayList<OptionInterface> options = question.getOptions();

            for (int i = 0; i < options.size(); i++) {
                ui.printOption(options.get(i), i + 1);
            }

            parser.parseChosenOption(ui, options, question);
        }
    }
}
