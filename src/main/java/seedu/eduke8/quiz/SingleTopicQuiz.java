package seedu.eduke8.quiz;

import seedu.eduke8.Command;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.parser.Parser;
import seedu.eduke8.parser.QuizParser;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class SingleTopicQuiz implements Quiz {
    private Topic topic;
    private int numberOfQuestions;
    private Parser quizParser = new QuizParser();

    public SingleTopicQuiz(Topic topic, int numberOfQuestions) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public void startQuiz(Ui ui) {
        ui.printStartQuizPage(numberOfQuestions, topic.getDescription());
        QuestionList questionList = topic.getQuestionList();
        questionList.setQuizQuestions(numberOfQuestions);

        goThroughQuizQuestions(ui, questionList);

        ui.printEndQuizPage();
    }

    private void goThroughQuizQuestions(Ui ui, QuestionList questionList) {
        while (!questionList.areAllQuestionsAnswered()) {
            Question question = questionList.getNextQuestion();
            ui.printQuestion(question, questionList.getCurrentQuestionNumber());

            OptionList optionList = question.getOptionList();

            ArrayList<Displayable> options = optionList.getInnerList();

            for (int i = 0; i < options.size(); i++) {
                ui.printOption((Option) options.get(i), i + 1);
            }

            String userInput = ui.getInputFromUser();

            Command answerCommand = quizParser.parseCommand(optionList, userInput);

            answerCommand.execute(questionList, ui);
        }
    }
}
