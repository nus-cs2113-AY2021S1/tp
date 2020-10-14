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
        JSONArray topicsArray = (JSONArray) jsonParser.parse(reader);

        //Iterate over employee array
        ArrayList<Displayable> topicsAsObjects = (ArrayList<Displayable>) topicsArray.stream()
                .map(topic -> parseToTopicObject((JSONObject) topic))
                .collect(toList());

        return topicsAsObjects;
    }

    private static Topic parseToTopicObject(JSONObject topic) {
        String topicTitle = (String) topic.get("topic");

        JSONArray questionsArray = (JSONArray) topic.get("questions");
        ArrayList<Displayable> questionsAsObjects = (ArrayList<Displayable>) questionsArray.stream()
                .map(question -> parseToQuestionObject((JSONObject) question))
                .collect(toList());

        QuestionList questionList = new QuestionList(questionsAsObjects);

        Topic topicAsObject = new Topic(topicTitle, questionList);

        return topicAsObject;
    }

    private static Question parseToQuestionObject(JSONObject question) {
        String questionDescription = (String) question.get("description");
        JSONArray optionsArray = (JSONArray) question.get("options");
        ArrayList<Displayable> optionsAsObjects = (ArrayList<Displayable>) optionsArray.stream()
                .map(option -> parseToOptionObject((JSONObject) option))
                .collect(toList());

        OptionList optionList = new OptionList(optionsAsObjects);

        String hintDescription = (String) question.get("hint");

        Hint hint = new Hint(hintDescription);

        Question questionAsObject = new Question(questionDescription, optionList, hint);

        return questionAsObject;
    }

    private static Option parseToOptionObject(JSONObject option) {
        String optionDescription = (String) option.get("description");
        boolean isCorrectAnswer = (boolean) option.get("correct");

        Option optionAsObject = new Option(optionDescription);

        if (isCorrectAnswer) {
            optionAsObject.markAsCorrectAnswer();
        }

        return optionAsObject;
    }
}
