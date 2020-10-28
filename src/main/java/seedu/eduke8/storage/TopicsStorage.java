package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.explanation.Explanation;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;

public class TopicsStorage extends LocalStorage {

    public TopicsStorage(String filePath) {
        super(filePath);
    }

    /**
     * Returns topics loaded from a local file in JSON format.
     *
     * @return Topics loaded into an ArrayList.
     * @throws IOException  If the file is not found or cannot be read.
     * @throws ParseException  If the file contents cannot be parsed as a JSON.
     * @throws ClassCastException If the the nesting of arrays and objects in the JSON is wrong
     * @throws NullPointerException If the keys required are not present in the file.
     */
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Displayable> load() throws IOException, ParseException, ClassCastException, NullPointerException {
        JSONArray topicsAsJsonArray = getJsonArrayFromFile();

        //Iterate over topics array
        ArrayList<Displayable> topicsAsObjects = (ArrayList<Displayable>) topicsAsJsonArray.stream()
                .map(topic -> parseToTopicObject((JSONObject) topic))
                .collect(toList());

        assert topicsAsObjects.get(0) instanceof Topic;

        LOGGER.log(Level.INFO, "Topics loaded from file");

        return topicsAsObjects;
    }

    @SuppressWarnings("unchecked")
    private Topic parseToTopicObject(JSONObject topic) {
        String topicTitle = (String) topic.get(KEY_TOPIC);

        JSONArray questionsAsJsonArray = (JSONArray) topic.get(KEY_QUESTIONS);
        ArrayList<Displayable> questionsAsObjects = (ArrayList<Displayable>) questionsAsJsonArray.stream()
                .map(question -> parseToQuestionObject((JSONObject) question))
                .collect(toList());

        assert questionsAsObjects.get(0) instanceof Question;

        QuestionList questionList = new QuestionList(questionsAsObjects);

        return new Topic(topicTitle, questionList);
    }

    @SuppressWarnings("unchecked")
    private Question parseToQuestionObject(JSONObject question) {
        String questionDescription = (String) question.get(KEY_DESCRIPTION);
        JSONArray optionsAsJsonArray = (JSONArray) question.get(KEY_OPTIONS);
        ArrayList<Displayable> optionsAsObjects = (ArrayList<Displayable>) optionsAsJsonArray.stream()
                .map(option -> parseToOptionObject((JSONObject) option))
                .collect(toList());

        assert optionsAsObjects.get(0) instanceof Option;

        OptionList optionList = new OptionList(optionsAsObjects);

        String hintDescription = (String) question.get(KEY_HINT);

        Hint hint = new Hint(hintDescription);

        String explanationDescription = (String) question.get(KEY_EXPLANATION);

        Explanation explanation = new Explanation(explanationDescription);

        return new Question(questionDescription, optionList, hint, explanation);
    }

    private Option parseToOptionObject(JSONObject option) {
        String optionDescription = (String) option.get(KEY_DESCRIPTION);
        boolean isCorrectAnswer = (boolean) option.get(KEY_CORRECT);

        Option optionAsObject = new Option(optionDescription);

        if (isCorrectAnswer) {
            optionAsObject.markAsCorrectAnswer();
        }

        assert optionAsObject.isCorrectAnswer() == isCorrectAnswer;

        return optionAsObject;
    }
}
