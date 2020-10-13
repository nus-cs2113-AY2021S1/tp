package seedu.eduke8.quiz;

import seedu.eduke8.command.AnswerCommand;
import seedu.eduke8.command.HintCommand;
import seedu.eduke8.command.Command;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.parser.QuizParser;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.question.QuizQuestionsManager;
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
    public void startQuiz(Ui ui) throws Eduke8Exception {
        QuestionList topicQuestionList = topic.getQuestionList();

        QuizQuestionsManager quizQuestionsManager =
                new QuizQuestionsManager(numberOfQuestions, topicQuestionList.getInnerList());

        assert !quizQuestionsManager.areAllQuestionsAnswered();

        ui.printStartQuizPage(numberOfQuestions, topic.getDescription());

        goThroughQuizQuestions(ui, quizQuestionsManager);

        ui.printEndQuizPage();
    }

    private void goThroughQuizQuestions(Ui ui, QuizQuestionsManager quizQuestionsManager) {
        while (!quizQuestionsManager.areAllQuestionsAnswered()) {
            Question question = quizQuestionsManager.getNextQuestion();
            ui.printQuestion(question, quizQuestionsManager.getCurrentQuestionNumber());

            OptionList optionList = question.getOptionList();

            ArrayList<Displayable> options = optionList.getInnerList();

            for (int i = 0; i < options.size(); i++) {
                ui.printOption((Option) options.get(i), i + 1);
            }

            quizParser.setQuestion(question);
            Command command;

            command = getCommand(ui, optionList);

            assert (command instanceof AnswerCommand || command instanceof HintCommand);

            while (command instanceof HintCommand) {
                command.execute(optionList, ui);
                command = getCommand(ui, optionList);
            }

            assert command instanceof AnswerCommand;

            command.execute(optionList, ui);
        }
    }

    private Command getCommand(Ui ui, OptionList optionList) {
        Command command;
        String userInput = ui.getInputFromUser();
        command = quizParser.parseCommand(optionList, userInput);
        return command;
    }
}
