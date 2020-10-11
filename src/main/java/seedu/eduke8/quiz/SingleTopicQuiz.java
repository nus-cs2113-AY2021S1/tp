package seedu.eduke8.quiz;

import seedu.eduke8.command.Command;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.parser.QuizParser;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.TopicQuestionList;
import seedu.eduke8.question.QuizQuestionList;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class SingleTopicQuiz implements Quiz {
    private Topic topic;
    private int numberOfQuestions;
    private QuizParser quizParser;

    public SingleTopicQuiz(Topic topic, int numberOfQuestions) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
        quizParser = new QuizParser();
    }

    @Override
    public void startQuiz(Ui ui) {
        ui.printStartQuizPage(numberOfQuestions, topic.getDescription());
        TopicQuestionList topicQuestionList = topic.getQuestionList();
        QuizQuestionList quizQuestionList = topicQuestionList.getQuizQuestionList(numberOfQuestions);

        goThroughQuizQuestions(ui, quizQuestionList);

        ui.printEndQuizPage();
    }

    private void goThroughQuizQuestions(Ui ui, QuizQuestionList quizQuestionList) {
        while (!quizQuestionList.areAllQuestionsAnswered()) {
            Question question = quizQuestionList.getNextQuestion();
            ui.printQuestion(question, quizQuestionList.getCurrentQuestionNumber());

            OptionList optionList = question.getOptionList();

            ArrayList<Displayable> options = optionList.getInnerList();

            for (int i = 0; i < options.size(); i++) {
                ui.printOption((Option) options.get(i), i + 1);
            }

            String userInput = ui.getInputFromUser();

            quizParser.setQuestion(question);
            Command command = quizParser.parseCommand(optionList, userInput);

            command.execute(optionList, ui);
        }
    }
}
