package seedu.duke.quiz;

import seedu.duke.TopicInterface;
import seedu.duke.ui.UiInterface;
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
        ui.printStartQuizPage(numberOfQuestions, topic.getTopic());
        QuestionListInterface questionList = topic.getQuestionList();
        questionList.setQuizQuestions(numberOfQuestions);

        goThroughQuizQuestions(ui, questionList);

        ui.printEndQuizPage();
    }

    private void goThroughQuizQuestions(UiInterface ui, QuestionListInterface questionList) {
        while (!questionList.areAllQuestionsAnswered()) {
            QuestionInterface question = questionList.getNextQuestion();
            ui.printQuestion(question, questionList.getQuestionCount());

            ArrayList<OptionInterface> options = question.getOptions();

            for (int i = 0; i < options.size(); i++) {
                ui.printOption(options.get(i), i + 1);
            }

            parseChosenOption(ui, options);
        }
    }

    private void parseChosenOption(UiInterface ui, ArrayList<OptionInterface> options) {
        // Should probably use parser for this part to add hints also
        int chosen = Integer.parseInt(ui.getInputFromUser());
        if (options.get(chosen).isCorrectAnswer()) {
            ui.printAnswerIsCorrect();
        } else {
            ui.printAnswerIsWrong();
        }
    }
}
