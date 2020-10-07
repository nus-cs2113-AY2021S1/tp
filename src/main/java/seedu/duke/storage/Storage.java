package seedu.duke.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.duke.topic.TopicInterface;
import seedu.duke.option.OptionInterface;
import seedu.duke.option.OptionListInterface;
import seedu.duke.question.QuestionInterface;
import seedu.duke.question.QuestionListInterface;
import seedu.duke.UiInterface;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Storage implements StorageInterface {
    private String filePath = new File("").getAbsolutePath();
    private UiInterface ui;

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
            e.printStackTrace();
            // ui.printError();
        }

        return null;
    }

    private static TopicInterface parseToTopicObject(JSONObject topic) {
        String topicTitle = (String) topic.get("topic");

        JSONArray questionsArray = (JSONArray) topic.get("questions");
        ArrayList<QuestionInterface> questionsAsObjects = (ArrayList<QuestionInterface>) questionsArray.stream()
                .map(question -> parseToQuestionObject((JSONObject) question))
                .collect(toList());

        QuestionListInterface questionList = null;
        // TODO construct questionList object when class is available

        TopicInterface topicAsObject = null;
        // TODO construct topic object when class is available

        return topicAsObject;
    }

    private static QuestionInterface parseToQuestionObject(JSONObject question) {
        String questionDescription = (String) question.get("description");
        JSONArray optionsArray = (JSONArray) question.get("options");
        ArrayList<OptionInterface> optionsAsObjects = (ArrayList<OptionInterface>) optionsArray.stream()
                .map(option -> parseToOptionObject((JSONObject) option))
                .collect(toList());

        OptionListInterface optionList = null;
        // TODO construct optionList object when class is available

        String hintDescription = (String) question.get("hint");

        QuestionInterface questionAsObject = null;
        // TODO construct question object when class is available

        return questionAsObject;
    }

    private static OptionInterface parseToOptionObject(JSONObject option) {
        String optionDescription = (String) option.get("description");
        boolean isCorrectAnswer = (boolean) option.get("correct");

        OptionInterface optionAsObject = null;
        // TODO construct option object when class is available

        return optionAsObject;
    }

    private void createFileIfNotExists() {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
