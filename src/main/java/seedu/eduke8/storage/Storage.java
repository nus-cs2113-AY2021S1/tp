package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.eduke8.hint.Hint;
import seedu.eduke8.hint.HintInterface;
import seedu.eduke8.option.Option;
import seedu.eduke8.option.OptionInterface;
import seedu.eduke8.option.OptionList;
import seedu.eduke8.option.OptionListInterface;
import seedu.eduke8.question.Question;
import seedu.eduke8.question.QuestionInterface;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.question.QuestionListInterface;
import seedu.eduke8.topic.Topic;
import seedu.eduke8.topic.TopicInterface;
import seedu.eduke8.ui.Ui;
import seedu.eduke8.ui.UiInterface;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Storage implements StorageInterface {
    private String filePath = new File("").getAbsolutePath();
    private static UiInterface ui = new Ui();

    public Storage(String filePath) {
        // Use relative path for Unix systems
        String[] filePathSplit = filePath.split("/");
        for (String path: filePathSplit) {
            this.filePath += File.separator + path;
        }
    }

    @Override
    public void save(ArrayList<TopicInterface> topics) {
        createFileIfNotExists();

        // For adding and removing questions for v2
    }

    @Override
    public ArrayList<TopicInterface> load() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(filePath);

            //Read JSON file
            JSONArray topicsArray = (JSONArray) jsonParser.parse(reader);

            //Iterate over employee array
            ArrayList<TopicInterface> topicsAsObjects = (ArrayList<TopicInterface>) topicsArray.stream()
                    .map(topic -> parseToTopicObject((JSONObject) topic))
                    .collect(toList());

            return topicsAsObjects;

        } catch (IOException | ParseException e) {
            ui.printError();
        }

        return null;
    }

    private static TopicInterface parseToTopicObject(JSONObject topic) {
        String topicTitle = (String) topic.get("topic");

        JSONArray questionsArray = (JSONArray) topic.get("questions");
        ArrayList<QuestionInterface> questionsAsObjects = (ArrayList<QuestionInterface>) questionsArray.stream()
                .map(question -> parseToQuestionObject((JSONObject) question))
                .collect(toList());

        QuestionListInterface questionList = new QuestionList(questionsAsObjects);

        TopicInterface topicAsObject = new Topic(topicTitle, questionList);

        return topicAsObject;
    }

    private static QuestionInterface parseToQuestionObject(JSONObject question) {
        String questionDescription = (String) question.get("description");
        JSONArray optionsArray = (JSONArray) question.get("options");
        ArrayList<OptionInterface> optionsAsObjects = (ArrayList<OptionInterface>) optionsArray.stream()
                .map(option -> parseToOptionObject((JSONObject) option))
                .collect(toList());

        OptionListInterface optionList = new OptionList(optionsAsObjects);

        String hintDescription = (String) question.get("hint");

        HintInterface hint = new Hint(hintDescription);

        QuestionInterface questionAsObject = new Question(questionDescription, optionList, hint);

        return questionAsObject;
    }

    private static OptionInterface parseToOptionObject(JSONObject option) {
        String optionDescription = (String) option.get("description");
        boolean isCorrectAnswer = (boolean) option.get("correct");

        OptionInterface optionAsObject = new Option(optionDescription);

        if (isCorrectAnswer) {
            optionAsObject.markAsCorrectAnswer();
        }

        return optionAsObject;
    }

    private void createFileIfNotExists() {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                ui.printError();
            }
        }
    }
}
