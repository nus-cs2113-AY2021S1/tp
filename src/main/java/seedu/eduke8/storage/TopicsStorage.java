package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.topic.Topic;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import static java.util.stream.Collectors.toList;

public class TopicsStorage extends LocalStorage {

    public TopicsStorage(String filePath) {
        super(filePath);
    }

    @Override
    public void save() throws IOException {
        createFileIfNotExists();

        // For adding and removing questions for v2
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(filePath);

        //Read JSON file
        JSONArray topicsAsJsonArray = (JSONArray) jsonParser.parse(reader);

        //Iterate over employee array
        ArrayList<Displayable> topicsAsObjects = (ArrayList<Displayable>) topicsAsJsonArray.stream()
                .map(topic -> parseToTopicObject((JSONObject) topic))
                .collect(toList());

        assert topicsAsObjects.get(0) instanceof Topic;

        LOGGER.log(Level.INFO, "Topics loaded from file");

        return topicsAsObjects;
    }

    private static Topic parseToTopicObject(JSONObject topic) {
        String topicTitle = (String) topic.get("topic");

        JSONArray questionsAsJsonArray = (JSONArray) topic.get("questions");
        ArrayList<Displayable> questionsAsObjects = (ArrayList<Displayable>) questionsAsJsonArray.stream()
                .map(question -> parseToQuestionObject((JSONObject) question))
                .collect(toList());

        assert questionsAsObjects.get(0) instanceof Question;

        QuestionList questionList = new QuestionList(questionsAsObjects);

        return new Topic(topicTitle, questionList);
    }

    private static Question parseToQuestionObject(JSONObject question) {
        String questionDescription = (String) question.get("description");
        JSONArray optionsAsJsonArray = (JSONArray) question.get("options");
        ArrayList<Displayable> optionsAsObjects = (ArrayList<Displayable>) optionsAsJsonArray.stream()
                .map(option -> parseToOptionObject((JSONObject) option))
                .collect(toList());

        assert optionsAsObjects.get(0) instanceof Option;

        OptionList optionList = new OptionList(optionsAsObjects);

        String hintDescription = (String) question.get("hint");

        Hint hint = new Hint(hintDescription);

        return new Question(questionDescription, optionList, hint);
    }

    private static Option parseToOptionObject(JSONObject option) {
        String optionDescription = (String) option.get("description");
        boolean isCorrectAnswer = (boolean) option.get("correct");

        Option optionAsObject = new Option(optionDescription);

        if (isCorrectAnswer) {
            optionAsObject.markAsCorrectAnswer();
        }

        assert optionAsObject.isCorrectAnswer() == isCorrectAnswer;

        return optionAsObject;
    }
}
